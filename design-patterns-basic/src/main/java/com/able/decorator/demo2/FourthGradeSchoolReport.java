package com.able.decorator.demo2;

public class FourthGradeSchoolReport implements SchoolReport {

    @Override
    public void report() {
        System.out.println("尊敬的Able家长");
        System.out.println("成绩如下");
        System.out.println("语文 87");
    }

    @Override
    public void sign(String name) {
        System.out.println("家长签名为："+name);
    }
    
}
