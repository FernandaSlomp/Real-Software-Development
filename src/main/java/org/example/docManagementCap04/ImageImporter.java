package org.example.docManagementCap04;

import org.example.docManagementCap04.interfaces.Importer;
import org.example.docManagementCap04.model.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.example.docManagementCap04.constants.Attributes.*;

public class ImageImporter implements Importer {

    @Override
    public Document importFile(File file) throws IOException {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());

        final BufferedImage image = ImageIO.read(file);
        attributes.put(WIDTH, String.valueOf(image.getWidth()));
        attributes.put(HEIGHT, String.valueOf(image.getHeight()));
        attributes.put(TYPE, "IMAGE");

        return new Document(attributes);

    }
}
