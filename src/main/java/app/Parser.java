package app;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public interface Parser {
    String parse(String stringToParse);

    default Scanner initScannerFromString(String stringForScanner) {

        InputStream inputStream = new ByteArrayInputStream(
                stringForScanner.getBytes(/*StandardCharsets.UTF_8*/)
        );

        Reader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        return new Scanner(reader);
    }
}
