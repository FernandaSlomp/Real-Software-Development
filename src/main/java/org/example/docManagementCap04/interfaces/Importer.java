package org.example.docManagementCap04.interfaces;

import org.example.docManagementCap04.model.Document;

import java.io.File;
import java.io.IOException;

public interface Importer {

    Document importFile(File file) throws IOException;

}
