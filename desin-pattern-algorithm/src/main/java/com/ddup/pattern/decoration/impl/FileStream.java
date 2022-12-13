package com.ddup.pattern.decoration.impl;

import com.ddup.pattern.decoration.Stream;

public class FileStream implements Stream {
    @Override
    public char read(int number) {
        // 读文件流
        return 0;
    }

    @Override
    public void seek(int position) {
        // 定位文件流
    }

    @Override
    public void write(char data) {
        // 写文件流
    }
}
