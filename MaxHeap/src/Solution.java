import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    /**
     * 剑指 Offer 40. 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 默认是最小堆，使用reverseOrder即为改用最大堆
        for (int i = 0; i < k; i ++) pq.add(arr[i]);

        for (int i = k; i < arr.length; i ++) {
            if (!pq.isEmpty() && arr[i] < pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i ++) res[i] = pq.remove();

        return res;
    }


    /**
     *215. 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i ++) pq.add(nums[i]);

        for (int i = k; i < nums.length; i ++) {
            if (!pq.isEmpty() && nums[i] > pq.peek()) {
                System.out.println("peek: " + pq.peek());
                pq.remove();
                pq.add(nums[i]);
            }
        }

        int res = pq.peek();

        return res;
    }
}
