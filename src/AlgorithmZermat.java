public class AlgorithmZermat {
	private final String ponderador = "212121212";
	private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private final String ALPHABET2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String value = "12345678912345678923456789";

	public Boolean processZermat(String reference) {
		String resOfMultiplication = "";
		String resOfMultiplicationS = "";
		String prefix = "";
		int i = 0;
		int res = 0;
		int res2 = 0;
		String vD = "";
		String stringVd = "";

		if (isNumeric(reference)) {
			if (reference.length() > 10) {
				return false;
			} else if (reference.length() < 10) {
				reference = createMask("0", reference, 10);
				vD = reference.charAt(9) + "";
				reference = reference.substring(0, 9);
			} else if (reference.length() == 10) {
				stringVd = reference.substring(Math.max(0, reference.length() - 1));
				reference = reference.substring(0, reference.length() - 1);
			}

			/*
			 * String stringPrefijo = reference.substring(Math.max(0, reference.length() -
			 * 2)); String SubCadenaB = reference.substring(0, 7); String referenceFinal=
			 * SubCadenaB +stringPrefijo;
			 */
			System.out.println("Reference: " + reference);
			for (i = 0; i < reference.length(); i++) {

				if (isNumber(Character.toString(reference.charAt(i)))) {

					res = Character.getNumericValue(ponderador.charAt(i))
							* Character.getNumericValue(reference.charAt(i));
					resOfMultiplication += res + "";
					resOfMultiplicationS += sumString(res + "") + "";

				} else {

					res2 = Character.getNumericValue(ponderador.charAt(i)) * letterValue(reference.charAt(i));
					prefix += reference.charAt(i) + "";
					resOfMultiplication += res2 + "";
					resOfMultiplicationS += sumString(res2 + "") + "";
				}
			}

			String suppressedTens = Character.toString(
					(sumString(resOfMultiplicationS) + "").charAt((sumString(resOfMultiplicationS) + "").length() - 1));

			int result = sumString(suppressedTens);

			System.out.println("Prefix: " + prefix);
			System.out.println("Multiplication result: " + resOfMultiplication);
			System.out.println("Multiplication result of the summed characters: " + resOfMultiplicationS);
			System.out.println("Final sum result: " + sumString(resOfMultiplicationS));
			System.out.println("The tens are suppressed: " + result);
			int digitVerify = 10 - result;
			String strDigitVerify = Integer.toString(digitVerify);
			System.out.println("Verification digit: " + strDigitVerify);
			if (digitVerify == 10) {
				digitVerify = 0;
				strDigitVerify = Integer.toString(digitVerify);
			}

			Boolean isValid = false;
			// System.out.println("Digito test: " + strDigitVerify);
			if (strDigitVerify.equals(stringVd) || strDigitVerify.equals(vD)) {
				isValid = true;
			}

			return isValid;
		} else {
			return false;
		}

	}

	public int letterValue(char character) {
		int number = -1;
		int i = 0;

		for (i = 0; i < ALPHABET.length() && number == -1; i++) {
			if (character == ALPHABET.charAt(i) || character == ALPHABET2.charAt(i)) {
				number = Character.getNumericValue(value.charAt(i));
			}
		}

		return number;
	}

	public static boolean isNumber(String character) {

		boolean isNumber = true;

		for (int i = 0; i < character.length(); i++) {
			if (!Character.isDigit(character.charAt(i))) {
				// Entonces devuelve false porque el string no contiene puros numbers
				isNumber = false;
				return isNumber;
			}
		}
		return isNumber;
	}

	public static int sumString(String toSum) {

		int result = 0;
		int i = 0;
		for (i = 0; i < toSum.length(); i++) {
			result += Character.getNumericValue(toSum.charAt(i));
		}

		return result;
	}

	public String createMask(String maskChar, String sufix, int length) {
		String finalMask = "";

		for (int a = 0; a < (length - sufix.toString().length()); a++) {
			finalMask = finalMask + maskChar;
		}

		finalMask = finalMask + sufix;

		return finalMask;
	}

	public static boolean isNumeric(String reference) {
		String stringPrefix = reference.substring(Math.max(0, reference.length() - 1));
		for (char c : stringPrefix.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

}