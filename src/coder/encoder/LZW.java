package coder.encoder;

import fileStreams.BitOutputStream;

import java.io.*;
import java.util.*;

public class LZW implements Encoder {
    private InputStreamReader inputStreamReader;
    private FileOutputStream fileOutputStream;
    private BitOutputStream bitOutputStream;
    private Map<List<Integer>, Integer> map;

    public LZW(String inputfile, String outputfile) throws IOException {
        inputStreamReader = new InputStreamReader(new FileInputStream(inputfile), "UTF-8");
        // Open output file for writing
        fileOutputStream = new FileOutputStream(outputfile);
        bitOutputStream = new BitOutputStream(fileOutputStream);
        // Create dictionary
        map = new LinkedHashMap<>();
        // Init dictionary
        makeMap();
        // Reopenfile
        inputStreamReader = new InputStreamReader(new FileInputStream(inputfile), "UTF-8");
    }

    /**
     * Function generate dictionary alphabet for file
     */
    private void makeMap() throws IOException {
        // Add to dictionary all possible characters
        // Add end of message marker
        map.put(new LinkedList<>(), 0);
        int character;
        // If reach end of file - end loop
        while ((character = inputStreamReader.read()) != -1){
            // If dictionary don`t contains this character
            // Add character to dictionary as key
            // Value equals index number
            if (!map.containsKey(Collections.singletonList(character))){
                map.put(Collections.singletonList(character), map.size());
            }
        }
        // Close datafile and then open it again
        // To set cursor to begin of file
        inputStreamReader.close();
    }

    /**
     * Function save dictionary to file
     * @throws IOException
     */
    private void saveMap() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(map);
    }

    /**
     * Function calculate length in bits
     * in which number should be write
     * @param length - current length
     * @return new length
     */
    private int bitLength(int length){
        while (Math.pow(2, length) - 1 <= map.size())
            length++;
        return length;
    }

    @Override
    public void encode() throws IOException {
        // Save to file current dictionary
        saveMap();
        //
        List<Integer> phrase = new LinkedList<>();
        int character;
        int code = 0;
        int length = bitLength(4);
        // Read char from file
        // If reach end of file - end loop
        while((character = inputStreamReader.read()) != -1) {
            phrase.add(character);

            if(!map.containsKey(phrase)){
                // Add new phrase to dictionary
                map.put(new LinkedList<>(phrase), map.size());
                // Write
                bitOutputStream.write(code, length);
                //
                length = bitLength(length);
                // Next phrase begins with current character
                phrase = new LinkedList<>(Collections.singleton(character));
            }
            // Remember code of phrase
            code = map.get(phrase);
        }
        // Write last character
        bitOutputStream.write(map.get(phrase), length);
        // Write end of message
        bitOutputStream.write(0, length);
        bitOutputStream.close();
    }
}
