public class LoopQueue<T> implements Queue<T>{

    private T[] data;
    private int front, tail, size;

    public LoopQueue(int capacity) {
        data = (T[]) new Object[capacity + 1];
        front = tail = size = 0;
    }
    public LoopQueue() {
        this(10);
    }


    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }


    public boolean isFull() {
        return front == ((tail + 1) % data.length);
    }

    @Override
    public void enqueue(T e) {
        if (isFull()) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public T dequeue() {

        if (isEmpty()) throw new IllegalArgumentException(" Dequeue failed");

        T ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;

        if (size <= getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public T getFront() {
        if (isEmpty()) throw new IllegalArgumentException(" Dequeue failed");

        return data[front];
    }



    private void resize(int newCapacity) {
        T[] newData =(T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("LoopQueue: front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            ret.append(data[i]);
            if ((i + 1) % data.length != tail) {
                ret.append(", ");
            }
        }
        ret.append("] tail");
        return ret.toString();
    }
}
