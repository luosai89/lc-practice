package Others;

/**
 * 8/7 (makeup
 */
public class T_0809_02_Circular_Insertion {
    public static Node circularInsertion(int data, Node head) {
        // Write your code here
        Node t = head;
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
        } else {
            while(t.next != head) {
                if ((data >= t.data && data < t.next.data)
                    || (data >= t.data && t.data > t.next.data && data > t.next.data)) {
                    break;
                }
                t = t.next;
            }
            newNode.next = t.next;
            t.next = newNode;
        }
        return newNode;
    }

    // Starter code, do not touch
    static class Node {
        int data;
        Node next;

        Node(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    // Starter code, do not touch.
    static class SinglyLinkedList {
        Node head;
        Node tail;

        SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        void insertNode(int nodeData) {
            Node node = new Node(nodeData);

            if (this.head == null) {
                this.head = node;
                this.head.next = tail;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
            this.tail.next = head;
        }
    }
}
