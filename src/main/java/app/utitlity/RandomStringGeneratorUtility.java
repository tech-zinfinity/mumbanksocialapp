package app.utitlity;

import java.util.Random;

public interface RandomStringGeneratorUtility {

	public static int generatRandonString() {
	    return new Random().nextInt();
	}
}
