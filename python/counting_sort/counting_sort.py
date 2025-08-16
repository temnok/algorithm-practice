# counting_sort should sort nums array using *counting* sort.
#
# Restrictions:
# - nums array is not empty
# - num in nums can be any integer, including negative values
# - difference between maximum and minimum value in arr won't exceed 1000
def counting_sort(nums: list[int]):
	# raise NotImplementedError('TODO')

	mi, ma = nums[0], nums[0]
	for num in nums:
		mi, ma = min(mi, num), max(ma, num)

	counts = [0] * (ma - mi + 1)
	for num in nums:
		counts[num - mi] += 1

	i = 0
	for j, c in enumerate(counts):
		num = mi + j
		while c > 0:
			nums[i] = num
			i += 1
			c -= 1

