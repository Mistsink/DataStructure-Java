public interface LinkList<T> {
    int getSize();
    boolean isEmpty();
    void add(int index, T e);
    void addFirst(T e);
    void addLast(T e);
    T get(int index);
    T getFirst();
    T getLast();
    void set(int index, T e);
    boolean contains(T e);
    T remove(int index);
    T removeFist();
    T removeLast();
}
