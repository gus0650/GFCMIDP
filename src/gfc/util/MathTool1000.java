package gfc.util;

/**
 */
public class MathTool1000 {
	
	/*
	 * values for SINUS1000[] were generated using this code: for (double i = 0;
	 * i < 360; i+= 10) { double s = Math.sin( Math.toRadians(i) ) *1000;
	 * System.out.print( (int)s+", " ); }
	 */
	public static int sin1000(int angle) {
		final int[] SINUS1000 = { 0, 17, 34, 52, 69, 87, 104, 121, 139, 156,
				173, 190, 207, 224, 241, 258, 275, 292, 309, 325, 342, 358,
				374, 390, 406, 422, 438, 453, 469, 484, 499, 515, 529, 544,
				559, 573, 587, 601, 615, 629, 642, 656, 669, 681, 694, 707,
				719, 731, 743, 754, 766, 777, 788, 798, 809, 819, 829, 838,
				848, 857, 866, 874, 882, 891, 898, 906, 913, 920, 927, 933,
				939, 945, 951, 956, 961, 965, 970, 974, 978, 981, 984, 987,
				990, 992, 994, 996, 997, 998, 999, 999, 1000 };

		angle %= 360; //angles > 360 => start over from 0
		if (angle < 0)
			angle += 360; //no negative angles

		if (angle <= 90) {
			//0..90 degrees
			return SINUS1000[angle];
		} else if (angle <= 180) {
			// 90..180 degrees
			return SINUS1000[180 - angle];
		} else if (angle <= 270) {
			// 180..270 degrees
			return -SINUS1000[angle - 180];
		} else {
			// 270..360 degrees
			return -SINUS1000[360 - angle];
		}
	}

	public static int cos1000(int angle) {
		return sin1000(angle + 90);
	}
	
	/*
	 * values for TAN1000[] were generated using this code: for (double i = 0; i <
	 * 90; i++) { double s = Math.tan( Math.toRadians(i) ) *1000;
	 * System.out.print( (int)s+", " ); } }
	 */
	public static int tan1000(int angle) {
		final int[] TAN1000 = { 0, 17, 34, 52, 69, 87, 105, 122, 140, 158, 176,
				194, 212, 230, 249, 267, 286, 305, 324, 344, 363, 383, 404,
				424, 445, 466, 487, 509, 531, 554, 577, 600, 624, 649, 674,
				700, 726, 753, 781, 809, 839, 869, 900, 932, 965, 999, 1035,
				1072, 1110, 1150, 1191, 1234, 1279, 1327, 1376, 1428, 1482,
				1539, 1600, 1664, 1732, 1804, 1880, 1962, 2050, 2144, 2246,
				2355, 2475, 2605, 2747, 2904, 3077, 3270, 3487, 3732, 4010,
				4331, 4704, 5144, 5671, 6313, 7115, 8144, 9514, 11430, 14300,
				19081, 28636, 57289, 0 };

		angle %= 360;

		if (angle <= 90) {
			//0..90 degrees
			return TAN1000[angle];
		} else if (angle <= 180) {
			// 90..180 degrees
			return -TAN1000[180 - angle];
		} else if (angle <= 270) {
			// 180..270 degrees
			return TAN1000[angle - 180];
		} else {
			// 270..360 degrees
			return -TAN1000[360 - angle];
		}
	}
}
