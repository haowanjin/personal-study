package com.ddup.pattern.chain;

public class Director extends AbstractHandler {
    @Override
    public void handleRequest(Integer request) {
        if (request <= 3) {
            System.out.println("请假天数为【" + request + "】天，由主管直接审批");
        } else {
            this.subHandler.handleRequest(request);
        }
    }
}
