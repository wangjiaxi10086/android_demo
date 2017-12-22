package com.androidleaf.audiorecord;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.todoroo.aacenc.AACEncoder;
import com.todoroo.aacenc.AACToM4A;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.util.Log;

public class AudioRecordUtils {

	private final int audioSource = MediaRecorder.AudioSource.MIC;
	// ������Ƶ�����ʣ�44100��Ŀǰ�ı�׼������ĳЩ�豸��Ȼ֧��22050,16000,11025
    private final int sampleRateInHz = 16000;
    // ������Ƶ��¼�Ƶ�����CHANNEL_IN_STEREOΪ˫������CHANNEL_CONFIGURATION_MONOΪ������
    private final int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
    // ��Ƶ���ݸ�ʽ:PCM 16λÿ����������֤�豸֧�֡�PCM 8λÿ����������һ���ܵõ��豸֧�֡�
    private final int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    
    private int inBufSize = 0;
	
	private AudioRecord audioRecord;
	
	private AACEncoder encoder = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private boolean isRecord = false;
	
	private Context mContext;
	/**
	 * ¼�Ƶ���Ƶ�ļ�����
	 */
	private String mAudioRecordFileName;
	
	private static final int RECORDED_INIT_DELETE = 0;
	
	private static final int RECORDED_COMPLETED_DELETE = 1;
	
	
	public AudioRecordUtils(Context context,String audioRecordFileName){
		mContext = context;
		mAudioRecordFileName = audioRecordFileName;
		initAudioRecord();
	}
    
	/**
	 * ��ʼ������
	 */
    private void initAudioRecord(){
		
		inBufSize = AudioRecord.getMinBufferSize(
				sampleRateInHz, 
				channelConfig, 
				audioFormat);
		
		audioRecord  = new AudioRecord(
				audioSource, 
				sampleRateInHz, 
				channelConfig, 
				audioFormat, 
				inBufSize);
		
		encoder = new AACEncoder();
		deleteAllFiles(RECORDED_INIT_DELETE);
		
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setTitle("��ʾ");
		mProgressDialog.setMessage("���ڱ���¼���������ĵȺ�......");
		
	}
    
    /**
	 * ��ʼ¼��
	 */
	public void startRecord(){
		new AudioRecordTask().execute();
	}
	
	/**
	 * ��ͣ¼��
	 */
	public void pauseRecord(){
		isRecord = false;
	}
	
	/**
	 * ֹͣ¼��
	 */
	public void stopRecord(){
		new AudioEncoderTask().execute();
	}
	
	/**
	 * ����¼��
	 */
	public void reRecord(){
		//����¼��ʱ��ɾ��¼���ļ����е�ȫ���ļ�
		deleteAllFiles(RECORDED_INIT_DELETE);
	} 
	
	private void encodeAudio(){
		try {
			//��ȡ¼�Ƶ�pcm��Ƶ�ļ�
	    	DataInputStream mDataInputStream = new DataInputStream(new FileInputStream(
	    			FileUtils.getPcmFilePath(mAudioRecordFileName)));
				byte[] b = new byte[(int) new File(FileUtils.
						getPcmFilePath(mAudioRecordFileName)).length()];
				mDataInputStream.read(b);
				//��ʼ����������
				encoder.init(32000, 2, sampleRateInHz, 16, FileUtils.
						getAAcFilePath(mAudioRecordFileName));
				//�Զ����ƴ�����б���
		        encoder.encode(b);
		        //�������
		        encoder.uninit();
		        //�ر���
		        mDataInputStream.close();
		        try {
		        	//��aac�ļ�ת���m4a�ļ�
		            new AACToM4A().convert(mContext, FileUtils.getAAcFilePath(mAudioRecordFileName), 
		            		FileUtils.getM4aFilePath(mAudioRecordFileName));
		        } catch (IOException e) {
		            Log.e("ERROR", "error converting", e);
		        }
		        deleteAllFiles(RECORDED_COMPLETED_DELETE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	class AudioRecordTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(audioRecord == null){
				initAudioRecord();
			}
			RandomAccessFile mRandomAccessFile = null;
			try {
				mRandomAccessFile = new RandomAccessFile(new File(
						FileUtils.getPcmFilePath(mAudioRecordFileName)), "rw");
				byte[] b = new byte[inBufSize/4];
				//��ʼ¼����Ƶ
				audioRecord.startRecording();
				//�ж��Ƿ�����¼��
				isRecord = true;
				while(isRecord){
					audioRecord.read(b, 0, b.length);
					//���ļ���׷������
					mRandomAccessFile.seek(mRandomAccessFile.length());
					mRandomAccessFile.write(b, 0, b.length);
				}
				//ֹͣ¼��
				audioRecord.stop();
				mRandomAccessFile.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	class AudioEncoderTask extends AsyncTask<Void, Void, Long>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if(mProgressDialog != null && !mProgressDialog.isShowing()){
				mProgressDialog.show();
			}
		}

		@Override
		protected Long doInBackground(Void... params) {
			// TODO Auto-generated method stub
			encodeAudio();
			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(mProgressDialog.isShowing()){
				mProgressDialog.cancel();
				mProgressDialog.dismiss();
			}
		}
	}
	
	/**
	 * �����Ƶ¼���ļ����е������ļ�
	 * @param isRecorded
	 */
	public void deleteAllFiles(int isRecorded){
		 File[] files = new File(FileUtils.getAudioRecordFilePath()).listFiles();
		switch (isRecorded) {
		case RECORDED_INIT_DELETE:
			for(File file: files){
	        	file.delete();
	        }
			break;
		case RECORDED_COMPLETED_DELETE:
	        for(File file: files){
	        	if(!file.getName().equals(mAudioRecordFileName + Constants.M4A_SUFFIX)){
	        		file.delete();
	        	}
	        }
			break;
		default:
			break;
		}
	}
}
