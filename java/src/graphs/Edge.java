package graphs;

class Edge {
	public final int u, v;
	public final double w;

	public Edge(int u, int v, double w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	@Override
	public String toString() {
		return String.format("(%s->%s:%s)", u, v, w);
	}
}
