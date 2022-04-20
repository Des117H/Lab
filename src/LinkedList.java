public class LinkedList<T> {

    // this class is used as a container to data
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node<T> head;

    public LinkedList() {
        size = 0;
        head = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(T item) {
        Node<T> n = new Node<T>(item);
        if (!isEmpty()) {
            n.next = head;
        }
        head = n;
        size++;
        return true;
    }

    public boolean pop() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return false;
        }
        // advance head
        head = head.next;
        size--;
        return true;
    }

    public T peek() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    public T pollLast() {
        Node<T> node = head;
        if (!isEmpty()) {
            while ((--size > 0))
                node = node.next;
            return node.data;
        }
        return null;
    }
}
