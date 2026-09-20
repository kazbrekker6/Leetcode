class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 1-based index in the string
            int stringIndex = i + 1;
            // Reversed alphabet position ('a' -> 26, 'b' -> 25, ..., 'z' -> 1)
            int reverseAlphabetPos = 26 - (c - 'a');
            
            totalDegree += stringIndex * reverseAlphabetPos;
        }
        return totalDegree;
    }
}