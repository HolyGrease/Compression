package FileStreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 */
public class BitOutputStream {
    private final FileOutputStream fileOutputStream;    // output stream for write to file
    private final int bufferSize;                       // size of buffer
    private byte[] buffer;                              // buffer for data to write
    private String bitsQueue;                           // queue used for form the byte

    /**
     *
     * @param filepath - path to the file
     * @throws FileNotFoundException if can't find file
     * @throws IllegalArgumentException if number representation less then bitsNumber
     */
    public BitOutputStream(String filepath) throws FileNotFoundException, IllegalArgumentException {
        this(new FileOutputStream(filepath));
    }

    /**
     *
     * @param filepath - path to the file
     * @param bufferSize - size of buffer, in bytes
     * @throws FileNotFoundException if can't find file
     * @throws IllegalArgumentException if number representation less then bitsNumber
     */
    public BitOutputStream(String filepath, int bufferSize) throws FileNotFoundException, IllegalArgumentException {
        this(new FileOutputStream(filepath), bufferSize);
    }

    /**
     *
     * @param file - file
     * @throws IllegalArgumentException if number representation less then bitsNumber
     */
    public BitOutputStream(FileOutputStream file) throws IllegalArgumentException {
        this(file, 32);
    }

    /**
     *
     * @param file - file
     * @param bufferSize - size of buffer, in bytes
     * @throws IllegalArgumentException if number representation less then bitsNumber
     */
    public BitOutputStream(FileOutputStream file, int bufferSize) throws IllegalArgumentException {
        if (bufferSize < 4)
            throw new IllegalArgumentException("Buffer size can't be less than 4!");
        fileOutputStream = file;
        this.bufferSize = bufferSize;
    }

    /**
     * Function push single bit to queue
     * Then check queue for a formed byte
     * if byte is ready - push this byte to buffer
     * @param bit - single bit (true - 1, false - 0)
     */
    public void write(boolean bit){
    }

    /**
     *  Function push list of bits to queue
     *  Then check queue for a formed byte
     *  if byte is ready - push this byte to buffer
     * @param bits - list of bits
     */
    public void write(List<Boolean> bits){

    }

    /**
     * Function push number to queue
     * Number represents in binary code
     * Binary code expands to bitsNumber
     * by adding 0 to the beginning
     * @param number - number
     * @param bitsNumber - length of binary code
     * @throws IllegalArgumentException if number representation less then bitsNumber
     */
    public void write(int number, int bitsNumber) throws IllegalArgumentException {
    }

    /**
     * Flush queue and buffer
     * Write information about the length of last binary code
     * (number of actual bits of last byte)
     * Close output stream
     */
    public void close(){

    }

    /**
     * Function add single bit to queue
     * @param bit - bit to push to queue
     */
    private void pushQueue(boolean bit){

    }

    /**
     * Function add binary code to queue
     * @param bits - binary code represented as a string
     */
    private void pushQueue(String bits){

    }

    /**
     * Function check if a queue contains full byte
     * @return true if byte is ready, false - otherwise
     */
    private boolean isQueueFull(){
        return false;
    }

    /**
     * Function convert binary code to byte
     * @param bits - binary code
     * @return binary code converted to byte
     */
    private byte convert(String bits){
        return 0;
    }

    /**
     * Function move full byte to buffer
     */
    private void flushQueue(){

    }

    /**
     * Function add byte to buffer
     * @param b - byte to push to buffer
     */
    private void pushBuffer(byte b){

    }

    /**
     * Function check if a buffer length
     * reaches max value(bufferSize)
     * @return true if buffer is full, false - otherwise
     */
    private boolean isBufferFull(){ return  false;}

    /**
     * Function write buffer to file
     */
    private void flushBuffer(){

    }
}
