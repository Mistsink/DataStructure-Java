package DFS;
import Graph.Graph;
/**
 * 检验是否有环
 */
public class CycleDetection {

    private Graph G;
    private boolean[] visited;

    private boolean hasCycle;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        hasCycle = false;

        for (int v = 0; v < G.V(); v ++)
            if (!visited[v] && !hasCycle) dfs(v, -1);
    }


    private void dfs(int v, int pre) {

        visited[v] = true;

        for (int w : this.G.adj(v))
            if (!visited[w])
                dfs(w, v);
            else if (w != pre) {
                hasCycle = true;
                return;
            }

    }


    public boolean hasCycle () {
        return hasCycle;
    }



    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());
    }

}
