/**
 * 最大堆（大根堆）
 * @param <T>
 */
public class MaxHeap<T extends Comparable<T>> {
    private Array<T> arr;

    public MaxHeap() {
        arr = new Array<>();
    }
    public MaxHeap(int capacity) {
        arr = new Array<>(capacity);
    }
    // heapify: 将数组整理为堆的形式
    public MaxHeap(T[] data) {
        arr = new Array<>(data);
        // 从最后的 非叶子 结点开始，往前进行节点的下沉操作，最后即得到一个大顶堆
        for (int i = parent(data.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("Index 0 dose not have parent");
        return (index - 1) / 2;
    }
    private int left(int index) {
        return index * 2 + 1;
    }
    private int right(int index) {
        return index * 2 + 2;
    }


    public int size() {
        return arr.getSize();
    }


    public boolean isEmpty() {
        return arr.isEmpty();
    }


    public void add(T e) {
        arr.addLast(e);
        siftUp(arr.getSize() - 1);
    }


    // 返回堆中最大值
    public T peekMax() {
        if (arr.getSize() == 0) throw new IllegalArgumentException("heap is empty");

        return arr.get(0);
    }


    // 取出堆中最大值
    public T extractMax() {
        T max = peekMax();

        arr.swap(0, arr.getSize() - 1);
        arr.removeLast();
        siftDown(0);

        return max;
    }



    private void siftUp(int index) {
        while (index > 0 && arr.get(parent(index)).compareTo(arr.get(index)) < 0){
            arr.swap(index, parent(index));
            index = parent(index);
        }
    }

    private void siftDown(int index) {
        int left = left(index), right = left + 1, size = arr.getSize();
        while (left < size){
            int maxChild = left;

            if (right < size) {
                maxChild = arr.get(left).compareTo(arr.get(right)) > 0 ? left: right;
            }

            if (arr.get(index).compareTo(arr.get(maxChild)) > 0) break;

            arr.swap(index, maxChild);
            index = maxChild;
            left = left(index); right = left + 1;
        }
    }


    // 取出最大值，并替换
    public T replace(T e) {
        T max = peekMax();

        arr.set(0, e);
        siftDown(0);

        return max;
    }



}
