package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "temp.txt";

        byte[] a = {-128, -1, 2, 3, 127};

        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write(a);
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(filename);
        byte[] b = new byte[5];
        fileInputStream.read(b);
        fileInputStream.close();

        for (byte value : b) System.out.println(value);

        byte some = 0;
        int number = 128;
        byte another;
        another = (byte)(some | number);
        System.out.println(Integer.toBinaryString(some));
        System.out.println(Integer.toBinaryString(another));
        // Get byte from binary string
        byte c = Byte.parseByte("0011111", 2);
        System.out.println(c);
        // Get binary string from byte
        String string = Integer.toBinaryString(c);
        string = "0000000".substring(string.length()) + string;
        System.out.println(string);
    }
}
