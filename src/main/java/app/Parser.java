package app;

import java.util.Map;

public interface Parser {
    Map<?, ?> parse(String stringToParse);
}
