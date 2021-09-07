package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class EmailParser294673 implements Parser {

    static private final Map<Integer, String> prefixes = new HashMap<>();

    static {
        prefixes.put(1, "Сделка:Название=");
        prefixes.put(2, "Сделка:Товар=");
        prefixes.put(3, "Контакт:Мобильный телефон=");
        prefixes.put(4, "Сделка:Артикул=");
        prefixes.put(5, "Сделка:Комментарий=");
    }

    EmailParser294673() {

    }

    @Override
    public String parse(String stringToParse) {
        stringToParse = Objects.requireNonNull(stringToParse);

        final StringBuilder builder = new StringBuilder();

        final Scanner sc = initScannerFromString(stringToParse);

        int colonCount = 0;

        while (sc.hasNextLine()) {
            String name = sc.nextLine();
            if (!name.isEmpty()) {
                colonCount++;
                builder.append(prefixes.get(colonCount))
                        .append(name.trim())
                        .append("&");
                break;
            }
        }

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (colonCount == 5) {
                if (Character.isDigit(str.charAt(0))) {
                    break;
                }
                builder.append(str.trim())
                        .append(";");
                continue;
            }
            int indexOfColon = str.indexOf(":");
            if (indexOfColon != -1) {
                colonCount++;
                builder.append(prefixes.get(colonCount));
                if (colonCount == 5) {
                    continue;
                }
                if (colonCount == 2) {
                    str = str.substring(indexOfColon + 1).trim();
                    int indexOfSecColon = str.indexOf(":");
                    while (!Character.isSpaceChar(str.charAt(indexOfSecColon))) {
                        indexOfSecColon--;
                    }
                    builder.append(
                            str.substring(0, indexOfSecColon)
                            .trim()
                    );
                } else {
                    builder.append(
                            str.substring(indexOfColon + 1)
                            .trim()
                    );
                }
            }
            if (colonCount != 5) {
                builder.append("&");
            }
        }

        if (colonCount != 5) {
            throw new IllegalArgumentException("Email is not correctness");
        }

        return builder.toString();
    }
}
