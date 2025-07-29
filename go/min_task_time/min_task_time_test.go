package min_task_time

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestExample(t *testing.T) {
	assert.Equal(t, 120, MinTaskTime(5,
		[]int{20, 10, 30, 50, 40},
		[][]int{{3, 4}, {2, 3}, {1, 0}, {2, 1}, {3, 1}, {3, 0}}))
}

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()
		actual := MinTaskTime(td.n, td.time, td.before)
		if td.expected != actual {
			assert.FailNowf(t, "", "minTaskTime(%v, %v, %v):\nwant %v\n got %v",
				td.n, td.time, td.before, td.expected, actual)
		}
	}
}
