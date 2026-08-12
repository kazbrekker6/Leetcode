class Solution {
    public int climbStairs(int n) {
        int[] a = new int[n+1];
        for(int i=0; i<a.length; i++){
            a[i] = -1;
        }
        return solve(n,a);
    }

    int solve(int n, int[] a){
        if(n==1 || n==2) return n;

        if(a[n] != -1){
            return a[n];
        }
        a[n] = solve(n-1 , a) + solve(n-2, a);
        return a[n];
    }
}