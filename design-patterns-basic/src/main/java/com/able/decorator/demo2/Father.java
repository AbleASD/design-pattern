package com.able.decorator.demo2;

public class Father {
    public static void main(String[] args) {
        SchoolReport sr = new FourthGradeSchoolReport();
        sr = new SortScoreDecorator(sr);
        sr = new HighScoreDecorator(sr);

        sr.report();
        sr.sign("Able");
    }
}
