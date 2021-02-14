public class BinarySearch {
    private void BinarySearch(){}

    public static <T extends Comparable<T>> int recursiveSearch(T[] arr, T tar) {
        return recursiveSearch(arr, 0, arr.length -1, tar);
    }

    private static <T extends Comparable<T>> int recursiveSearch(T[] arr, int l, int r, T tar) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(tar) == 0) return mid;
        else if (arr[mid].compareTo(tar) > 0) return recursiveSearch(arr, l, mid - 1, tar);
        else return recursiveSearch(arr, mid + 1, r, tar);
    }

    // 非递归式
    public static <T extends Comparable<T>> int search(T[] arr, T tar) {
        int l = 0, r = arr.length -1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;

            if (arr[mid].compareTo(tar) == 0) return mid;
            else if (arr[mid].compareTo(tar) > 0) r = mid - 1;
            else l = mid + 1;
        }

        return -1;
    }


    // upper: > target 的最小值的索引
    public static <T extends Comparable<T>> int upper(T[] arr, T tar) {
        int l = 0, r = arr.length;

        // arr[l, r] 中寻解，同时r必须为区间最末位索引加一
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(tar) <= 0) l = mid + 1;
            else r = mid;
        }

//        return l == arr.length ? -1 : l; // 此时退出了循环 l == r
        return l;
    }

    // lower_ceil: >= target 的最小索引
    public static <T extends Comparable<T>> int lower_ceil(T[] arr, T tar) {
        int l = 0, r = arr.length;

        // arr[l, r] 中寻解，同时r必须为区间最末位索引加一
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(tar) < 0) l = mid + 1;
            else r = mid;
        }

        return l;
    }

    /**
     * ceil:天花板、上界
     * 若数组中存在tar，返回最大的索引
     * 若数组中不存在tar，返回upper值
     * @param arr
     * @param tar
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int ceil(T[] arr, T tar) {
        int upper = upper(arr, tar);

        if (upper - 1 >= 0 && arr[upper - 1].compareTo(tar) == 0) {
            return upper - 1;
        } else return upper;
    }


    /**
     * lower:下界
     * < tar 的最大索引
     * 若数组中存在tar，返回最大的索引
     * 若数组中不存在tar，返回upper值
     * @param arr
     * @param tar
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int lower(T[] arr, T tar) {
        int l = -1, r = arr.length - 1;

        // arr[l, r] 中寻解，同时r必须为区间最末位索引加一
        while (l < r) {
//            int mid = l + (r - l) / 2;

            int mid = l + (r -l + 1) / 2; // 向上取整
            // 大坑！！！若l与r相邻时，mid = l，那么区间不会改变，会陷入死循环
            // 优雅的解决方法：mid = l + (r - l +1) / 2
            // + 1会使当l = r时，mid会取r的值，而不是 l 的值
            if (arr[mid].compareTo(tar) < 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }
}
