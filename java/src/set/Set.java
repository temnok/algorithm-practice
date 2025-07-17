package set;

public interface Set {
	// add should insert given val into this set
	// and return true if list did not contain val previously,
	// or do nothing and return false if val is already present
	boolean add(int val);

	// contains should return true if val is present in the set
	boolean contains(int val);

	// remove should delete val in the set and return true if val was present
	// or do nothing and return false otherwise
	boolean remove(int val);
}
