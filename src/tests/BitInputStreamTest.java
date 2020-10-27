package tests;

import FileStreams.BitInputStream;
import FileStreams.BitOutputStream;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class BitInputStreamTest {

    @Test
    public void readSingleByte() throws IOException {
        String filename = "src\\tests\\files\\readSingleByte.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected = 56;

        output.write(expected, 8);
        output.close();

        BitInputStream input = new BitInputStream(filename);

        int actual = 0;

        for (int i = 0; i < 8; i++){
            boolean bit = input.read();
            actual <<= 1;
            if (bit) actual |= 1;
        }
        System.out.println(Integer.toBinaryString(expected));
        System.out.println(Integer.toBinaryString(actual));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readInt() throws IOException {
        String filename = "src\\tests\\files\\readInt.test";
        BitOutputStream output = new BitOutputStream(filename);
        int expected = 256;

        output.write(expected, 17);
        output.close();

        BitInputStream input = new BitInputStream(filename);

        int actual = 0;

        for (int i = 0; i < 17; i++){
            boolean bit = input.read();
            actual <<= 1;
            if (bit) actual |= 1;
        }
        System.out.println(Integer.toBinaryString(expected));
        System.out.println(Integer.toBinaryString(actual));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void someBuffers() throws IOException {
        String filename = "src\\tests\\files\\someBuffers.test";
        BitOutputStream output = new BitOutputStream(filename);
        int[] expected = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

        for (int x: expected)
            output.write(x, 8);
        output.close();

        BitInputStream input = new BitInputStream(filename, 4);

        int[] actual = new int[8];
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++){
                boolean bit = input.read();
                actual[j] <<= 1;
                if (bit) actual[j] |= 1;
            }

        for (int i = 0; i < expected.length; i++)
            Assert.assertEquals(expected[i], actual[i]);
    }

    @Test
    public void readWhileNotEmpty() throws IOException {
        String filename = "src\\tests\\files\\someBuffers.test";
        BitOutputStream output = new BitOutputStream(filename);
        int[] expected = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

        for (int x: expected)
            output.write(x, 8);
        output.close();

        BitInputStream input = new BitInputStream(filename, 4);

        int[] actual = new int[8];
        for (int j = 0; !input.isEmpty(); j++)
            for (int i = 0; i < 8; i++){
                boolean bit = input.read();
                actual[j] <<= 1;
                if (bit) actual[j] |= 1;
            }

        for (int x: expected)
            System.out.print(x + " ");

        System.out.println();

        for (int x: actual)
            System.out.print(x + " ");

        for (int i = 0; i < expected.length; i++)
            Assert.assertEquals(expected[i], actual[i]);
    }

    @Test
    public void readLessThanByte() throws IOException {
        String filename = "src\\tests\\files\\readSingleByte.test";
        BitOutputStream output = new BitOutputStream(filename);
        byte expected = 1;

        output.write(5, 1);
        output.close();

        BitInputStream input = new BitInputStream(filename);

        int actual = 0;

        for (int i = 0; !input.isEmpty(); i++){
            boolean bit = input.read();
            actual <<= 1;
            if (bit) actual |= 1;
        }
        System.out.println(Integer.toBinaryString(expected));
        System.out.println(Integer.toBinaryString(actual));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read1MbFile() throws IOException {
        String filename = "src\\tests\\files\\write1MbFile.test";
        BitInputStream input = new BitInputStream(filename);

        while (!input.isEmpty())
            input.read();

        input.close();
    }

    @Test
    public void read10MbFile() throws IOException {
        String filename = "src\\tests\\files\\write10MbFile.test";
        BitInputStream input = new BitInputStream(filename);

        while (!input.isEmpty())
            input.read();

        input.close();
    }
}