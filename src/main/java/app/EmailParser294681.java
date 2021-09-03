package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class EmailParser294681 implements Parser {

    static private final Map<Integer, String> prefixes = new HashMap<>();

    static {
        prefixes.put(1, "FIELDS[TITLE]=");
        prefixes.put(2, "FIELDS[PHONE][0][VALUE]=");
        prefixes.put(3, "FIELDS[PHONE][0][VALUE_TYPE]=WORK");
        prefixes.put(4, "FIELDS[NAME]=");
        prefixes.put(5, "FIELDS[COMMENTS]=");
    }

    EmailParser294681() {

    }

    @Override
    public String parse(String stringToParse) {
        stringToParse = Objects.requireNonNull(stringToParse);

        final StringBuilder builder = new StringBuilder();

        final Scanner sc = initScannerFromString(stringToParse);

        int colonCount = 0;

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (colonCount == 5) {
                builder.append(str.trim());
                continue;
            }
            int indexOfColon = str.indexOf(":");
            if (indexOfColon != -1) {
                colonCount++;
                builder.append(prefixes.get(colonCount))
                        .append(
                        str.substring(indexOfColon + 1)
                                .trim()
                );
                if (colonCount == 2) {
                    colonCount++;
                    builder.append(
                            prefixes.get(colonCount)
                    );
                }
                if (colonCount != 5) {
                    builder.append("&");
                }
            }
        }


        return builder.toString();
    }
}
