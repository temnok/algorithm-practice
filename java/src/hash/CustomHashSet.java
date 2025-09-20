package hash;

public class CustomHashSet<T> {
	public Node [] nodes;

	public boolean contains(T val) {
		throw new UnsupportedOperationException("TODO");
	}

	public boolean add(T val) {
		throw new UnsupportedOperationException("TODO");
	}

	public boolean remove(T val) {
		throw new UnsupportedOperationException("TODO");
	}

	public static class Node<T> {
		Node prev;
		Node next;
		T val;
		int hashCode;

		Node(T val) {
			this.val = val;
			this.hashCode = val.hashCode();
		}
	}
}
