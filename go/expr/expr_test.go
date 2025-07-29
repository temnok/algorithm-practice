package expr

import (
	"fmt"
	"math/rand/v2"
	"testing"
)

var random = rand.New(rand.NewPCG(0, 0))

func TestRandomCases(t *testing.T) {
	for test := 0; test < 100_000; test++ {
		expr, want, _ := genExpr(random.IntN(10))
		got := EvaluateExpr(expr)
		if got != want {
			t.Fatalf("EvaluateExpr(%v):\nwant %v\n got %v", expr, want, got)
		}
	}
}

func genExpr(depth int) (expr string, val int, add bool) {
	if depth == 0 {
		v := random.IntN(10)
		return fmt.Sprint(v), v, false
	}

	n := 1 + random.IntN(5)
	add = random.IntN(2) == 0

	for i := 0; i < n; i++ {
		e, v, a := genExpr(random.IntN(depth))
		if a && !add || random.IntN(10) == 0 {
			e = "(" + e + ")"
		}

		if i == 0 {
			expr, val = e, v
			continue
		}

		if op := random.IntN(2); add {
			if op == 0 {
				expr += "+" + e
				val += v
			} else {
				expr += "-(" + e + ")"
				val -= v
			}
		} else {
			if op == 0 {
				expr += "*(" + e + ")"
				val *= v
			} else {
				expr += "/(" + e + ")"
				if v != 0 {
					val /= v
				} else {
					val = 0
				}
			}
		}
	}

	return expr, val, add
}
