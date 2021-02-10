public class ArrayQueue<T> implements Queue<T> {

    private Array<T> queue;

    public ArrayQueue(int capacity) {
        queue = new Array<>(capacity);
    }

    public ArrayQueue() {
        queue = new Array<>();
    }

    @Override
    public int getSize() {
        return queue.getSize();
    }

    public int getCapacity() {
        return queue.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void enqueue(T e) {
        queue.addLast(e);
    }

    @Override
    public T dequeue() {
        return queue.removeFirst();
    }

    @Override
    public T getFront() {
        return queue.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Queue: front [");
        for (int i = 0; i < queue.getSize(); i++) {
            ret.append(queue.get(i));
            if (i < queue.getSize() - 1) {
                ret.append(", ");
            }
        }
        ret.append("] tail");
        return ret.toString();
    }
}
