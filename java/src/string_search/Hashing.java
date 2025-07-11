package string_search;

public class Hashing {
	// 0, 1, 2, 3, 4 - 5
	// 3, 4 - 2, size 4 = 5 - 2 + 1;
	// 0, 1, 2, 3
	// 1, 2     + 2 = 3

	public static long[] getHashes(String str, int size) {
		char[] source = str.toCharArray();
		long [] hashes = new long[str.length() - size + 1];

		long hash = 0;
		int base = 31;
		long pow_base = 1;

		for (int i = size - 1; i >= 0; i--) {
			hash = hash + source[i] * pow_base;
			pow_base = pow_base * base;
		}

		hashes[0] = hash;
		for (int i = 1; i < hashes.length; i++) {
			hash = hash * base + source[i + size - 1];
			hash -= source[i - 1]*pow_base;
			hashes[i] = hash;
		}

		return hashes;
	}

	public static int search(String source, String substring) {
		long hashes[] = getHashes(source, substring.length());
		long hash = getHashes(substring, substring.length())[0];

		for (int i = 0; i < hashes.length; i++) {
			if (hashes[i] == hash) {
				return i;
			}
		}

		return -1;
	}
}
