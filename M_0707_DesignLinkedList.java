/**
* 3/30/2021 https://leetcode.com/problems/design-linked-list/
*
*/
class M_0707_DesignLinkedList {
    IntNode sentinel;
    int size;

    public class IntNode {
        public IntNode prev;
        public int val;
        public IntNode next;
        public IntNode(IntNode prev, int val, IntNode next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }

    /** Initialize your data structure here. */
    public M_0707_DesignLinkedList() {
        sentinel = new IntNode(null, 721, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        IntNode p = getNode(index);
        if (p == null) {
            return -1;
        }
        return p.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        IntNode newNode = new IntNode(null, val, null);
        IntNode p = getNode(index);
        newNode.prev = p.prev;
        newNode.next = p;
        p.prev.next = newNode;
        p.prev = newNode;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        IntNode p = getNode(index);
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
    }

    private IntNode getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        IntNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p;
    }
}

/**
 * Your M_0707_DesignLinkedList object will be instantiated and called as such:
 * M_0707_DesignLinkedList obj = new M_0707_DesignLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
