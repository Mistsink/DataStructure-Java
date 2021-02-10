public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 6; i ++) {
            list.addFirst(i);
        }
        System.out.println("linked list -----------");
        System.out.println(list);

        list.add(3, 1);
        System.out.println(list);

        System.out.println(list.removeFist());
        System.out.println(list);

        System.out.println(list.removeLast());
        System.out.println(list);


        System.out.println("recursive linked list -----------");

        RecursiveLinkedList<Integer> Rlist = new RecursiveLinkedList<>();
        for (int i = 0; i < 6; i ++) {
            Rlist.addFirst(i);
        }
        System.out.println(Rlist);

        Rlist.add(3, 1);
        System.out.println(Rlist);

        System.out.println(Rlist.removeFist());
        System.out.println(Rlist);

        System.out.println(Rlist.removeLast());
        System.out.println(Rlist);

    }
}
