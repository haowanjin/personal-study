package com.ddup.pattern.decoration;

public interface Stream {
    char read(int number);

    void seek(int position);

    void write(char data);
}
