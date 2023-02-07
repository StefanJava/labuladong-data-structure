package com.stefan.arraylinklist;

/**
 * @Description:
 * @Date: 2023/2/7 11:24
 * @Author: stefanyang
 */
public class RingBuffer {

    private byte[] buffer;

    /**
     * mask 用于防止索引越界
     */
    private int mask;

    /**
     * 读写指针的位置
     */
    private int r, w;

    /**
     * 记录可以读取的字节个数
     */
    private int size;

    /**
     * 默认初始化的 buffer 大小
     */
    private final static int INIT_CAP = 1024;

    public RingBuffer() {
        this(INIT_CAP);
    }

    public RingBuffer(int cap) {
        int newCap = ceilToPowerOfTwo(cap);
        this.buffer = new byte[newCap];
        this.r = this.w = 0;
        this.size = 0;
        this.mask = newCap - 1;
    }

    /**
     * 从 RingBuffer 中读取元素到 out 中，返回读取的字节数
     * @param out 存储读取的数据
     * @return 读取的字节数
     */
    public int read(byte[] out) {
        if (out == null || out.length == 0 || isEmpty()) {
            return 0;
        }

        // 读取的字节数
        int n = Math.min(size, out.length);

        // 情况1： r----w
        if (w > r) {
            // r----w 读取后变成 **r--w
            // copy data[r..r+n] to out[0..]
            System.arraycopy(buffer, r, out, 0, n);
            // 向前移动读指针
            r += n;
            // 可读取的字节数减少了 n
            size -= n;
            return n;
        }

        // 情况2：--w  r---
        if (r + n <= buffer.length) {
            // 情况2.1：--w  r--- 读取后变成 --w  **r-
            // copy data[r..r+n] to out[0..]
            System.arraycopy(buffer, r, out, 0, n);
        } else {
            // 情况2.2：----w  r-- 读取后变成  *r--w  ***
            int n1 = buffer.length - r;
            int n2 = n - n1;
            // copy data[r..] to out[0..n1]
            System.arraycopy(buffer, r, out, 0, n1);
            // copy data[0..n2] to out[n1..]
            System.arraycopy(buffer, 0, out, n1, n2);
        }

        // 向前移动读指针
        r = (r + n) & mask;

        // 可读取的字节数减少了 n
        size -= n;
        return n;
    }

    /**
     * 将 in 中的数据写入 RingBuffer 中，返回写入的字节数
     * @param in 需写入的数据
     * @return 写入的字节数
     */
    public int write(byte[] in) {

        if (in == null || in.length == 0) {
            return 0;
        }

        int n = in.length;

        if (n > buffer.length - size) {
            resize(buffer.length + n);
        }


        if (r <= w) {
            // ---r*****w----
            if (w + n <= buffer.length) {
                // ---r*******w--
                System.arraycopy(in, 0, buffer, w, n);
            } else {
                // *w-r**********
                int n1 = buffer.length - w;
                int n2 = n - n1;
                System.arraycopy(in, 0, buffer, w, n1);
                System.arraycopy(in, n1, buffer, 0, n2);
            }
        } else {
            // ***w-----r****
            System.arraycopy(in, 0, buffer, w, n);
        }
        w = (w + n) & mask;
        size += n;
        return n;
    }

    /**
     * 返回可读的字节数
     * @return 可读的字节数
     */
    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int newCap) {

        int cap = ceilToPowerOfTwo(newCap);

        byte[] temp = new byte[cap];

        int n = read(temp);

        buffer = temp;
        this.r = 0;
        this.w = n;
        this.mask = cap - 1;
    }

    /**
     * 将输入的 n 转化为 2 的指数，比如输入 12，返回 16
     * @param n 需转化的数
     * @return 转化的结果
     */
    private static int ceilToPowerOfTwo(int n) {
        if (n < 0) {
            // 肯定不能小于 0
            n = 2;
        }

        if (n > (1 << 30)) {
            // int 型最大值为 2^31 - 1
            // 所以无法向上取整到 2^31
            n = 1 << 30;
        }

//        int res = 1;
//        while (res < n) {
//            res = res * 2;
//        }
//        return res;

        // 位运算技巧，参考如下链接：
        // http://graphics.stanford.edu/~seander/bithacks.html#RoundUpPowerOf2
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;

        return n;
    }

    public static void main(String[] args) {
        RingBuffer rb = new RingBuffer(3);

        String s = "abcdefghijklmn";
        int nwrite = rb.write(s.getBytes());
        System.out.println("write " + nwrite + " bytes " + s);

        byte[] out = new byte[9];
        int nread = rb.read(out);
        System.out.println("read " + nread + " bytes " + new String(out));

        nread = rb.read(out);
        System.out.println("read " + nread + " bytes " + new String(out));

        //write 14 bytes abcdefghijklmn
        //read 9 bytes abcdefghi
        //read 5 bytes jklmnfghi
    }

}
