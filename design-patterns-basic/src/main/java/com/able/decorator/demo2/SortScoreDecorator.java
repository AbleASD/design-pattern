package com.able.decorator.demo2;

public class SortScoreDecorator extends Decorator {

    public SortScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportSort() {
        System.out.println("排名为2");
    }

    @Override
    public void report() {
        this.reportSort();
        super.report();
    }

}
