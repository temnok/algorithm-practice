from kruskal import minimum_spanning_forest
from mst_random_test_data import RandomTestData


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()
		got = minimum_spanning_forest(td.n, td.edges.copy())
		td.assert_answer(got)
