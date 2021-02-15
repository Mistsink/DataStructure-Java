import java.util.LinkedList;

public class LinkedListSet<T> implements Set<T> {
    private LinkedList<T> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(T e) {
        if (!linkedList.contains(e)) linkedList.addFirst(e);
    }

    @Override
    public void remove(T e) {
        linkedList.remove(e);
    }

    @Override
    public int getSize() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public boolean contains(T e) {
        return linkedList.contains(e);
    }
}
