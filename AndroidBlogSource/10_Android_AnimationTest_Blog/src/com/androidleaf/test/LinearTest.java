package com.androidleaf.test;

public class LinearTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��ǰ��ʱ��
		float time = 10;
		//ʱ���������
		float timeFactor = 0.0f;
		for(int i = 0;i < 4;i++){
			System.out.println("ʱ�� = " + time +"ms");
			timeFactor = time/40;
			System.out.println("ʱ������ = "+ timeFactor);
			//��ֵ����
			float interpolatorFactor = LinearTest.getInterpolation(timeFactor);
			System.out.println("��ֵ���� = " + interpolatorFactor);
			//����ֵ
			int propertyValue = evaluate(interpolatorFactor, 0, 40);
			System.out.println("����ֵ = " + propertyValue);
			time += 10;
			System.out.println();
		}
		
	}
	
	/**
	 * �����ֵ����
	 * @param input ʱ���������
	 * @return float ���ز�ֵ����
	 */
	 public static float getInterpolation(float input) {
	        return input;
	 }
	
	 /**
	  * ���㵱ǰ������ֵ
	  * @param fraction ��ֵ����
	  * @param startValue ������ʼֵ
	  * @param endValue ���Խ���ֵ
	  * @return Integer ��ǰ������ֵ
	  */
	 public static Integer evaluate(float fraction, Integer startValue, Integer endValue) {
	        int startInt = startValue;
	        return (int)(startInt + fraction * (endValue - startInt));
	 }
	
}


