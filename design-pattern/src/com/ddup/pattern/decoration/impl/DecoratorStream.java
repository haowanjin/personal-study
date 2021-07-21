package com.ddup.pattern.decoration.impl;

import com.ddup.pattern.decoration.Stream;

public abstract class DecoratorStream implements Stream {
    protected Stream stream;

    public DecoratorStream(Stream stream) {
        this.stream = stream;
    }
}
