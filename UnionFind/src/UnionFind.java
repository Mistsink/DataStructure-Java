/**
 * 第四版
 */
public class UnionFind implements UF {
    private int[] parent;
    private int[] rank; // 维护以i为根的树的高度

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
    }


    private int find(int p) {
        if (p <0 || p >= parent.length) throw new IllegalArgumentException("index invalid");

        if (parent[p] != p)
            // path compress 路经压缩,将所有子节点直接指向根 节点
            parent[p] = find(p);
        return parent[p];
    }


    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p), qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

    }
}
