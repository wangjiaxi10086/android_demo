package com.androidleaf.test;

public class AccelerateDecelerateTest {

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
			float interpolatorFactor = AccelerateDecelerateTest.getInterpolation(timeFactor);
			System.out.println("��ֵ���� = " + interpolatorFactor);
			//����ֵ
			float propertyValue = evaluate(interpolatorFactor, 0, 40);
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
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
	
	/**
	  * ���㵱ǰ������ֵ
	  * @param fraction ��ֵ����
	  * @param startValue ������ʼֵ
	  * @param endValue ���Խ���ֵ
	  * @return Integer ��ǰ������ֵ
	  */
	public static Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }
	
}


