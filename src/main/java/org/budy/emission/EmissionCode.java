package org.budy.emission;

import org.budy.assembler.nodes.AssemblyProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EmissionCode {
    private final EmissionCodeVisitor emissionCodeVisitor = new EmissionCodeVisitor();
    public void createAssemblyFile(AssemblyProgram program, Path path) {
        Path fileName = Path.of(path.getFileName().toString().replaceFirst("\\.c$",".s"));
        Path parentPath = path.getParent();
        if (parentPath == null) {
            throw new IllegalArgumentException("O caminho especificado não possui um diretório pai válido.");
        }
        Path newPath = parentPath.resolve(fileName).toAbsolutePath();

        String assemblyCode = emissionCodeVisitor.visit(program);
        System.out.println("//////////////////////");
        System.out.println(assemblyCode);
        try {
            Files.writeString(newPath, assemblyCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
