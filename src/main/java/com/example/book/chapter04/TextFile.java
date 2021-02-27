package com.example.book.chapter04;

import static com.example.book.chapter04.Attributes.PATH;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TextFile {

    private final Map<String, String> attributes;
    private final List<String> lines;

    TextFile(File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }

    Map<String, String> getAttributes() {
        return this.attributes;
    }

    void addLineSuffix(final String prefix, final String attributeName) {
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }

    /**
     *
     * @param start
     *      시작할 행의 숫자를 가리키는 인덱스
     * @param isEnd
     *      마지막 행에 도달하면 true를 만환한다.
     * @param attributeName
     *      현재값과 연결할 속성명
     * @return
     */
    int addLines(int start, Predicate<String> isEnd, String attributeName) {

        final StringBuilder accumulator = new StringBuilder();
        int lineNumber;
        for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
            final String line = lines.get(lineNumber);
            if (isEnd.test(line)) {
                break;
            }

            accumulator.append(line);
            accumulator.append("\n");
        }
        attributes.put(attributeName, accumulator.toString().trim());
        return lineNumber;
    }
}
