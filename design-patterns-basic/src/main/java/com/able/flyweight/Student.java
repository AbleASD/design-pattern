package com.able.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private static List<Book> books = new ArrayList<>();
    private static Library library;

    private static void studentBorrow(String bookName) {
        books.add(library.libToBorrow(bookName));
        
    }

    public static void main(String[] args) {
        library = Library.getInstance();
        studentBorrow("java编程思想");
        studentBorrow("java核心卷一");
        studentBorrow("java从入门到精通");
        System.out.println("后两本没学会，又借了一次 ");
        studentBorrow("java核心卷一");
        studentBorrow("java从入门到精通");
        //把每一本书借出去
        for (Book book : books) {
            book.borrow();
        }
        //输出一些学生一共借多少本书
        System.out.println("学生一共借了 " + books.size() + " 本书! ");
        //输出一下图书馆一共借出多少本书
        System.out.println("图书馆实际借出" + library.getAllBookSize() + " 本书");
    }
}
