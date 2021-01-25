package com.company;

public class Heap {
    private int[] nodes = new int[100];
    private int index = 0;

    //        finding child left = parent * 2 + 1
//        finding child right = parent * 2 + 2
//        finding parent = (index - 1) / 2

    public void insert(int node) {
        insert(index, node);
    }

    public void insert(int index, int node) {
//        (15, 10, 3, 8, 12, 9, 4, 1, 24)
//        (15, 12, 3, 8, 10, 9, 4, 1, 24)

        if (index == 0) {
            nodes[this.index++] = node;
        } else {
            int parentIndex = (index - 1) / 2;
            int parentValue = nodes[parentIndex];
            nodes[index] = node;
            if (node > parentValue) {
                nodes[index] = parentValue;
                insert(parentIndex, node);
            } else {
                this.index++;
            }
        }
    }

    public void remove() {

    }
}
