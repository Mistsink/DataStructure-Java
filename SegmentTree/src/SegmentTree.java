/**
 * 线段树（区间树）
 * @param <T>
 */
public class SegmentTree<T> {

    private T[] data;
    private T[] tree;
    private Merger<T> merger;

    public SegmentTree(T[] arr, Merger<T> merger) {
        this.merger = merger;

        data = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (T[]) new Object[4 * arr.length]; // 4倍的长度能保证整棵树能用数组表示
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 以rootIndex索引为根节点，对[left, right]区间创建线段树
    private void buildSegmentTree(int rootIndex, int left, int right) {
        if (left == right) {
            tree[rootIndex] = data[left];    // 处理叶子节点
            return;
        }

        int leftIndex = leftChild(rootIndex);
        int rightIndex = rightChild(rootIndex);
        int mid = left + (right - left) / 2;

        buildSegmentTree(leftIndex, left, mid);             // [left, mid]
        buildSegmentTree(rightIndex, mid + 1, right);   // [mid + 1, right]

        tree[rootIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }


    public int getSize() {
        return data.length;
    }


    public T get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Failed to get, index < 0 || index >= data.length");
        return data[index];
    }


    // 查询[ queryL, queryR ]
    public T query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    // 在rootIndex为根的节点 [l, r] 中查询 [ queryL, queryR ]
    private T query(int rootIndex, int l, int r, int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length
                || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Failed to query, index invalid");

        if (queryL == l && queryR == r) return tree[rootIndex];

        int mid = l + (r - l) / 2;

        int leftIndex = leftChild(rootIndex);
        int rightIndex = rightChild(rootIndex);

        // [mid+1, r]
        if (queryL >= mid + 1) return query(rightIndex, mid + 1, r, queryL, queryR);

        // [l, mid]
        if (queryR <= mid) return query(leftIndex, l, mid, queryL, queryR);

        // [queryL, mid] + [mid+1, queryR]
        return merger.merge(query(leftIndex, l, mid, queryL, mid),
                query(rightIndex, mid + 1, r, mid + 1, queryR));
    }


    // [index] 更新为e
    public void set(int index, T e) {
        if (index < 0 || index >= data.length) throw new IllegalArgumentException("Failed to set, index invalid");

        set(0, 0, data.length - 1, index, e);
    }
    private void set(int rootIndex, int l, int r, int index, T e) {

        if (l == r) {
            tree[rootIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(rootIndex);
        int rightIndex = rightChild(rootIndex);

        if (index <= mid) set(leftIndex, l, mid, index, e);
        else set(rightIndex, mid + 1, r, index, e);

        tree[rootIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }


    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("segment tree: [");
        for (int i = 0; i < tree.length; i++) {
            ret.append(tree[i]);

            if (i != tree.length - 1) ret.append(", ");
        }

        ret.append("]");
        return ret.toString();
    }
}
