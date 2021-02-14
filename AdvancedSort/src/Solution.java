public class Solution {
//    public double myPow(double x, int n) {
//        if (n == 1) return x;
//        if (n == 0) return 1;
//        if (n < 0) {
//            x = 1 / x;
//            n = -n;
//        }
//        double res = 1;
//
//        res *= myPow(x, n / 2);
//        res *= myPow(x, n - n / 2);
//
//        return res;
//    }

    public static double _pow(double x, long n) {
        if (n == 1) return x;
        if (n == 0) return 1;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        if (n % 2 == 0) {
            double res = _pow(x, n / 2);
            return res * res;
        } else {
            return x * _pow(x, n - 1);
        }
    }

    public static double myPow(double x, int n) {
        long N = n;
        return _pow(x, N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public static double Pow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static void main(String[] args) {
        System.out.println(Pow(0.00001, 2147483647));
        System.out.println(myPow(0.00001, 2147483647));
    }
}