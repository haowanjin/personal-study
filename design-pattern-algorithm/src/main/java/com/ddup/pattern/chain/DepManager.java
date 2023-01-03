package com.ddup.pattern.chain;

public class DepManager extends AbstractHandler {
    @Override
    public void handleRequest(Integer request) {
        if (request <= 10) {
            System.out.println("请假天数为【" + request + "】天，由部门经理审批");
        } else {
            this.subHandler.handleRequest(request);
        }
    }
}
