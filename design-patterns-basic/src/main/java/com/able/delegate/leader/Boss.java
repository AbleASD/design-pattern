package com.able.delegate.leader;

public class Boss {
    static void command(String command, Leader leader) {
        leader.doSomething(command);
    }

    public static void main(String[] args) {
        Leader leader = new Leader();
        Boss.command("加密", leader);
        Boss.command("登录", leader);
    }
}
