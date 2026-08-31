/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstCPI = -1;
        int prevCPI = -1;
        int currentIndex = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        int res[] = new int[2];
        res[0] = Integer.MAX_VALUE;

        while(curr.next != null){
            ListNode nextNode = curr.next;
            if((curr.val < nextNode.val && curr.val < prev.val) ||
            (curr.val > nextNode.val && curr.val > prev.val)){
                if(prevCPI == -1){
                    firstCPI = currentIndex;
                    prevCPI = currentIndex;
                }
                else{
                    res[0] = Math.min(res[0], currentIndex-prevCPI);
                    prevCPI = currentIndex;
                }
            }
            prev = prev.next;
            curr = curr.next;
            currentIndex++;
        }
        if(firstCPI != -1 && res[0] != Integer.MAX_VALUE){
            res[1] = prevCPI - firstCPI;
        }
        else{
            res[0] = -1;
            res[1] = -1;
        }      
        return res;
    }
}