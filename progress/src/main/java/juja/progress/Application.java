package juja.progress;

import java.io.IOException;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public interface Application {

    void start() throws IOException;

    void stop() throws IOException;
}
