package com.ddup.io;

import java.io.*;

public class ReadIoStudy {
    public static void main(String[] args) throws Exception {
        String fileName = "E:/documents/books/Java工程师成神之路.pdf";
//        String fileName = "C:/Users/haowanjin/Pictures/Saved Pictures/background.jpg";
        testFileInputStream(fileName);
    }

    public static void testFileInputReader(String fileName) throws Exception {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fileReader.close();
    }

    public static void testFileInputStream(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream br = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream("E:/documents/test.pdf");
        BufferedOutputStream bw = new BufferedOutputStream(fos);
        byte[] buffer = new byte[1024];
        int line;
        while ((line = br.read(buffer, 0, buffer.length)) != -1) {
            bw.write(buffer, 0, line);
        }
        br.close();
        fis.close();
        bw.close();
        fos.close();
    }

}
