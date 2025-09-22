package tree;

import java.util.stream.IntStream;

public class Tree {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		TreeNode root = buildNode(nums, 0 , nums.length);
		return root;
	}

	TreeNode buildNode(int nums[], int l, int r) {
		if (r - l < 4) {
			int m = (l + r)/2;

			TreeNode root = new TreeNode(nums[m]);
			if (m - 1 == l) {
				root.left = new TreeNode(nums[m - 1]);
			}
			if (m + 1 == r - 1) {
				root.right = new TreeNode(nums[m + 1]);
			}

			return root;
		}

		int m = (l + r)/2;
		TreeNode root = new TreeNode(nums[m]);
		root.left = buildNode(nums, l, m);
		root.right = buildNode(nums, m + 1, r);

		return root;
	}

	public static void main(String[] args) {
		int n [] = new int[] {-10,-3,0,5,9};
		TreeNode root = new Tree().sortedArrayToBST(n);

		System.out.println();
	}
}
