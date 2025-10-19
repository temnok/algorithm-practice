package sliding_window

import "math"

// SlidingWindowMin should return minimum for each position of
// the sliding window of length n on array arr. For example,
// if arr = [-1, 2, 1, 0, 5, -10] and n = 3,
// then result should be [-1, 0, 0, -10]
//
// Constrains:
//   - n >= 1
//   - len(arr) >= n
//   - arr can contain any integer
func SlidingWindowMin(arr []int, n int) []int {
	ans := make([]int, len(arr)-n+1)

	var q MinQueue

	for i := range n - 1 {
		q.add(arr[i])
	}

	for i := range ans {
		q.add(arr[i+n-1])
		ans[i] = q.min()
		q.remove()
	}

	return ans
}

type MinStack struct {
	stack [][2]int
}

func (s *MinStack) isEmpty() bool {
	return len(s.stack) == 0
}

func (s *MinStack) min() int {
	if sp := len(s.stack) - 1; sp >= 0 {
		return s.stack[sp][1]
	}

	return math.MaxInt
}

func (s *MinStack) push(val int) {
	s.stack = append(s.stack, [2]int{val, min(val, s.min())})
}

func (s *MinStack) pop() int {
	sp := len(s.stack) - 1
	vals := s.stack[sp]
	s.stack = s.stack[:sp]
	return vals[0]
}

type MinQueue struct {
	l, r MinStack
}

func (q *MinQueue) min() int {
	return min(q.l.min(), q.r.min())
}

func (q *MinQueue) add(val int) {
	q.r.push(val)
}

func (q *MinQueue) remove() int {
	if q.l.isEmpty() {
		for !q.r.isEmpty() {
			q.l.push(q.r.pop())
		}
	}

	return q.l.pop()
}
