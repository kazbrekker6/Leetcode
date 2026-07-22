class Solution {
    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            if (n == 0) return;
            this.tree = new int[4 * n];
            build(arr, 1, 0, n - 1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = start + (end - start) / 2;
            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }

        public int query(int l, int r) {
            if (l > r || n == 0) return 0;
            return query(1, 0, n - 1, l, r);
        }

        private int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];
            int mid = start + (end - start) / 2;
            return Math.max(query(2 * node, start, mid, l, r), query(2 * node + 1, mid + 1, end, l, r));
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int initialOnes = 0;
        for(char ch : s.toCharArray()){
            if(ch == '1'){
                initialOnes++;
            }
        }

        List<Integer> zeroBlockLength = new ArrayList<> ();
        List<Integer> zeroBlockStart = new ArrayList<> ();
        List<Integer> zeroBlockEnd = new ArrayList<> ();

        int i = 0;

        while(i < n){
            int start = i;

            while(i < n && s.charAt(i) == s.charAt(start)){
                i++;
            }

            if(s.charAt(start) == '0'){
                zeroBlockLength.add(i - start);
                zeroBlockStart.add(start);
                zeroBlockEnd.add(i-1);
            }
        }

        int blockCount = zeroBlockLength.size();

        if(blockCount < 2){
            List<Integer> answer = new ArrayList<> ();

            for(int[] query : queries){
                answer.add(initialOnes);
            }
            return answer;
        }

        int[] pairSum = new int[blockCount - 1];
        for(int j = 0; j < blockCount - 1; j++){
            pairSum[j] = zeroBlockLength.get(j) + zeroBlockLength.get(j+1);
        }

        SegmentTree segmentTree = new SegmentTree(pairSum);

        List<Integer> answer = new ArrayList<> ();

        for(int[] query : queries){
            int left = query[0];
            int right = query[1];

            int firstBlock = lowerBound(zeroBlockEnd , left);
            int lastBlock = upperBound(zeroBlockStart , right) - 1;

            // less than 2 zero blocks inside substring
            if(firstBlock > blockCount-1 || lastBlock < 0 || firstBlock >= lastBlock){
                answer.add(initialOnes);
                continue;
            }
            // effective length of first zero block inside substring
            int firstLength = zeroBlockEnd.get(firstBlock) - Math.max(zeroBlockStart.get(firstBlock),left) + 1;
            // effective length of last zero block inside substring
            int lastLength =  Math.min(zeroBlockEnd.get(lastBlock),right) - zeroBlockStart.get(lastBlock) + 1;

            // exactly two zero blocks
            if(firstBlock + 1 == lastBlock){
                int bestGain = firstLength + lastLength;
                answer.add(initialOnes + bestGain);
                continue;
            }

            int option1 = firstLength + zeroBlockLength.get(firstBlock + 1);
            int option2 = zeroBlockLength.get(lastBlock - 1) + lastLength;
            int option3 = segmentTree.query(firstBlock + 1 , lastBlock - 2);
            int bestGain = Math.max(option1, Math.max(option2 , option3));

            answer.add(initialOnes + bestGain);
        }
        return answer;
    }

    private int lowerBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size();

        while(left < right){
            int mid = left +(right - left)/2;

            if(list.get(mid) < target){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }

    private int upperBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size();

        while(left < right){
            int mid = left +(right - left)/2;

            if(list.get(mid) <= target){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
        }
}
