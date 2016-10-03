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
	
	public static double RandomBetweenGaussian(double n, double m){
		return ran.nextGaussian()*(m-n+1) + n;
	}
	
}
