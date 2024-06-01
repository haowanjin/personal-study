package com.ddup.spring.test;

import java.io.*;
import java.util.Scanner;

public class ExecuteCommandWithPassword {
    public static void main(String[] args) throws IOException, InterruptedException {
        String command = "your_command"; // 替换为你想要执行的 Linux 命令
        
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(new File("/path/to/working/dir")); // 设置工作目录
        processBuilder.command("bash", "-c", command + " < /dev/tty"); // 重定向标准输入到 tty
        
        Process process = processBuilder.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()), true);
        
        System.out.println("Enter password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        
        writer.write(password);
        writer.flush();
        
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Command executed successfully.");
            System.out.println("Output:\n" + output.toString());
        } else {
            System.err.println("Command execution failed with error code: " + exitCode);
        }
    }
}