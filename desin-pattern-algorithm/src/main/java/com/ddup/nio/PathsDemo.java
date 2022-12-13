package com.ddup.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsDemo {
    public static void main(String[] args) {
        Path path = Paths.get("D:/work");
        try (DirectoryStream<Path> streams = Files.newDirectoryStream(path, "*.zip")) {
            for (Path stream : streams) {
                System.out.println(stream.getFileName());
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
