package fileStreams;

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
    private boolean endOfFile = false;                              // Flag of getting to end of file
    private int left = -1;                                          // Bytes left to read
                                                                    // (init when find end of file)
    private int bitsInLastByte;                                     // Number of bits in last byte

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

    /**
     * Function
     * @return
     */
    public boolean isEmpty(){
        return endOfFile && current == left;
    }

    /**
     * Function read from buffer single bit
     * @return bit, if 1 - true, 0 - fasle
     * @throws IOException
     */
    public boolean read() throws IOException {
        return readFromBuffer();
    }

    /**
     *  Function read some bits from buffer
     *  and convert them to integer
     * @param bits - number of bits to read
     * @return number
     * @throws IOException
     */
    public int read(int bits) throws IOException {
        if (isEmpty())
            return -1;

        int number = 0;
        for (int i = 0; i < bits; i++){
            boolean bit = readFromBuffer();
            number <<= 1;
            if (bit) number |= 1;
        }
        return number;
    }

    /**
     *
     * @throws IOException
     */
    public void close() throws IOException {
        fileInputStream.close();
    }

    /**
     *
     * @param read
     */
    private void onEndOfFile(int read) {
        endOfFile = true;
        if (current == 0)
            left = read - 1;
        else
            left = read - 1 + bufferSize - current;
        bitsInLastByte = buffer[left];
    }

    /**
     *
     * @return
     */
    private boolean isByteRead(){
        return n == 0;
    }

    /**
     *
     * @return
     */
    private boolean isBufferFullEnough() {
        return (bufferSize - current > 3) || endOfFile;
    }

    /**
     * Function fill buffer by
     * reading data from file
     * @throws IOException
     */
    private void pushBuffer() throws IOException {
        int read;
        if (current == 0)
            read = fileInputStream.read(buffer, current, bufferSize - current);
        else{
            System.arraycopy(buffer, current, buffer, 0, bufferSize - current);
            read = fileInputStream.read(buffer, bufferSize - current, current);
        }

        if (fileInputStream.available() == 0)
            onEndOfFile(read);

        current = 0;
    }

    /**
     * Function get from buffer single bit
     * @return bit
     * @throws IOException
     */
    private boolean readFromBuffer() throws IOException {
        if (!isBufferFullEnough())
            pushBuffer();

        n--;
        boolean bit = ((buffer[current] >> n) & 1) == 1;

        if (isByteRead()) {
            n = byteSize;
            current++;
        }

        if (current == left)
            n = bitsInLastByte;

        return bit;
    }
}
