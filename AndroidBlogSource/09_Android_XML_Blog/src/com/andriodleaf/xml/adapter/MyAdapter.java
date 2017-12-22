package com.andriodleaf.xml.adapter;

import java.util.ArrayList;

import com.andriodleaf.xml.activity.MainActivity;
import com.andriodleaf.xml.activity.R;
import com.andriodleaf.xml.entity.Person;
import com.andriodleaf.xml.tools.Constants;
import com.andriodleaf.xml.tools.ImageCallBack;
import com.andriodleaf.xml.tools.MyAsynctask;
import com.andriodleaf.xml.tools.MyAsynctask.HttpDownloadedListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * �����չʾ����������
 * @author AndroidLeaf
 */
public class MyAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Person> mList;
	private Bitmap[] mBitmaps;
	
	public MyAdapter(Context mContext,ArrayList<Person> mList){
		this.mContext = mContext;
		this.mList = mList;
		mBitmaps = new Bitmap[mList.size()];
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		Person mPerson = mList.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
			mHolder = new ViewHolder();
			mHolder.mTextView_id = (TextView)convertView.findViewById(R.id.item_id);
			mHolder.mTextView_name = (TextView)convertView.findViewById(R.id.item_name);
			mHolder.mTextView_height = (TextView)convertView.findViewById(R.id.item_height);
			mHolder.mImageView_image = (ImageView)convertView.findViewById(R.id.item_image);
			//ΪImageview����TAG����Ϊÿһ��ImageView��Ψһ��ʶ
			mHolder.mImageView_image.setTag(mPerson.getImageUrl());
			convertView.setTag(mHolder);
		}else{
			mHolder = (ViewHolder)convertView.getTag();
		}
		mHolder.mTextView_id.setText(String.valueOf(mPerson.getId()));
		mHolder.mTextView_name.setText(mPerson.getUserName());
		mHolder.mTextView_height.setText(String.valueOf(mPerson.getHeight()));
		/**
		 * ����첽���ع�����Listview�б���ͼƬ��ʾ��λ����
		 */
		//�жϵ�ǰλ�õ�ImageView���Ƿ�Ϊ�ϴ�ִ�м��ز�����ImageView����false�������ϴμ��ص��Ǹ�Imageview�е�ͼƬ��Դ
		if(!mPerson.getImageUrl().equals(String.valueOf(mHolder.mImageView_image.getTag()))){
			mHolder.mImageView_image.setImageResource(R.drawable.ic_launcher);
		}
		//����ΪImageViewʵ������TAG
		mHolder.mImageView_image.setTag(mPerson.getImageUrl());
		if(mBitmaps[position] == null){
			//ִ���첽����ͼƬ����
			MainActivity.downloadData((HttpDownloadedListener)mContext, Constants.BASE_PATH + mPerson.getImageUrl(), -1, 
					mHolder.mImageView_image, new MyImageCallBack(position,mPerson.getImageUrl()), Constants.TYPE_STREAM);
		}else{
			mHolder.mImageView_image.setImageBitmap(mBitmaps[position]);
		}
		
		return convertView;
	}
	
	class ViewHolder{
		TextView mTextView_id;
		TextView mTextView_name;
		TextView mTextView_height;
		ImageView mImageView_image;
	}
	
	class MyImageCallBack implements ImageCallBack{
		int index = -1;
		String imageUrl = null;
		public MyImageCallBack(int index,String imageUrl){
			this.index = index;
			this.imageUrl = imageUrl;
		}
		
		@Override
		public void resultImage(ImageView mImageView, Bitmap mBitmap) {
			// TODO Auto-generated method stub
			//�жϵ�ǰ��ʾ��ImageView��URL�Ƿ�����Ҫ���ص�ͼƬImageView��URL��ͬ
			if(imageUrl.equals(String.valueOf(mImageView.getTag()))){
				mBitmaps[index] = mBitmap;
				mImageView.setImageBitmap(mBitmap);
			}
		}
	}
}
