public class Main {



    public static void main(String[] args) {
        int a = 4;
        print(((Integer)a).hashCode());

        double b = -3.3;
        print(((Double)b).hashCode());
    }

    private static <T> void print(T a) {
        System.out.println(a);
    }
}
