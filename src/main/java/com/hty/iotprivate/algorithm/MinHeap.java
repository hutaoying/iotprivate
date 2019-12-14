package com.hty.iotprivate.algorithm;

/**
 * Created by hutaoying on 2019/12/12
 */


public class MinHeap {
    private int[] data;

    public MinHeap(int[] data) {
        this.data = data;
    }

    public void createHeap() {
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            heapIfy(i);
        }
    }

    public void heapIfy(int value) {
        int lchild = left(value);
        int rchild = right(value);

        // 这是一个临时变量，表示 根结点、左结点、右结点中最小的值的结点的下标
        int smallest = value;

        if (lchild < data.length && data[lchild] < data[value])
            smallest = lchild;
        if (rchild < data.length && data[rchild] < data[smallest])
            smallest = rchild;
        if (value == smallest)
            return;
        swap(value, smallest);
        heapIfy(smallest);
    }

    public int left(int value) {
        //2*N+1
        return ((value + 1) << 1) - 1;
    }

    public int right(int value) {
        //2*N+2
        return (value + 1) << 1;
    }

    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void setRoot(int value) {
        data[0] = value;
        heapIfy(0);
    }

    public static void main(String[] args) {
        int[] value = {3,5,8,9,1,2};
        MinHeap heap = new MinHeap(value);
        heap.createHeap();
        for (int i = 0; i < value.length; i++) {
            System.out.print(heap.data[i] + " ");
        }

        System.out.println();
        heap.setRoot(64);
        for (int i = 0; i < value.length; i++) {
            System.out.print(heap.data[i] + " ");
        }
        System.out.println();
    }
}

