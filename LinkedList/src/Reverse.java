/**
 * 206. 反转链表
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Reverse {
    public ListNode reverseList(ListNode head) {
        return head == null ? null : _reverse(null, head, head.next);
    }

    private ListNode _reverse(ListNode pre, ListNode cur, ListNode next) {
        cur.next = pre;
        if (next == null) {
            return cur;
        }
        return _reverse(cur, next, next.next);
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
