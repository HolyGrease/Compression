package tests;

import FileStreams.BitOutputStream;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

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
            string = "0000000".substring(string.length()) + string;

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

    @Test
    public void write1MbFileBySingleBit() throws IOException {
        String filename = "src\\tests\\files\\write1MbFileBySingleBit.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 80000000; i++)
            output.write(true);

        output.close();
    }

    @Test
    public void write10MbFileBySingleBit() throws IOException {
        String filename = "src\\tests\\files\\write10MbFileBySingleBit.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 800000000; i++)
            output.write(true);

        output.close();
    }

    @Test
    public void write1MbFile() throws IOException {
        String filename = "src\\tests\\files\\write1MbFile.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 10000000; i++)
            output.write(127, 8);

        output.close();
    }

    @Test
    public void write10MbFile() throws IOException {
        String filename = "src\\tests\\files\\write10MbFile.test";
        BitOutputStream output = new BitOutputStream(filename);

        for (int i = 0; i < 100000000; i++)
            output.write(127, 8);

        output.close();
    }

    //@Test
    public void write100MbFile() throws IOException {
        String filename = "src\\tests\\files\\write100MbFile.test";
        BitOutputStream output = new BitOutputStream(filename, 2048);

        for (int i = 0; i < 1000000000; i++)
            output.write(127, 8);

        output.close();
    }
}