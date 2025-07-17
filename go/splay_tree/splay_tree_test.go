package splay_tree

import (
	"github.com/temnok/algorithms-practice/go/set"
	"testing"
)

func TestRandomCases(t *testing.T) {
	set.GenericRandomCases(t, "SplayTree", NewSplayTree)
}
