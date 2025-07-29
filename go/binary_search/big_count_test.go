package binary_search

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

type Array1 struct{}
type Array1000 struct{}

const count = 1_000_000_000_000_000_000

func (*Array1) len() int {
	return count
}

func (*Array1) get(i int) int {
	if i < 0 || i >= count {
		panic("IndexOutOfBoundsException")
	}

	return 0
}

func (*Array1000) len() int {
	return count
}

func (*Array1000) get(i int) int {
	if i < 0 || i >= count {
		panic("IndexOutOfBoundsException")
	}

	return i / (count / 1000)
}

func TestBigCount1(t *testing.T) {
	arr := &Array1{}
	assert.Equal(t, 0, BigCount(arr, -1))
	assert.Equal(t, count, BigCount(arr, 0))
	assert.Equal(t, 0, BigCount(arr, 1))
}

func TestBigCount1000(t *testing.T) {
	arr := &Array1000{}
	assert.Equal(t, 0, BigCount(arr, -1))
	assert.Equal(t, count/1000, BigCount(arr, 0))
	assert.Equal(t, count/1000, BigCount(arr, 500))
	assert.Equal(t, count/1000, BigCount(arr, 999))
	assert.Equal(t, 0, BigCount(arr, 1000))
}
