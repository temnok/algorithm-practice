package tree

import "testing"

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData(100)
		for range 100 {
			rotate(td.randomNode(t))
			td.assertNodes(t)
		}
	}
}
