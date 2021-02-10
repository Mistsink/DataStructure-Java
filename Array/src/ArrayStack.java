public class ArrayStack<T> implements Stack<T> {
    Array<T> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(T e) {
        array.addLast(e);
    }

    @Override
    public T pop() {
        return array.removeLast();
    }

    @Override
    public T peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++) {
            ret.append(array.get(i));
            if (i < array.getSize() - 1) {
                ret.append(", ");
            }
        }
        ret.append("] top");
        return ret.toString();
    }
}
