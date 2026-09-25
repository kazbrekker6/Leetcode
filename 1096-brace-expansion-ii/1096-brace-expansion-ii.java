class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = dfs(expression);
        List<String> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);
        return sortedList;
    }

    private Set<String> dfs(String exp) {
        Set<String> currentResult = new HashSet<>();
        int braceCount = 0;

        // Step 1: Handle unions by looking for top-level commas
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == '{') braceCount++;
            else if (c == '}') braceCount--;
            else if (c == ',' && braceCount == 0) {
                currentResult.addAll(dfs(exp.substring(0, i)));
                currentResult.addAll(dfs(exp.substring(i + 1)));
                return currentResult;
            }
        }

        // Step 2: Handle concatenations
        int i = 0;
        Set<String> prevSet = new HashSet<>(Collections.singleton(""));

        while (i < exp.length()) {
            if (exp.charAt(i) == '{') {
                // Find matching closing brace
                int j = i, bCount = 0;
                while (j < exp.length()) {
                    if (exp.charAt(j) == '{') bCount++;
                    else if (exp.charAt(j) == '}') bCount--;
                    if (bCount == 0) break;
                    j++;
                }
                // Recursively solve inside the braces
                Set<String> nextSet = dfs(exp.substring(i + 1, j));
                prevSet = multiply(prevSet, nextSet);
                i = j + 1;
            } else {
                // Base Case Fix: Extract plain text directly without calling dfs recursively
                int j = i;
                while (j < exp.length() && Character.isLowerCase(exp.charAt(j))) {
                    j++;
                }
                String word = exp.substring(i, j);
                Set<String> nextSet = new HashSet<>(Collections.singleton(word));
                prevSet = multiply(prevSet, nextSet);
                i = j;
            }
        }

        currentResult.addAll(prevSet);
        return currentResult;
    }

    private Set<String> multiply(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
}
