
from quick_sort import partition


# ith_min should return i-th minimal element in the array for average-linear time
# Constraints:
# - 0 <= i < arr.length
# - it's ok to change array in place
def ith_min(arr: list[int], k: int) -> int:
	# raise NotImplementedError('TODO')

	return ith_min_recurse(arr, k, 0, len(arr))


def ith_min_recurse(arr, k, l, r):
	i, j = partition(arr, l, r)

	if i <= k < j:
		return arr[k]

	if k < i:
		return ith_min_recurse(arr, k, l, i)
	else:
		return ith_min_recurse(arr, k, j, r)
