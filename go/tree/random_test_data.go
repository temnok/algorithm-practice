package tree

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"testing"
)

type randomTestData struct {
	nodes, referenceNodes []*Node
	referenceMap          map[*Node]*Node
	snapshot              string
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData(maxNodeCount int) *randomTestData {
	td := &randomTestData{
		referenceMap: map[*Node]*Node{},
	}

	n := 1 + random.IntN(maxNodeCount)
	td.nodes = make([]*Node, n)
	td.referenceNodes = make([]*Node, n)

	for i := range n {
		td.nodes[i] = &Node{val: i}
		td.referenceNodes[i] = &Node{val: i}
		td.referenceMap[td.referenceNodes[i]] = td.nodes[i]

		if i > 0 {
			td.nodes[i-1].parent = td.nodes[i]
			td.nodes[i].left = td.nodes[i-1]

			td.referenceNodes[i-1].parent = td.referenceNodes[i]
			td.referenceNodes[i].left = td.referenceNodes[i-1]
		}
	}

	return td
}

func (td *randomTestData) randomNode(t *testing.T) *Node {
	td.snapshot = toSnapshotT(t, td.nodes)

	i := random.IntN(len(td.nodes))
	c := td.referenceNodes[i]
	p := c.parent
	if p != nil {
		g := p.parent
		if g != nil {
			if p == g.left {
				g.left = c
			} else {
				g.right = c
			}
		}
		c.parent = g
		p.parent = c
		if c == p.left {
			p.left = c.right
			if p.left != nil {
				p.left.parent = p
			}
			c.right = p
		} else {
			p.right = c.left
			if p.right != nil {
				p.right.parent = p
			}
			c.left = p
		}
	}
	return td.nodes[i]
}

func (td *randomTestData) assertNodes(t *testing.T) {
	actual := toSnapshotT(t, td.nodes)
	expected := toSnapshotT(t, td.referenceNodes)
	assert.Equal(t, expected, actual, "State before rotation: "+td.snapshot)

	for i, n := range td.nodes {
		var r = td.referenceNodes[i]
		assert.Same(t, td.referenceMap[r], n)
		assert.Same(t, td.referenceMap[r.left], n.left)
		assert.Same(t, td.referenceMap[r.right], n.right)
		assert.Same(t, td.referenceMap[r.parent], n.parent)
	}
}

func toSnapshotT(t *testing.T, nodes []*Node) string {
	var root *Node
	for _, node := range nodes {
		if node.parent == nil {
			if root != nil {
				assert.FailNow(t, "More than one root node found")
			}
			root = node
		}
	}
	if root == nil {
		assert.FailNow(t, "No root node found")
	}
	var buf []byte
	toSnapshot(root, &buf)
	return string(buf)
}

func toSnapshot(c *Node, buf *[]byte) {
	if c == nil {
		return
	}
	*buf = append(*buf, '(')
	toSnapshot(c.left, buf)
	*buf = append(*buf, fmt.Sprint(c.val)...)
	toSnapshot(c.right, buf)
	*buf = append(*buf, ')')
}
