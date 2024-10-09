//TC: O(n * 2^n)
//SC: O(2^n)
class Solution {
    // Helper function to check if a string has valid parentheses
    private boolean isValid(String s) {
        int count = 0;  // To track the balance of parentheses
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;   // Increment for every '('
            } else if (c == ')') {
                count--;   // Decrement for every ')'
            }
            if (count < 0) {
                return false;  // More ')' than '(' at any point
            }
        }
        return count == 0;  // Valid if balance is zero
    }
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.add(s);
        visited.add(s);

        boolean found = false;  // To stop once we find the first valid strings

        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            if (isValid(current)) {
                result.add(current);
                found = true;
            }

            if (found) continue;  // Stop exploring deeper once valid strings are found

            // Generate all possible strings by removing one parenthesis at a time
            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) != '(' && current.charAt(i) != ')') continue;

                String next = current.substring(0, i) + current.substring(i + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return result;
    }
}
