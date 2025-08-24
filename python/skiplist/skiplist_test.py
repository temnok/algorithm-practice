from ..set.set import generic_random_cases
from skiplist import Skiplist


def test_random_cases():
	generic_random_cases("Skiplist", lambda: Skiplist())
