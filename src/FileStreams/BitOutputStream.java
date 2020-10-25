package FileStreams;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 */
public class BitOutputStream {
    private FileOutputStream fileOutputStream;
    private int bufferSize;
    private byte[] buffer;
    private String bitsQueue;

    /**
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public BitOutputStream(String filename) throws FileNotFoundException {
        this(filename, 32);
    }

    /**
     *
     * @param filename
     * @param bufferSize
     * @throws FileNotFoundException
     */
    public BitOutputStream(String filename, int bufferSize) throws FileNotFoundException {
        fileOutputStream = new FileOutputStream(filename);
        this.bufferSize = bufferSize;
    }

    /**
     *
     * @param bit
     */
    public void write(boolean bit){
    }

    /**
     *
     * @param bits
     */
    public void write(List<Boolean> bits){

    }

    /**
     *
     * @param number
     * @param bitsNumber
     */
    public void write(int number, int bitsNumber){

    }

    /**
     *
     */
    public void close(){

    }

    /**
     *
     * @param bit
     */
    private void pushQueue(boolean bit){

    }

    /**
     *
     * @param bits
     */
    private void pushQueue(String bits){

    }

    /**
     *
     * @return
     */
    private boolean isQueueFull(){
        return false;
    }

    /**
     *
     */
    private void pushBuffer(){

    }

    /**
     *
     * @return
     */
    private boolean isBufferFull(){ return  false;}

    /**
     *
     */
    private void flushBuffer(){

    }
}
