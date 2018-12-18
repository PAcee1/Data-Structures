
import com.enbuys.linkedlist.LinkedList;

import java.util.Queue;

/**
 * @Author: Pace
 * @Data: 2018/12/14 10:39
 * @Version: v1.0
 */
public class Test {
    public ListNode reverseList(ListNode head) {
        /* recursive solution */
        return reverseListInt(head, null);
    }

    private ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null){
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next,head);
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Test()).reverseList(head);
        System.out.println(res);
        System.out.println(5/2);
    }

}
