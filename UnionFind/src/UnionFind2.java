/**
 * 第二版
 */
public class UnionFind2 implements UF {
    private int[] parent;
    private int[] sz; // 维护以i为根的树的长度

    public UnionFind2(int size) {
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            sz[i] = 1;
            parent[i] = i;
        }
    }


    private int find(int p) {
        if (p <0 || p >= parent.length) throw new IllegalArgumentException("index invalid");

        int pID = parent[p];

        while (pID != p) {
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

        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot] ;
        }

    }
}
