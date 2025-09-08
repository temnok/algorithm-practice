
from count import count_ints

count = 1_000_000_000_000_000_000


class Array1:
	def __len__(self):
		return count

	def __getitem__(self, i):
		if not 0 <= i < count:
			raise IndexError()

		return 0


class Array1000:
	def __len__(self):
		return count

	def __getitem__(self, i):
		if not 0 <= i < count:
			raise IndexError()

		return i // (count // 1000)


def test_big_count1():
	arr = Array1()
	assert count_ints(arr, -1) == 0
	assert count_ints(arr, 0) == count
	assert count_ints(arr, 1) == 0


def test_big_count1000():
	arr = Array1000()
	assert count_ints(arr, -1) == 0
	assert count_ints(arr, 0) == count//1000
	assert count_ints(arr, 500) == count//1000
	assert count_ints(arr, 999) == count//1000
	assert count_ints(arr, 1000) == 0
