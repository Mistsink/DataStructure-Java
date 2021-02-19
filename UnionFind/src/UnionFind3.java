/**
 * 第三版
 */
public class UnionFind3 implements UF {
    private int[] parent;
    private int[] rank; // 维护以i为根的树的高度

    public UnionFind3(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
    }


    private int find(int p) {
        if (p <0 || p >= parent.length) throw new IllegalArgumentException("index invalid");

        int pID = parent[p];

        while (pID != p) {
            // path compress 路经压缩
            parent[pID] = parent[parent[pID]]; // 改变下层的节点的父亲指向，提升下层节点，减少树的高度
            pID = parent[pID];
        }
        return pID;
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
