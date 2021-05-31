package com.ddup.pattern.chain;

/**
 * 责任链设计模式（Chain of Responsibility Pattern）
 */

public abstract class AbstractHandler {
    protected AbstractHandler subHandler;

    public void setSubHandler(AbstractHandler subHandler) {
        this.subHandler = subHandler;
    }

    public abstract void handleRequest(Integer request);
}
