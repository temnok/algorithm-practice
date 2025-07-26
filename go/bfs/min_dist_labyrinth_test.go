package bfs

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestMinDistLabyrinth(t *testing.T) {
	for range 100_000 {
		td := newRandomLabyrinthTestData()

		expected := td.minDist
		actual := MinDistLabyrinth(td.labyrinth)

		if expected != actual {
			assert.FailNowf(t, "",
				"MinDistLabyrinth(%v):\nwant %v\n got %v",
				td.labyrinth, expected, actual,
			)
		}
	}
}
