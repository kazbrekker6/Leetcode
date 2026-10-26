class Solution {
    int[][] grid;
    int rows,cols;

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        int count = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] > 0){
                    long sum = dfs(i,j);
                    if(sum % k == 0) count++;
                }
            }
        }
        return count;
    }

    private long dfs(int i, int j){
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

        long sum = grid[i][j];
        grid[i][j] = 0;

        for(int[] dir: directions){
            int i_ = i + dir[0];
            int j_ = j + dir[1];
            if(i_ >= 0 && i_ < rows && j_ >= 0 && j_ < cols && grid[i_][j_] > 0){
                sum += dfs(i_,j_);
            }
        }  
        return sum;
    }
} 