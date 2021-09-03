package app;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

public class EmailParser implements Parser{
    @Override
    public String parse(String stringToParse) {

        stringToParse = Objects.requireNonNull(stringToParse);

        Scanner sc = initScannerFromString(stringToParse);

        Map<String, String> parsedString = new TreeMap<>();

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.contains(":")) {
                int indexOfColon = str.indexOf(":");
                String key = str.substring(0, indexOfColon);
                String value = str.substring(indexOfColon + 1);
                parsedString.put(key.trim(), value.trim());
            }
        }

        //return parsedString;
        return null;
    }

//    private Scanner initScannerFromString(String stringForScanner) {
//
//        InputStream inputStream = new ByteArrayInputStream(
//                stringForScanner.getBytes(/*StandardCharsets.UTF_8*/)
//        );
//
//        Reader reader = new BufferedReader(
//                new InputStreamReader(inputStream)
//        );
//
//        Scanner sc = new Scanner(reader);
//        return sc;
//    }
}
