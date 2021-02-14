import java.util.Arrays;

public class Solution {

    /**
     * 875. 爱吃香蕉的珂珂
     */
    public int minEatingSpeed(int[] piles, int H) {

        int l = 1, r = Arrays.stream(piles).max().getAsInt();

        // [l, r]
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (eatingTime(piles, mid) <= H) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    private int eatingTime(int[] piles, int mid) {
        int res = 0;
        for (int i: piles) res += i / mid + (i % mid == 0 ? 0 : 1);

        return res;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     */
    public int shipWithinDays(int[] weights, int D) {
        int l = 1, r = 1000000000;

        // [l ,r]
        while (l < r) {
            int mid = l + (r -l) / 2;

            System.out.println("l: "+l+"r: "+r+"mid: "+ mid + "transTime: " + transTime(weights, mid));
            if (transTime(weights, mid) <= D) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    private int transTime(int[] weights, int mid) {
        int res = 0, count = 0;
        for (int i : weights) {

            if (i > mid) return 10000000;

            if (count + i > mid) {
                count = i;
                res += 1;
            } else {
                count += i;
            }
        }

        return res + 1;
    }

}
