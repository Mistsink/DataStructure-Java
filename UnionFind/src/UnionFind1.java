/**
 * 第一版----并查集
 */
public class UnionFind1 implements UF {
    private int[] id ;

    public UnionFind1(int size) {
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }


    // 返回原数组中索引p的id值
    private int find(int p) {
        if (p <0 || p >= id.length) throw new IllegalArgumentException("index invalid");
        return id[p];
    }


    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p), qID = find(q);
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
    }
}
