package mellohi138.netherized.util;

public class MathUtil {
	/**
	 * Calculates the precentage of a value.
	 * <p>
	 * 1st value is the value you want to take the precentage of. (Example: 200)
	 * <p>
	 * 2nd value is how much precentage you want to use. (Example: %15)
	 */
	public static float calculatePrecentage(float precentageOf, float precentageVal) {
		return (precentageOf * precentageVal) / 100.0F;
	}
}
