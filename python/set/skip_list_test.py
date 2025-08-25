from set import generic_random_cases
from skip_list import SkipList


def test_random_cases():
	generic_random_cases("SkipList", lambda: SkipList())
