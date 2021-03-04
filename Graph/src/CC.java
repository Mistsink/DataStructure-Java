import java.util.ArrayList;

/**
 * CC :     Connected Component
 *  连通分量
 */
public class CC {

    private Graph G;
    private int[] visited;
    private int cccount;    //  connected component count

    public CC(Graph G) {
        this.G = G;
        cccount = 0;
        visited = new int[G.V()];
        for (int i = 0; i < G.V(); i ++) visited[i] = -1;

        for (int v = 0; v < G.V(); v ++){
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount ++;
            }
        }
    }


    private void dfs(int v, int cId) {

        visited[v] = cId;

        for (int w : this.G.adj(v))
            if (visited[w] == -1) dfs(w, cId);

    }


    public int count () {
        for (int v : visited) {
            System.out.print(v + " ");
        }
        System.out.println();
        return cccount;
    }


    public boolean isConnected (int v, int w) {
        return visited[v] == visited[w];
    }


    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[cccount];

        for (int i = 0; i < cccount; i++) res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v ++) {
            res[visited[v]].add(v);
        }

        return res;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0,1));
    }

}
