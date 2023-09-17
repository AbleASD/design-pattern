package com.able.chain.responsibility.demo3;

import java.util.LinkedList;
import java.util.List;

public class ChainDemo {
    public static void main(String[] args) {
        DefaultExecutorChain chain = new DefaultExecutorChain();

        chain.addExecutor(new Executor() {
            @Override
            public void run(ExecutorChain c) {
                System.out.println("Hello World");
                c.run();
            }
        }).addExecutor(new Executor() {
            @Override
            public void run(ExecutorChain c) {
                System.out.println("Hello Able");
                c.run();
            }
        });
        chain.run();
    }



    public static class DefaultExecutorChain implements ExecutorChain {
        private final List<Executor> executors = new LinkedList<Executor>();

        private int index = 0;

        @Override
        public ExecutorChain addExecutor(Executor executor) {
            this.executors.add(executor);
            return this;
        }

        @Override
        public void run() {
            if (index == executors.size())
                return;

            int pos = index;
            Executor executor = executors.get(pos);

            System.out.println("执行第" + pos + "个Executor");
            index++;
            executor.run(this);
        }

    }

    public interface Executor {
        void run(ExecutorChain chain);
    }

    public interface ExecutorChain {
        ExecutorChain addExecutor(Executor executor);

        void run();
    }
}
