package com.example.book.chapter04;

import static com.example.book.chapter04.Attributes.ADDRESS;
import static com.example.book.chapter04.Attributes.BODY;
import static com.example.book.chapter04.Attributes.PATIENT;
import static com.example.book.chapter04.Attributes.TYPE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LetterImporter implements Importer {

    private static final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, line -> line.startsWith("regards,"), BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
}
