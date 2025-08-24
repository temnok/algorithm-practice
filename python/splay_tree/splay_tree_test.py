from ..set.set import generic_random_cases
from splay_tree import SplayTree


def test_random_cases():
	generic_random_cases("SplayTree", lambda: SplayTree())
