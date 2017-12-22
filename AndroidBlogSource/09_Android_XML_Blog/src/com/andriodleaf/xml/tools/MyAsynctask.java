package com.andriodleaf.xml.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.andriodleaf.xml.https.HttpRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;


/**
 * �첽���󹤾���
 * @author AndroidLeaf
 */
public class MyAsynctask extends AsyncTask<Object, Void, Object>  {

	private ImageView mImageView;
	private ImageCallBack mImageCallBack;
	//�������ͣ���ΪXML�ļ������ͼƬ��������
	private int typeId;
	//ʹ�õ�XML��������ID
	private int requestId;
	
	/**
	  * ����һ���ص������ڼ����������󣬵�������������ط��ʽ��
	  */
	 public HttpDownloadedListener mHttpDownloadedListener;
	
	 public interface HttpDownloadedListener{
		public void onDownloaded(String result,int requestId);
	 }
	 
	 public void setOnHttpDownloadedListener(HttpDownloadedListener mHttpDownloadedListener){
		 this.mHttpDownloadedListener = mHttpDownloadedListener;
	 }
	
	public MyAsynctask(ImageView mImageView,ImageCallBack mImageCallBack,int requestId){
		this.mImageView = mImageView;
		this.mImageCallBack = mImageCallBack;
		this.requestId = requestId;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		InputStream mInputStream = HttpRequest.getInputStreamFromNetWork((String)params[0]);
		if(mInputStream != null){
			switch ((int)params[1]) {
			case Constants.TYPE_STR:
				typeId = Constants.TYPE_STR;
				return WriteIntoFile(mInputStream);
			case Constants.TYPE_STREAM:
				typeId = Constants.TYPE_STREAM;
				return getBitmap(HttpRequest.readStream(mInputStream),
						200, 200);
			default:
				break;
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		if(result != null){
			switch (typeId) {
			case Constants.TYPE_STR:
				mHttpDownloadedListener.onDownloaded((String)result,requestId);
				break;
			case Constants.TYPE_STREAM:
				mImageCallBack.resultImage(mImageView,(Bitmap)result);
				break;
			default:
				break;
			}
			typeId = -1;
		}
		super.onPostExecute(result);
	}
	
	public Bitmap getBitmap(byte[] bytes,int width,int height){
		//��ȡ��Ļ�Ŀ�͸�  
        /** 
         * Ϊ�˼������ŵı�����������Ҫ��ȡ����ͼƬ�ĳߴ磬������ͼƬ 
         * BitmapFactory.Options������һ�������ͱ���inJustDecodeBounds����������Ϊtrue 
         * ���������ǻ�ȡ���ľ���ͼƬ�ĳߴ磬�����ü���ͼƬ�ˡ� 
         * �������������ֵ��ʱ�����ǽ��žͿ��Դ�BitmapFactory.Options��outWidth��outHeight�л�ȡ��ֵ 
         */  
        BitmapFactory.Options op = new BitmapFactory.Options();  
        op.inJustDecodeBounds = true;  
        Bitmap pic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        
        int wRatio = (int) Math.ceil(op.outWidth / (float) width); //�����ȱ���  
        int hRatio = (int) Math.ceil(op.outHeight / (float) height); //����߶ȱ���
        
        /** 
         * �����������Ǿ���Ҫ�ж��Ƿ���Ҫ�����Լ����׶Կ��Ǹ߽������š� 
         * ����ߺͿ���ȫ����������Ļ����ô�������š� 
         * ����ߺͿ���������Ļ��С�������ѡ�������ء� 
         * ����Ҫ�ж�wRatio��hRatio�Ĵ�С 
         * ���һ���������ţ���Ϊ���Ŵ��ʱ��С��Ӧ���Զ�����ͬ�������š� 
         * ����ʹ�õĻ���inSampleSize���� 
         */  
        if (wRatio > 1 && hRatio > 1) {  
            if (wRatio > hRatio) {  
                op.inSampleSize = wRatio;  
            } else {  
                op.inSampleSize = hRatio;  
            }  
        }  
        op.inJustDecodeBounds = false; //ע�����һ��Ҫ����Ϊfalse����Ϊ�������ǽ�������Ϊtrue����ȡͼƬ�ߴ���  
        pic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return pic;
	}
	
	/**
	 * �����ص�XML�ļ����洢���ֻ�ָ����SDcardĿ¼��
	 * @param mInputStream ��Ҫ�������
	 * @return String ���ش洢��XML�ļ���·��
	 */
	public String WriteIntoFile(InputStream mInputStream){
		if(isSDcard()){
			try {
				FileOutputStream mOutputStream = new FileOutputStream(new File(getFileName()));
				int len = -1;
				byte[] bytes = new byte[2048];
				try {
					while((len = mInputStream.read(bytes)) != -1){
						mOutputStream.write(bytes, 0, len);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						if(mOutputStream != null){
							mOutputStream.close();
						}
						if(mInputStream != null){
							mInputStream.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getFileName();
		}
		return null;
	}
	
	/**
	 * ���SDcard�Ƿ����
	 * @return
	 */
	public boolean isSDcard(){
		 if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	/**
	 * ��ȡ��Ҫ�洢��XML�ļ���·��
	 * @return String ·��
	 */
	public String getFileName(){
		String path = Environment.getExternalStorageDirectory().getPath() +"/XMLFiles";
		File mFile = new File(path);
		if(!mFile.exists()){
			mFile.mkdirs();
		}
		return mFile.getPath() + "/xmlfile.xml";
	}
}
