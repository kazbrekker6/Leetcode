class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        // A valid path has exactly (m + n - 1) characters. 
        // If the path length is odd, it can never form balanced parentheses.
        if ((m + n - 1) % 2 != 0) return false;
        
        // The maximum possible open balance can't exceed the path length
        int maxBalance = m + n; 
        memo = new Boolean[m][n][maxBalance];
        
        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int bal) {
        // Update balance based on current cell character
        if (grid[r][c] == '(') {
            bal++;
        } else {
            bal--;
        }

        // If balance drops below 0, the prefix is invalid
        if (bal < 0) return false;
        
        // If we reach the bottom-right corner, check if balance is perfectly 0
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        // Return cached result if already calculated
        if (memo[r][c][bal] != null) {
            return memo[r][c][bal];
        }

        boolean right = false;
        boolean down = false;

        // Move Right
        if (c + 1 < n) {
            right = dfs(grid, r, c + 1, bal);
        }
        
        // Move Down
        if (r + 1 < m) {
            down = dfs(grid, r + 1, c, bal);
        }

        // Cache and return the result
        return memo[r][c][bal] = (right || down);
    }
}
