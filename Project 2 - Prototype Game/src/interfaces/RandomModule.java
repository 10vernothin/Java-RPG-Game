package interfaces;

import java.util.Random;

public class RandomModule {
	public static Random ran = new Random();
	
	public static int RandomBetween(int n, int m) {
		return ran.nextInt(m-n+1) + n;
	}
	
	public static double RandomBetween(double n, double m) {
		return ran.nextDouble()*(m-n+1) + n;
	}
	
	public static double RandomBetweenGaussian(double min, double max){
		if (min == 0 && max == 0){
			return 0;
		}
		if (min > max) {
			return -1;
			//error
		}
		double range = (ran.nextGaussian() + ((min+max)/2));
		while ((range > max) || (range < min)) {
			range = (ran.nextGaussian() + ((min+max)/2));
		}
		return range;
	}
	
	public static double RandomInDigits(int n) {
		//this returns 0 if n = 0;
		double m = 0;
		if (n == 0) {
			return m;
		}
		do {
			m = ran.nextDouble()*Math.pow(10, n);
		} while (m < Math.pow(10, n-1));
		return m;
	}
}
