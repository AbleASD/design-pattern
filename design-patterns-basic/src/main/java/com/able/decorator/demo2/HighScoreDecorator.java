package com.able.decorator.demo2;

public class HighScoreDecorator extends Decorator {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHighScore() {
        System.out.println("最高分为: 99");
    }

    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }

    
}
