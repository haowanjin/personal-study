package com.ddup.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTreeDemo {
    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("D:/work");
        Files.walkFileTree(path, new MyFileVisitor());
    }

    public static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            super.visitFile(file, attrs);
            if (file.toString().endsWith(".zip")) {
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
