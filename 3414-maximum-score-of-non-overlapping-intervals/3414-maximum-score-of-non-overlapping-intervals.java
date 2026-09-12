class Solution {
    // Helper class to map and hold individual interval data
    private static class Interval {
        int start, end, weight, id;
        Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }
    }

    // Memoized result tracker structure
    private static class Result {
        long weight;
        List<Integer> ids;

        Result(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    private Interval[] intervals;
    private Result[][] memo;
    private int n;

    public int[] maximumWeight(List<List<Integer>> inputIntervals) {
        n = inputIntervals.size();
        intervals = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            List<Integer> inter = inputIntervals.get(i);
            intervals[i] = new Interval(inter.get(0), inter.get(1), inter.get(2), i);
        }

        // Sort primarily by start time to make non-overlapping jumps sequential
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        memo = new Result[n][5];
        
        // Compute best configuration starting from index 0 picking up to 4 intervals
        Result finalResult = solve(0, 4);
        
        // Extract indices, sort them ascending as requested by standard presentation
        List<Integer> optimalIds = finalResult.ids;
        Collections.sort(optimalIds);
        
        int[] ans = new int[optimalIds.size()];
        for (int i = 0; i < optimalIds.size(); i++) {
            ans[i] = optimalIds.get(i);
        }
        return ans;
    }

    private Result solve(int i, int count) {
        if (i == n || count == 0) {
            return new Result(0, new ArrayList<>());
        }
        if (memo[i][count] != null) {
            return memo[i][count];
        }

        // Option 1: Skip current interval
        Result skipRes = solve(i + 1, count);

        // Option 2: Take current interval
        int nextIdx = findNextNonOverlapping(i);
        Result takeNextRes = solve(nextIdx, count - 1);
        
        long takeWeight = intervals[i].weight + takeNextRes.weight;
        List<Integer> takeList = new ArrayList<>();
        takeList.add(intervals[i].id);
        takeList.addAll(takeNextRes.ids);

        // Compare choices based on weight and lexicographical order
        Result best;
        if (takeWeight > skipRes.weight) {
            best = new Result(takeWeight, takeList);
        } else if (skipRes.weight > takeWeight) {
            best = new Result(skipRes.weight, skipRes.ids);
        } else {
            // Tie-breaker: Compare the lists lexicographically
            List<Integer> sortedTake = new ArrayList<>(takeList);
            List<Integer> sortedSkip = new ArrayList<>(skipRes.ids);
            Collections.sort(sortedTake);
            Collections.sort(sortedSkip);
            
            if (isLexicographicallySmaller(sortedTake, sortedSkip)) {
                best = new Result(takeWeight, takeList);
            } else {
                best = new Result(skipRes.weight, skipRes.ids);
            }
        }

        memo[i][count] = best;
        return best;
    }

    // Binary search to find the first interval whose start time > current interval's end time
    private int findNextNonOverlapping(int currIdx) {
        int target = intervals[currIdx].end;
        int low = currIdx + 1, high = n - 1;
        int ans = n;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid].start > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}
