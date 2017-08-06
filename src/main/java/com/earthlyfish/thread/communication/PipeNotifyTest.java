package com.earthlyfish.thread.communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by earthlyfisher on 2017/3/13.
 */
public class PipeNotifyTest {

    public static void main(String[] args) throws IOException {
        PipedOutputStream ous=new PipedOutputStream();
        PipedInputStream ins=new PipedInputStream();
        ins.connect(ous);
        ThreadWriter writer=new ThreadWriter("writer",ous);
        ThreadReader reader=new ThreadReader("reader",ins);
        writer.start();
        reader.start();
    }
}


class ThreadReader extends Thread {

    private PipedInputStream pipedInputStream;

    public ThreadReader(String name, PipedInputStream pipedInputStream) {
        super(name);
        this.pipedInputStream = pipedInputStream;
    }

    @Override
    public void run() {
        System.out.println("read : ");
        byte[] byteArray = new byte[20];
        try {
            int readLength = pipedInputStream.read(byteArray);
            while (readLength != -1) {
                String readData = new String(byteArray, 0, readLength);
                System.out.print(readData);
                readLength = pipedInputStream.read(byteArray);
            }
            System.out.println();
            pipedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ThreadWriter extends Thread {

    private PipedOutputStream pipedOutputStream;

    public ThreadWriter(String name, PipedOutputStream pipedOutputStream) {
        super(name);
        this.pipedOutputStream = pipedOutputStream;
    }

    @Override
    public void run() {
        System.out.println("write : ");
        try {
            for (int i = 0; i < 300; i++) {
                String writeData = "" + (i + 1);
                pipedOutputStream.write(writeData.getBytes());
                System.out.print(writeData);
            }
            System.out.println();
            pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}