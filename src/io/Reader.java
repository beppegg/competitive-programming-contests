package io;

import java.io.*;
import java.util.Arrays;

public class Reader implements Closeable, AutoCloseable{
    private static final int LINE_LENGTH = 64;
    private static final int BUFFER_SIZE = 1 << 16;

    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader(InputStream in) {
        din = new DataInputStream(in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() {
        byte[] buf = new byte[LINE_LENGTH]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                break;
            }
            buf[cnt++] = (byte) c;
            if (cnt % LINE_LENGTH == 0) {
                buf = Arrays.copyOf(buf, LINE_LENGTH * (cnt / LINE_LENGTH + 1));
            }
        }
        return new String(buf, 0, cnt);
    }

    public String next() {
        byte[] buf = new byte[LINE_LENGTH]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (!Character.isLetterOrDigit(c)) {
                break;
            }
            buf[cnt++] = (byte) c;
            if (cnt % LINE_LENGTH == 0) {
                buf = Arrays.copyOf(buf, LINE_LENGTH * (cnt / LINE_LENGTH + 1));
            }
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public double nextDouble() {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() {
        try {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte read() {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }
}
