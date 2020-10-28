package tests;

import fileStreams.BitOutputStream;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;

class BitOutputStreamTest {

    @Test
    public void writeByteByBits() throws IOException {
        String filename = "src\\tests\\files\\writeBytesByBits.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected = 127;
        String string = Integer.toBinaryString(expected);

        for (int i = 0; i < string.length(); i++)
            output.write(string.charAt(i) == '1');

        output.close();

        FileInputStream input = new FileInputStream(filename);
        int actual = input.read();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeSomeBytesByBits() throws IOException {
        String filename = "src\\tests\\files\\writeSomeBytessByBits.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 127};

        for (byte b: expected){
            String string = Integer.toBinaryString(b);
            string = "00000000".substring(string.length()) + string;

            for (int i = 0; i < string.length(); i++)
                output.write(string.charAt(i) == '1');
        }
        output.close();

        FileInputStream input = new FileInputStream(filename);
        byte[] actual = new byte[expected.length];

        input.read(actual);

        for (int i = 0; i < expected.length; i++)
            Assert.assertEquals(expected[i], actual[i]);
    }

    @Test
    public void writeNumberByBits() throws IOException {
        String filename = "src\\tests\\files\\writeNumberByBits.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected = 127;
        int bitsNumber = 7;
        String string = Integer.toBinaryString(expected);

        output.write(expected, bitsNumber);

        output.close();

        FileInputStream input = new FileInputStream(filename);
        int actual = input.read();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeNumberInBiggerBits() throws IOException {
        String filename = "src\\tests\\files\\writeNumberInBiggerBits.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected1 = 1;
        byte expected2 = 63;
        int bitsNumber = 14;
        byte number = 127;

        output.write(number, bitsNumber);

        output.close();

        FileInputStream input = new FileInputStream(filename);
        int actual1 = input.read();
        int actual2 = input.read();

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void writeSomeNumbersByBits() throws IOException {
        String filename = "src\\tests\\files\\writeSomeNumberByBits.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 127};

        for (byte b: expected)
            output.write(b,8);
        output.close();

        FileInputStream input = new FileInputStream(filename);
        byte[] actual = new byte[expected.length];

        input.read(actual);

        for (int i = 0; i < expected.length; i++)
            Assert.assertEquals(expected[i], actual[i]);
    }

    @Test void writeSingleByte() throws IOException {
        String filename = "src\\tests\\files\\writeSingleByte.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected1 = 127;
        byte expected2 = 8;
        byte expected3 = -1;
        int bitsNumber = 8;
        byte number = 127;

        output.write(number, bitsNumber);

        output.close();

        FileInputStream input = new FileInputStream(filename);
        int actual1 = input.read();
        int actual2 = input.read();
        int actual3 = input.read();

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);
    }

    @Test
    public void bitsToRead() throws IOException {
        String filename = "src\\tests\\files\\bitsToRead.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected = 7;
        int bitsNumber = 7;
        byte number = 63;

        output.write(number, bitsNumber);

        output.close();

        FileInputStream input = new FileInputStream(filename);
        input.read();
        int actual = input.read();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void write1MbFileBySingleBit() throws IOException {
        String filename = "src\\tests\\files\\write1MbFileBySingleBit.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 8000000; i++)
            output.write(true);

        output.close();
    }

    @Test
    public void write10MbFileBySingleBit() throws IOException {
        String filename = "src\\tests\\files\\write10MbFileBySingleBit.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 80000000; i++)
            output.write(true);

        output.close();
    }

    @Test
    public void write1MbFile() throws IOException {
        String filename = "src\\tests\\files\\write1MbFile.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 1000000; i++){
            //System.out.println(i);
            output.write(127, 8);
        }

        output.close();
    }

    @Test
    public void write10MbFile() throws IOException {
        String filename = "src\\tests\\files\\write10MbFile.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 10000000; i++)
            output.write(127, 8);

        output.close();
    }

    @Test
    public void write100MbFile() throws IOException {
        String filename = "src\\tests\\files\\write100MbFile.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 100000000; i++)
            output.write(127, 8);

        output.close();
    }
}