package algorithms.sequential;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Carlos
 * @description 顺序查询（基于无序链表）
 * @Date 2019/9/11
 */

public class SequentialSearchST<Key, Value> {
    private int n;
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node (Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            return false;
        }
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = this.root; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        this.root = new Node(key, value, this.root);
        n++;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        for (Node x = this.root; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        this.root = delete(this.root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = this.root; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

}
