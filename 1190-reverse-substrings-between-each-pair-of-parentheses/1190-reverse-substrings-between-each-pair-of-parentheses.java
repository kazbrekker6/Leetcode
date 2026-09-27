class Solution {
    public String reverseParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Store the current length of StringBuilder before the opening bracket
                stack.push(sb.length());
            } else if (c == ')') {
                // Reverse the substring from the matching opening bracket's position
                int start = stack.pop();
                reverse(sb, start, sb.length() - 1);
            } else {
                // Append regular characters
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }
}
