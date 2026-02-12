public class SolutionOneFixed {
    
    interface Foo {
        public boolean dfs_color(int node, int color);
    }
    
    public boolean isBipartite(final int[][] graph) {
        final int[] visited = new int[graph.length];
        
        Foo gColor = new Foo() {
            @Override 
            public boolean dfs_color(int node, int color) {
                // FIX #1: Check color consistency when revisiting nodes
                // If node is already visited, verify it has the expected color
                if (visited[node] != 0) { 
                    return (visited[node] == color); // Returns true only if colors match
                }
                
                visited[node] = color;
                for (int i = 0; i < graph[node].length; i++) {
                    int neighbor = graph[node][i];
                    // FIX #2: Use opposite color for adjacent nodes
                    // Bipartite graphs require neighbors to have opposite colors
                    boolean res = dfs_color(neighbor, -color); 
                    if (!res) return false;
                }
                return true;
            }
        };
        
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0) {
                if (!gColor.dfs_color(i, 1)) return false;
            }
        }
        return true;
    }
}
