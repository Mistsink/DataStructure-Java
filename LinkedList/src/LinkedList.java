public class LinkedList<T> implements LinkList<T> {
    private class Node {
        public T e;
        public Node next;
        public Node(T e, Node next) {
            this.e = e;
            this.next = next;
        }
        public Node(T e) {
            this(e, null);
        }
        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add (int index, T e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add filed, index < 0 || index > size");
        }

        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    @Override
    public void addFirst(T e) {
        add(0, e);
    }

    @Override
    public void addLast(T e) {
        add(size, e);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add filed, index < 0 || index > size");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }

        return cur.e;
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public void set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set filed, index < 0 || index > size");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public boolean contains(T e) {
        Node cur  = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove filed, index < 0 || index > size");
        }

        Node pre = dummyHead;
        Node ret;
        for (int i = 0; i < index; i ++) {
            pre = pre.next;
        }
        ret = pre.next;
        pre.next = ret.next;
        ret.next = null;

        size--;
        return ret.e;
    }

    @Override
    public T removeFist() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("LinkedList: head:");
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            ret.append(cur.e);
            ret.append("->");
        }
        ret.append("NULL");
        return ret.toString();
    }


}
