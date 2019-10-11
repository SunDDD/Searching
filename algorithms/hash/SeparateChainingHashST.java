package algorithms.hash;

import algorithms.sequential.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Carlos
 * @description 基于拉链法的散列表
 * @Date 2019/9/12
 */

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n; //键值对数量
    private int m; //链表数组的大小

    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.n = temp.n;
        this.m = temp.m;
        this.st = temp.st;
    }

    public boolean contains(Key key) {
        if (key == null) {
            return false;
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }
        if (value == null) {
            delete(key);
        }
        if (n >= 10 * m) {
            resize(m * 2);
        }
        int i = hash(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, value);

    }

    public void delete(Key key) {
        if (key == null || !contains(key)) {
            return;
        }
        n--;
        int i = hash(key);
        if (m > INIT_CAPACITY && n <= m * 2) {
            resize(m / 2);
        }
        st[i].delete(key);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        st.put("Houwenjun", 1);
        st.put("Wenjun", 2);
        for (String key : st.keys()) {
            System.out.println(st.get(key));
        }
    }

}
