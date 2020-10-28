package tests;

import coder.decoder.Decoder;
import coder.encoder.Encoder;
import coder.encoder.LZW;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class LZWTest {

    boolean compareFiles(String first, String second) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(first));

        BufferedReader reader2 = new BufferedReader(new FileReader(second));

        String line1 = reader1.readLine();

        String line2 = reader2.readLine();
        while (line1 != null || line2 != null)
        {
            if(line1 == null || line2 == null)
            {
                reader1.close();
                reader2.close();
                return false;
            }
            else if(! line1.equalsIgnoreCase(line2))
            {
                reader1.close();
                reader2.close();
                return false;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }

        reader1.close();
        reader2.close();
        return true;
    }

    @Test
    void decodeSmallFile() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data1.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded1.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded1.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, compareFiles(datafile, decoded));
    }

    @Test
    void decodeBiggerFile() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data2.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded2.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded2.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, compareFiles(datafile, decoded));
    }

    //@Test
    void decodeVoynaIMir() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data3.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded3.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded3.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, compareFiles(datafile, decoded));
    }

    @Test
    void decodeWikiPage() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data4.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded4.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded4.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, compareFiles(datafile, decoded));
    }

    //@Test
    void decode10WikiPages() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data5.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded5.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded5.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, compareFiles(datafile, decoded));
    }

    @Test
    void decodeWikiPageTimeTest() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data6.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded6.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded6.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, true);
    }

    @Test
    void decode10WikiPagesTimeTest() throws IOException, ClassNotFoundException {
        String datafile = "src\\tests\\files\\LZW\\data7.txt";
        String codedfile = "src\\tests\\files\\LZW\\coded7.bin";
        String decoded = "src\\tests\\files\\LZW\\decoded7.txt";

        Encoder encoder = new LZW(datafile, codedfile);
        encoder.encode();

        Decoder decoder = new coder.decoder.LZW(codedfile, decoded);
        decoder.decode();

        Assert.assertEquals(true, true);
    }
}