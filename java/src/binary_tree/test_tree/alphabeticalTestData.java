package binary_tree.test_tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class alphabeticalTestData {
	public static List<String> alphabet = IntStream.rangeClosed('a', 'z').mapToObj(var -> String.valueOf((char) var))
		.collect(Collectors.toList());


	public static List<String> getRangeInclusive(int start, int end) {
		return new LinkedList<>(alphabet.subList(start, end + 1));
	}
}