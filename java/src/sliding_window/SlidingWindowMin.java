package sliding_window;

import java.util.Stack;

public class SlidingWindowMin {

	// slidingWindowMin should return minimum for each position of
	// the sliding window of length n on array arr. For example,
	// if arr = [-1, 2, 1, 0, 5, -10] and n = 3,
	// then result should be [-1, 0, 0, -10]
	//
	// Constrains:
	//   - n >= 1
	//   - len(arr) >= n
	//   - arr can contain any integer
	public static int[] slidingWindowMin(int[] arr, int n) {
		var q = new MinQueue();

		for (var i = 0; i < n-1; i++) {
			q.add(arr[i]);
		}

		var ans = new int[arr.length - n + 1];

		for (var i = 0; i < ans.length; i++) {
			q.add(arr[i+n-1]);
			ans[i] = q.min();
			q.remove();
		}

		return ans;
	}
}

class MinStack {
	Stack<int[]> stack = new Stack<>();

	boolean isEmpty() {
		return stack.isEmpty();
	}

	int min() {
		if (isEmpty()) {
			return Integer.MAX_VALUE;
		}

		return stack.lastElement()[1];
	}

	void push(int val) {
		stack.push(new int[] {val, Math.min(val, min())});
	}

	int pop() {
		return stack.pop()[0];
	}
}

class MinQueue {
	MinStack l = new MinStack(), r = new MinStack();

	int min() {
		return Math.min(l.min(), r.min());
	}

	void add(int val) {
		r.push(val);
	}

	int remove() {
		if (l.isEmpty()) {
			while (!r.isEmpty()) {
				l.push(r.pop());
			}
		}

		return l.pop();
	}
}
