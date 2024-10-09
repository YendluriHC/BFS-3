//TC: O(V + E)
//SC: O(V)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // BFS function to clone the graph
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // Map to store original node -> cloned node
        Map<Node, Node> cloned = new HashMap<>();

        // Initialize the queue for BFS and add the original node
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // Create the clone of the root node
        cloned.put(node, new Node(node.val));

        // Perform BFS
        while (!queue.isEmpty()) {
            Node curr = queue.poll();  // Get the current node

            // Iterate over all neighbors of the current node
            for (Node neighbor : curr.neighbors) {
                // If this neighbor hasn't been cloned yet
                if (!cloned.containsKey(neighbor)) {
                    // Create a clone of the neighbor
                    cloned.put(neighbor, new Node(neighbor.val));
                    // Add it to the queue for further BFS exploration
                    queue.add(neighbor);
                }
                // Add the cloned neighbor to the current node's cloned neighbor list
                cloned.get(curr).neighbors.add(cloned.get(neighbor));
            }
        }

        // Return the clone of the original starting node
        return cloned.get(node);
    }
}
