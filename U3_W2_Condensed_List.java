import java.util.HashSet;
import java.util.Set;

public class U3_W2_Condensed_List {
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

    public static SinglyLinkedListNode condenseSolution(SinglyLinkedListNode head) {
        // Write your code here
        Set<Integer> numbers = new HashSet<>();
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode prev = null;
        while (current != null) {
            int val = current.data;
            if (!numbers.contains(val)) {
                numbers.add(val);
                prev = current;
            } else {
                prev.next = current.next;
            }
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] list = {8, 3, 4, 3, 2, 6, 1, 2, 6};
        SinglyLinkedListNode test = new SinglyLinkedListNode(list);
        test.print();
        SinglyLinkedListNode result = condense(test);
        result.print();

        test = new SinglyLinkedListNode(list);
        test.print();
        result = condenseSolution(test);
        result.print();
    }
}
