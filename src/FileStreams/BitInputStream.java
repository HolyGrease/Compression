package FileStreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BitInputStream {
    private final FileInputStream fileInputStream;      //
    private final int bufferSize;                       //
    private byte[] buffer;                              //
    private String bitsQueue;                           //

    public BitInputStream(String filename) throws FileNotFoundException {
        this(new FileInputStream(filename));
    }

    public BitInputStream(String filename, int bufferSize) throws FileNotFoundException {
        this(new FileInputStream(filename), bufferSize);
    }

    public BitInputStream(FileInputStream file){
        this(file, 32);
    }

    public BitInputStream(FileInputStream file, int bufferSize) throws IllegalArgumentException {
        if (bufferSize < 4)
            throw new IllegalArgumentException("Buffer size can't be less than 4!");
        fileInputStream = file;
        this.bufferSize = bufferSize;
    }
}
