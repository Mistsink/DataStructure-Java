import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 逆序对
 */
public class ReversePairs {
    private int count;

    public int reversePairs(int[] nums) {
        count = 0;
        int[] temp = Arrays.copyOf(nums, nums.length);
        mergeSort(nums, 0, nums.length - 1, temp);
        return count;
    }

    private void mergeSort(int[] nums, int l, int r, int[] temp) {
        if (l >= r) return ;

        int mid = l + (r - l) / 2;

        mergeSort(nums, l, mid, temp);
        mergeSort(nums, mid + 1, r, temp);

        if (nums[mid] > nums[mid + 1]) {
            merge(nums, l, mid, r, temp);
        }
    }

    private void merge(int[] nums, int l, int mid, int r, int[] temp) {
        System.arraycopy(nums, l, temp, l, r - l + 1);

        int i = l, j = mid +1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                nums[k] = temp[j]; j++;
            } else if (j > r) {
                nums[k] = temp[i]; i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i]; i++;
            } else {
                nums[k] = temp[j]; j++;
                count += mid - i + 1;
            }
        }
    }

    public int simpleSolution(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i ++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count ++;
                }
            }
        }
        return count;
    }
}
