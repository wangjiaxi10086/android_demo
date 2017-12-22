package com.fendou.aidl;

import android.os.Parcel;
import android.os.Parcelable;

//<1>ʵ��Parceable�ӿ�
public class Person implements Parcelable {

	private int id;
	private String userName;
	private double height;
	
	public Person(){
		
	}
	//����˽�л��Ĺ��캯��������readFromParcel()����
	private Person(Parcel in)
	{
		readFromParcel(in);
	}
	//(3)�����ṩһ����ΪCREATOR��static final���� ��������Ҫʵ��android.os.Parcelable.Creator<T>�ӿ� 
	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Person(source);
		}

		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
	};
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ע�⣺writeToParcel()��readFromParcel()�Ա����Ķ�д˳����һ��
	 */
	//(2)��Person�����״̬д��Parcel��
	public void writeToParcel(Parcel out, int arg1) {
		// TODO Auto-generated method stub
		out.writeInt(id);
		out.writeString(userName);
		out.writeDouble(height);

	}
	//(2)��Parcel�ж�ȡPerson�����״̬
	public void readFromParcel(Parcel in){
		id = in.readInt();
		userName = in.readString();
		height = in.readDouble();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
