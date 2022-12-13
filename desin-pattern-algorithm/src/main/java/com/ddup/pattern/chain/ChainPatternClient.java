package com.ddup.pattern.chain;

public class ChainPatternClient {
    public static void main(String[] args) {
        // AbstractHandler director = new Director();
        AbstractHandler depManager = new DepManager();
        AbstractHandler boss = new Boss();
        //director.setSubHandler(depManager);
        depManager.setSubHandler(boss);

        depManager.handleRequest(2);
        depManager.handleRequest(7);
        depManager.handleRequest(20);
        depManager.handleRequest(60);
    }
}
