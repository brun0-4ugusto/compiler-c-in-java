package org.budy.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReaderCFile {
    public static char[] read(Path filePath) throws IOException {
        return Files.readString(filePath).toCharArray();
    }
}
