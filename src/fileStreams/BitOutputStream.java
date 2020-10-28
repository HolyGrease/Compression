package fileStreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class BitOutputStream {
    private static final int defaultBufferSize = 512;               // Default size of buffer
    private static final int minBufferSize = 4;                     // Min size of buffer
    private static final int byteSize = 8;                          // Size of byte in bits

    private final FileOutputStream fileOutputStream;                // Output stream for write to file
    private final int bufferSize;                                   // Size of buffer
    private byte[] buffer;                                          // Buffer for data to write
    private int current = 0;                                        // Index of first empty buffer byte
                                                                    // (set where to write next byte)
    private int n = 0;                                              // Number of bits wrote to byte

    /**
     *
     * @param filepath - path to the file
     * @throws FileNotFoundException if can't find file
     * @throws IllegalArgumentException if bufferSize less than minBufferSize
     */
    public BitOutputStream(String filepath) throws FileNotFoundException {
        this(filepath, defaultBufferSize);
    }

    /**
     *
     * @param filepath - path to the file
     * @param bufferSize - size of buffer, in bytes
     * @throws FileNotFoundException if can't find file
     * @throws IllegalArgumentException if bufferSize less than minBufferSize
     */
    public BitOutputStream(String filepath, int bufferSize) throws FileNotFoundException, IllegalArgumentException {
        this(new FileOutputStream(filepath), bufferSize);
    }

    /**
     *
     * @param file - file
     * @throws IllegalArgumentException if bufferSize less than minBufferSize
     */
    public BitOutputStream(FileOutputStream file) {
        this(file, defaultBufferSize);
    }

    /**
     *
     * @param file - file
     * @param bufferSize - size of buffer, in bytes
     * @throws IllegalArgumentException if bufferSize less than minBufferSize
     */
    public BitOutputStream(FileOutputStream file, int bufferSize) throws IllegalArgumentException {
        if (bufferSize < minBufferSize)
            throw new IllegalArgumentException("Buffer size can't be less than " + minBufferSize + "!");
        fileOutputStream = file;
        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize];
    }

    /**
     * Function push single bit to buffer
     * @param bit - single bit (true - 1, false - 0)
     */
    public void write(boolean bit) throws IOException {
        pushBuffer(bit);
    }

    /**
     *  Function push list of bits to buffer
     * @param bits - list of bits
     */
    public void write(List<Boolean> bits) throws IOException {
        for (boolean bit: bits)
            pushBuffer(bit);
    }

    /**
     * Function push number to buffer
     * Number represents in binary code
     * Binary code expands to bitsNumber
     * by adding 0 to the beginning
     * @param number - number
     * @param bitsNumber - length of binary code
     * @throws IllegalArgumentException if number representation less then bitsNumber
     */
    public void write(int number, int bitsNumber) throws IllegalArgumentException, IOException {
        for (int i = 0; i < bitsNumber; i++) {
            boolean bit = ((number >>> (bitsNumber - i - 1)) & 1) == 1;
            pushBuffer(bit);
        }
    }

    /**
     * Flush buffer
     * Write information about the length of last binary code
     * (number of actual bits of last byte)
     * Close output stream
     */
    public void close() throws IOException {
        int len = n;

        if (n != 0)
            current++;
        if (current != 0)
            flushBuffer();

        if (len == 0)
            len = 8;
        fileOutputStream.write(len);
        fileOutputStream.close();
    }

    /**
     * Function add bit to buffer
     * @param b - bit to push to buffer
     */
    private void pushBuffer(boolean b) throws IOException {
        buffer[current] <<= 1;
        if (b) buffer[current] |= 1;
        n++;
        if (isByteFull()) {
            n = 0;
            current++;
        }
        if (isBufferFull())
            flushBuffer();
    }

    /**
     * Function check if a current byte
     * is full (contains 8 bits)
     * @return true if byte contains 8 bis, false - otherwise
     */
    private boolean isByteFull() {
        return n == byteSize;
    }

    /**
     * Function check if a current pointer
     * reaches max value(bufferSize)
     * @return true if buffer is full, false - otherwise
     */
    private boolean isBufferFull(){
        return current == bufferSize;
    }

    /**
     * Function write buffer to file
     */
    private void flushBuffer() throws IOException {
        fileOutputStream.write(buffer, 0, current);
        buffer = new byte[bufferSize];
        current = 0;
    }
}
