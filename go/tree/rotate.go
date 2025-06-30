package tree

func rotate(c *Node) {
	p := c.parent
	if p == nil {
		return
	}
	g := p.parent

	var m *Node
	if c == p.left {
		m = c.right
		p.left = m
		c.right = p
	} else {
		m = c.left
		p.right = m
		c.left = p
	}

	if m != nil {
		m.parent = p
	}
	c.parent = g
	p.parent = c

	if g != nil {
		if p == g.left {
			g.left = c
		} else {
			g.right = c
		}
	}
}
