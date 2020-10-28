package coder.decoder;

import fileStreams.BitInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LZW implements Decoder {
    private FileInputStream fileInputStream;
    private BitInputStream bitInputStream;
    private FileOutputStream fileOutputStream;
    private BufferedWriter bufferedWriter;
    private OutputStreamWriter outputStreamWriter;
    private Map<Integer, List<Integer>> map;

    public LZW(String inputfile, String outputfile) throws IOException, ClassNotFoundException {
        // Open datafile for reading
        fileInputStream = new FileInputStream(inputfile);
        // Open output file for writing
        fileOutputStream = new FileOutputStream(outputfile);
        outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        bufferedWriter = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(outputfile), StandardCharsets.UTF_8));
        getMap();
        bitInputStream = new BitInputStream(fileInputStream);
    }

    private void getMap() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Map<List<Integer>, Integer> m = (Map<List<Integer>, Integer>) objectInputStream.readObject();
        map = m.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    private void write(List<Integer> phrase) throws IOException {
        for (int character: phrase){
            // If you want to see decoding process
            //System.out.print((char)(character));
            bufferedWriter.write((char)(character));
        }
    }

    private int bitLength(int length){
        while (Math.pow(2, length) - 2 <= map.size())
            length++;
        return length;
    }

    @Override
    public void decode() throws IOException {
        List<Integer> phrase = new LinkedList<>();
        int code;
        int length = bitLength(4);
        // Read integer from file
        // If reach end of file - end loop
        while ((code = bitInputStream.read(length)) != 0){
            // Handle the degenerate case
            /*
            if (!map.containsKey(code)){
                phrase.add(phrase.get(0));
                map.put(map.size(), phrase);


                phrase = new LinkedList<>(map.get(code));
                write(map.get(code));
                //
                length = bitLength(length);
                continue;
            }

             */

            phrase.add(map.get(code).get(0));

            write(map.get(code));
            // If phrase not in dictionary
            if (!map.containsValue(phrase)){
               // Add to dictionary this phrase
               map.put(map.size(), new LinkedList<>(phrase));
               //
               length = bitLength(length);
               // New phrase with current character
               phrase = new LinkedList<>(map.get(code));
            }
        }
        bufferedWriter.close();
    }

    @Override
    public String toString() {
        return "LZW{" +
                "map=" + map +
                '}';
    }
}
