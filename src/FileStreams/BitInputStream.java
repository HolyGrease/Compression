package FileStreams;

import java.io.FileInputStream;
import java.io.IOException;

public class BitInputStream {
    private static final int defaultBufferSize = 32;                // Default size of buffer
    private static final int minBufferSize = 4;                     // Min size of buffer
    private static final int byteSize = 8;                          // Size of byte in bits

    private final FileInputStream fileInputStream;                  // Input stream to read from file
    private final int bufferSize;                                   // Size of buffer
    private byte[] buffer;                                          // Buffer for data from file
    private int current;                                            // Index of current byte of buffer to read
                                                                    // (set which byte will be read next)
    private int n = byteSize;                                       // Number of bits read from byte
    private boolean endOfFile = false;
    private int left = -1;
    private int bitsInLastByte;

    public BitInputStream(String filename) throws IOException {
        this(filename, defaultBufferSize);
    }

    public BitInputStream(String filename, int bufferSize) throws IOException, IllegalArgumentException {
        this(new FileInputStream(filename), bufferSize);
    }

    public BitInputStream(FileInputStream file) throws IOException {
        this(file, defaultBufferSize);
    }

    public BitInputStream(FileInputStream file, int bufferSize) throws IllegalArgumentException, IOException {
        if (bufferSize < minBufferSize)
            throw new IllegalArgumentException("Buffer size can't be less than " + minBufferSize + "!");
        fileInputStream = file;
        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize];
        pushBuffer();
    }

    public boolean isEmpty(){

    }

    public boolean read() throws IOException {

    }

    public int read(int bits) throws IOException {

    }

    public void close() throws IOException {

    }

    private void onEndOfFile(int read) {

    }

    private boolean isByteRead(){

    }

    private boolean isBufferFullEnough() {

    }

    private void pushBuffer() throws IOException {

    }

    private boolean readFromBuffer() throws IOException {

    }
}
