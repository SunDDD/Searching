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

    private int n;  //键值对总数
    private int m;  //散列表的大小
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

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        int i = hash(key);
        return st[i].get(key);
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

        if (n >= 10 * m) {
            resize(2 * m);
        }

        int i = hash(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, value);

    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value>  temp= new SeparateChainingHashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.n = temp.n;
        this.m = temp.m;
        this.st = temp.st;
    }

    public void delete(Key key) {
        if (key == null || !contains(key)) {
            return;
        }
        int i = hash(key);
        st[i].delete(key);

        if (m > INIT_CAPACITY && n <= 2 * m) {
            resize(m / 2);
        }
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
}
