class Solution {
    int[][] grid;
    int rows, cols;
    Set<Integer> visit = new HashSet<>();

    public int closedIsland(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        int res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && !visit.contains(i * cols + j)) {
                    res += dfs(i, j);
                }
            }
        }
        return res;
    }

    public int dfs(int i, int j) {
        // Out of bounds → touches border → NOT closed
        if (i < 0 || i >= rows || j < 0 || j >= cols)
            return 0;

        // Land or already visited → safe
        if (grid[i][j] == 1 || visit.contains(i * cols + j))
            return 1;

        visit.add(i * cols + j);

        // All 4 directions must be closed (return 1)
        int up    = dfs(i - 1, j);
        int down  = dfs(i + 1, j);
        int left  = dfs(i, j - 1);
        int right = dfs(i, j + 1);

        return (up + down + left + right) == 4 ? 1 : 0;
    }
}   