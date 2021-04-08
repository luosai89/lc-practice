import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class U3W2CondensedList {
    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
        SinglyLinkedListNode(int data) {
            this.data = data;
        }
        SinglyLinkedListNode(int[] list) {
            data = list[0];
            for (int i = 1; i < list.length; i++) {
                add(list[i]);
            }
        }
        void add(int data) {
            SinglyLinkedListNode p = this;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new SinglyLinkedListNode(data);
            return;
        }
        void print() {
            System.out.print(data);
            SinglyLinkedListNode p = next;
            while (p != null) {
                System.out.print(" " + p.data);
                p = p.next;
            }
            System.out.println();
        }

    }
    public static SinglyLinkedListNode condense(SinglyLinkedListNode head) {
        // Write your code here
        Set<Integer> numbers = new HashSet<>();
        numbers.add(head.data);
        SinglyLinkedListNode p = head;
        while (p.next != null) {
            if (numbers.contains(p.next.data)) {
                p.next = p.next.next;
            } else {
                numbers.add(p.next.data);
                p = p.next;
            }
        }
        return head;
    }
    public static void main(String[] args) {
        int[] list = {8, 3, 4, 3, 2, 6, 1, 2, 6};
        SinglyLinkedListNode test = new SinglyLinkedListNode(list);
        test.print();
        SinglyLinkedListNode result = condense(test);
        result.print();
    }
}
