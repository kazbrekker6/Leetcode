class Solution {
    class Deal{
        int cost;
        int deals;

        Deal(int cost , int deals){
            this.cost = cost;
            this.deals = deals;
        }
    }
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] count = new int[n+1];

        long minCost = Long.MAX_VALUE;

        for(int[] item : items){
            int factor = item[0];
            int cost = item[1];

            count[factor]++;

            minCost = Math.min(minCost , cost);
        }

        int[] temp = new int[n+1];
        for(int factor = 1; factor <= n; factor++){
            if(count[factor] == 0){
                continue;
            }
            int deals = -1;
            for(int multiple = factor; multiple <= n; multiple += factor){
                deals += count[multiple];
            }
            temp[factor] = deals;
        }

        long limit = 2 * minCost;
        List<Deal> dealList = new ArrayList<>();

        for(int[] item : items){
            int factor = item[0];
            int cost = item[1];

            int deals = temp[factor];
            if(cost < limit && deals > 0){
                dealList.add(new Deal(cost, deals));
            }
        }
        Collections.sort(dealList, (a,b) -> Integer.compare(a.cost,b.cost));
        long answer = 0;

        for(Deal current : dealList){
            long take = Math.min((long) current.deals, budget/current.cost);

            answer += 2L * take;
            budget -= take * current.cost;
        }

        answer += budget/minCost;
        return (int) answer;
    }
}   