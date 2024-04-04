package org.example.docManagementCap04;

import org.example.docManagementCap04.interfaces.Importer;
import org.example.docManagementCap04.model.Document;
import org.example.docManagementCap04.model.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.example.docManagementCap04.constants.Attributes.*;

class ReportImporter implements Importer {
    private static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line -> false, BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }
}
