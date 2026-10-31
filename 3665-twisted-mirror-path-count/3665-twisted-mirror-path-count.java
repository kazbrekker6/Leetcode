class Solution {
    private static final long MOD = 1_000_000_007;
    private int m,n;
    private long[][] dp;

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        dp = new long[m][n];

        for(int i = 0; i < m; i++){
            Arrays.fill(dp[i], -1);
        }    
        return (int) func(grid, 0 , 0);
    } 

    public long func(int[][] grid, int i, int j){
        if(i >= m || j >= n) return 0;

        if(i == m-1 && j == n-1) return 1;

        if(dp[i][j] != -1) return dp[i][j];

        long cntPaths = 0;

        int x = i;
        int y = j + 1;
        int dir = 0;

        while(x < m && y < n && grid[x][y] == 1){
            if(dir == 0){
                x++;
                dir = 1;
            }
            else{
                y++;
                dir = 0;
            }
        }
        if(x < m && y < n){
            cntPaths += func(grid, x, y);
        }

        x = i + 1;
        y = j;
        dir = 1;

        while(x < m && y < n && grid[x][y] == 1){
            if(dir == 0){
                x++;
                dir = 1;
            }
            else{
                y++;
                dir = 0;
            }
        }
        if(x < m && y < n){
            cntPaths += func(grid, x ,y);
        }
        return dp[i][j] = cntPaths % MOD;
    }
}