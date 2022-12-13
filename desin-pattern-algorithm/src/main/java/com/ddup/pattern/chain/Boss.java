package com.ddup.pattern.chain;

public class Boss extends AbstractHandler {
    @Override
    public void handleRequest(Integer request) {
        if (10 < request && request <= 30) {
            System.out.println("请假天数为【" + request + "】天，由老板审批");
        } else {
            System.out.println("请假天数太长，建议直接离职");
        }
    }
}
