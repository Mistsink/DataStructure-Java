import java.util.ArrayList;

public class GraphDFS {

    //  记录遍历的顶点
    private ArrayList<Integer> preOrder = new ArrayList<>();
    private ArrayList<Integer> postOrder = new ArrayList<>();

    private Graph G;
    private boolean[] visited;

    public GraphDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v ++)
            if (!visited[v]) dfs(0);
    }


    private void dfs(int v) {

        visited[v] = true;
        preOrder.add(v);

        for (int w : this.G.adj(v))
            if (!visited[w]) dfs(w);

        postOrder.add(v);
    }


    public Iterable<Integer> preOrder(){
        return preOrder;
    }


    public Iterable<Integer> postOrder(){
        return postOrder;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.preOrder());
        System.out.println(graphDFS.postOrder());
    }

}
