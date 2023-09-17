package com.able.prototype;

public class Test {
    public static void main(String[] args) {
        CloneTarget target = new CloneTarget();
        target.name = "Able";
        target.target = new CloneTarget();
        
        CloneTarget clone;
        try {
            clone = (CloneTarget) target.clone();
            clone.name = "Able1";
            System.out.println("target name: " + target.name + "\n" + "clone name: " + clone.name);
            
            System.out.println(target.name == clone.name);
            System.out.println(target.target);
            System.out.println(clone.target);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
