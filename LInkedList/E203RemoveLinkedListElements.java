package LInkedList;

/**
 * 4/10/2021 
 */
public class E203RemoveLinkedListElements {
    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.next.val == val) p.next = p.next.next;
            //
            else p = p.next;
        }
        return head;
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode();
        node1.val = 1;
        ListNode node2 = new ListNode();
        node2.val = 2;
        ListNode node3 = new ListNode();
        node3.val = 2;
        ListNode node4 = new ListNode();
        node4.val = 1;
//        ListNode node5 = new ListNode();
//        node5.val = 4;
//        ListNode node6 = new ListNode();
//        node6.val = 5;
//        ListNode node7 = new ListNode();
//        node7.val = 6;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = null;
        ListNode result = removeElements(node1, 2);
        ListNode p = result;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }
}
