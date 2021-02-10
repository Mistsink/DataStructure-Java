import java.util.Objects;

public class Array<T> {
    private T[] data;
    private int size;

    public Array(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(T e) {
        add(size, e);
    }

    public void addFirst(T e) {
        add(0, e);
    }

    public void add(int index, T e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, index < 0 || index > size");
        }

        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = e;
        size ++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, index < 0 || index >= size");
        }

        return data[index];
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size-1);
    }

    public T set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, index < 0 || index >= size");
        }

        data[index] = e;
        return e;
    }

    public boolean contains(T e) {
        return find(e) != -1;
    }

    public int find(T e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public T remove(int index) {
        T ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size --;
        data[size] = null;

        // eager model
        // if (size == data.length / 2 && data.length / 2 != 0) {
        //     resize(data.length / 2);
        // }

        // lazy model
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public void removeAll(T e) {
        int index = find(e);
        while (index != -1) {
            remove(index);
            index = find(e);
        }
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size: %d, capacity: %d.\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i < size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }


    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }
}
