package FileStreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BitInputStream {
    private static final int defaultBufferSize = 32;                // Default size of buffer
    private static final int minBufferSize = 4;                     // Min size of buffer

    private final FileInputStream fileInputStream;                  // Input stream to read from file
    private final int bufferSize;                                   // Size of buffer
    private byte[] buffer;                                          // Buffer for data from file
    private int next;                                               // Index of next byte of buffer to read
                                                                    // (set which byte will be read next)
    private String bitsQueue;                                       // Queue used for form bits from byte

    public BitInputStream(String filename) throws FileNotFoundException {
        this(filename, defaultBufferSize);
    }

    public BitInputStream(String filename, int bufferSize) throws FileNotFoundException, IllegalArgumentException {
        this(new FileInputStream(filename), bufferSize);
    }

    public BitInputStream(FileInputStream file) {
        this(file, defaultBufferSize);
    }

    public BitInputStream(FileInputStream file, int bufferSize) throws IllegalArgumentException {
        if (bufferSize < minBufferSize)
            throw new IllegalArgumentException("Buffer size can't be less than " + minBufferSize + "!");
        fileInputStream = file;
        this.bufferSize = bufferSize;
    }

    public boolean isEndOfFile(){
        return true;
    }

    public boolean read(){
        return false;
    }

    public int read(int bits){
        return 0;
    }

    public void close(){

    }

    private boolean isQueueFullEnough(int bits){
        return false;
    }

    private void pushQueue(){

    }

    private boolean isBufferFullEnough(){
        return false;
    }

    private void pushBuffer(){

    }
}
