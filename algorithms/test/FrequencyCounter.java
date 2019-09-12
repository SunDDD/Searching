package algorithms.test;

import algorithms.hash.LinearProbingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author Carlos
 * @description 频率计数，使用符号表进行查找
 * @Date 2019/9/11
 */

public class FrequencyCounter {

    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        //1.标准符号比奥
//        ST<String, Integer> st = new ST<>();
        //2.无序链表符号表
//        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        //2.1 系统无序链表符号表
//        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        //3.基于拉链的散列表
//        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        //4.基于线性探测的散列表
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        //4.1系统基于线性探测的散列表
//        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

        Stopwatch stopwatch = new Stopwatch();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) {
                continue;
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String max = " ";
        st.put(max, 0 );
        for (String word : st.keys()) {
            if(st.get(max) < st.get(word)) {
                max  = word;
            }
        }
        double time = stopwatch.elapsedTime();

        StdOut.println(max + " " + st.get(max) + " " + time);

    }

}
