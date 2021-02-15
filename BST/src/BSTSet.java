public class BSTSet<T extends Comparable<T>> implements Set<T> {
    private BST<T> bst;

    public BSTSet() {
        this.bst = new BST<>();
    }

    @Override
    public void add(T e) {
        bst.add(e);
    }

    @Override
    public void remove(T e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public boolean contains(T e) {
        return bst.contains(e);
    }
}
