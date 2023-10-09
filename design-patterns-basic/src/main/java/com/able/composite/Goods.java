package com.able.composite;

// 树叶构建
class Goods implements Articles {
    private String name;
    private int quantity;
    private float unitPrice;

    public Goods(String name, int quantity, float unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public float calculation() {
        return quantity * unitPrice;
    }

    @Override
    public void show() {
        System.out.println(name + "(" + "quantity: " + quantity + ", unit price: " + unitPrice + ")");
    }

    @Override
    public void add(Articles articles) {
        throw new UnsupportedOperationException("Unimplemented method 'getArticles'");
    }

    @Override
    public void remove(Articles articles) {
        throw new UnsupportedOperationException("Unimplemented method 'getArticles'");
    }

    @Override
    public Articles getArticles(int index) {
        throw new UnsupportedOperationException("Unimplemented method 'getArticles'");
    }

}