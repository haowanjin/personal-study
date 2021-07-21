package com.ddup.pattern.decoration.impl;

import com.ddup.pattern.decoration.Stream;

public class BufferedStream extends DecoratorStream {

    public BufferedStream(Stream stream) {
        super(stream);
    }

    @Override
    public char read(int number) {
        return stream.read(number);
    }

    @Override
    public void seek(int position) {
        stream.seek(position);
    }

    @Override
    public void write(char data) {
        stream.seek(data);
    }
}
