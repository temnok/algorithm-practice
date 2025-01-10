package binary_search

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

type arrWrapper struct {
	t         *testing.T
	len       int
	callCount int
	get       func(int) int
}

func (a *arrWrapper) checkCallCount() {
	if a.callCount++; 1<<(a.callCount/100) > a.len {
		a.t.Fatalf("Too many array accesses: %v", a.callCount)
	}
}

func (a *arrWrapper) Len() int {
	a.checkCallCount()

	return a.len
}

func (a *arrWrapper) Get(i int) int {
	a.checkCallCount()

	if i < 0 || i >= a.len {
		a.t.Fatalf("Array index out of range: %v", i)
	}

	return a.get(i)
}

func TestCountInts_VeryLargeArray(t *testing.T) {
	const arrLen = 1_000_000_000_000_000_000

	arr := &arrWrapper{
		t:   t,
		len: arrLen,
		get: func(i int) int {
			return i / (arrLen / 1000)
		},
	}

	assert.Equal(t, 0, CountInts(arr, -1))
	assert.Equal(t, arrLen/1000, CountInts(arr, 0))
	assert.Equal(t, arrLen/1000, CountInts(arr, 500))
	assert.Equal(t, arrLen/1000, CountInts(arr, 999))
	assert.Equal(t, 0, CountInts(arr, 1000))
}
