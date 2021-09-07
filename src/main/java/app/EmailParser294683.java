package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class EmailParser294683 implements Parser {

    static private final Map<Integer, String> prefixes = new HashMap<>();

    static {
        prefixes.put(1, "Сделка:Название=");
        prefixes.put(2, "Контакт:Мобильный телефон=");
        prefixes.put(3, "Сделка:Комментарий=");
    }

    EmailParser294683() {

    }

    @Override
    public String parse(String stringToParse) {
        stringToParse = Objects.requireNonNull(stringToParse);

        final StringBuilder builder = new StringBuilder();

        final Scanner sc = initScannerFromString(stringToParse);

        int colonCount = 0;

        while (sc.hasNextLine() && (colonCount < 3)) {
            String str = sc.nextLine();
            int indexOfColon = str.indexOf(":");
            if (indexOfColon != -1) {
                colonCount++;
                builder.append(prefixes.get(colonCount))
                        .append(
                                str.substring(indexOfColon + 1)
                                        .trim()
                        );
                if (colonCount != 3) {
                    builder.append("&");
                }
            }
        }

        return builder.toString();
    }
}
