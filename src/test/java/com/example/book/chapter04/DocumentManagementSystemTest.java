package com.example.book.chapter04;

import static com.example.book.chapter04.Attributes.AMOUNT;
import static com.example.book.chapter04.Attributes.HEIGHT;
import static com.example.book.chapter04.Attributes.PATH;
import static com.example.book.chapter04.Attributes.PATIENT;
import static com.example.book.chapter04.Attributes.TYPE;
import static com.example.book.chapter04.Attributes.WIDTH;
import static java.io.File.separator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class DocumentManagementSystemTest {

    private static final String RESOURCES =
        "src" + separator + "test" + separator + "resources" + separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String JOE_BLOGGS = "Joe Bloggs";

    private DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    void shouldImportFile() throws IOException {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATH, LETTER);
    }

    @Test
    void shouldImportLetterAttributes() throws IOException {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
    }

    @Test
    void shouldImportImageAttributes() throws IOException {
        system.importFile(XRAY);

        final Document document = onlyDocument();
        assertAttributeEquals(document, WIDTH, "1600");
        assertAttributeEquals(document, HEIGHT, "915");
        assertTypeIs("IMAGE", document);
    }

    @Test
    void shouldNotImportMissingFile() {
        assertThrows(FileNotFoundException.class,
            () -> system.importFile("not_exists.txt")
        );
    }

    @Test
    void shouldNotImportUnknownFile() {
        assertThrows(UnknownFileTypeException.class,
            () -> system.importFile(RESOURCES + "unknown.txt")
        );
    }

    @Test
    void shouldImportInvoiceAttributes() throws IOException {
        system.importFile(INVOICE);

        Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, AMOUNT, "$100");
        assertTypeIs("INVOICE", document);
    }

    private void assertTypeIs(String type, Document document) {
        assertAttributeEquals(document, TYPE, type);

    }

    private void assertAttributeEquals(Document document, String attributeName, String expectedValue) {
        assertEquals(
            expectedValue,
            document.getAttribute(attributeName),
            "Document has the wrong value for " + attributeName
        );
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents).hasSize(1);
        return documents.get(0);
    }
}