package min_task_time;

public class MinTaskTime {
	// minTaskTime should return minimal time for completing n tasks where each task i takes time[i] > 0 to complete.
	// Tasks may have dependencies so one must be performed strictly before another.
	// Array 'before' contains pairs [u, v] meaning that task u should be done before v. Dependencies are
	// transitive so if 'before' array contains pairs [a, b] and [b, c], then task a should be done before task c.
	// If there is no dependencies before two tasks, they must be completed in parallel.
	//
 	// There will be no dependency cycles between the tasks so the answer should always be positive.
	//
 	// For example, for the following example
	//
	// n = 5, time = [20, 10, 30, 50, 40], before = [[3, 4], [2, 3], [1, 0], [2, 1], [3, 1], [3, 0]]
 	//
	// the result is 120:
	// task 2 must be done first (t=30), then task 3 (t=30+50=80), then tasks 4 and (1-then-0) can be done in parallel
	// (t=80+max(40, 10+20)=120)
 	//
  	//   ----------------------
	//  |       |             |
 	//  v       v             |
 	//  *<-----*<-----*----->*------>*
	//  0      1      2      3       4
	// t=20   t=10   t=30   t=50   t=40
	public static int minTaskTime(int n, int[] time, int[][] before) {
		throw new UnsupportedOperationException("TODO");
	}
}
