class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (i == 0 || j == n - 1) {
                    row.append('.');
                } else {
                    row.append('#');
                }
            }

            ans[i] = row.toString();
        }

        return ans;
    }
}