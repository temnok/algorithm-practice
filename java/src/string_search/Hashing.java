package string_search;

public class Hashing {
	public static int search(String source, String substring) {
		char sourceArray[] = source.toCharArray();
		char substringArray[] = substring.toCharArray();

		int base = 31;
		long pow_base = 1;
		long sourceHash = 0;
		long substringHash = 0;

		for (int i = substring.length() - 1; i >= 0; i--) {
			sourceHash = toInt(sourceArray[i]) * pow_base;
			substringHash = toInt(substringArray[i]) * pow_base;
			pow_base = pow_base * base;
		}

		for (int i = 0; i < sourceArray.length - substringArray.length; i++) {
			if (sourceHash == substringHash) {
				return i;
			}

			sourceHash = sourceHash * base;
			sourceHash -= toInt(sourceArray[i])* pow_base;
			sourceHash += sourceArray[i + substringArray.length];
		}

		return -1;
	}

	public static int toInt(char c) {
		return c + 1;
	}
}
