package com.able.composite;

import java.util.ArrayList;

/**
 * 透明模式，抽象构建声明了子类所有方法，不区分树枝对象和树叶对象。
 * 树叶对象要实现一些空方法，同时也容易引发安全性问题
 */
public class TransparentModeShopping {
    public static void main(String[] args) {
        float s = 0;
        Bags BigBag, mediumBag, smallRedBag, smallWhiteBag;
        Goods sp;
        BigBag = new Bags("大袋子");
        mediumBag = new Bags("中袋子");
        smallRedBag = new Bags("红色小袋子");
        smallWhiteBag = new Bags("白色小袋子");
        sp = new Goods("婺源特产", 2, 7.9f);
        smallRedBag.add(sp);
        sp = new Goods("婺源地图", 1, 9.9f);
        smallRedBag.add(sp);
        sp = new Goods("韶关香菇", 2, 68);
        smallWhiteBag.add(sp);
        sp = new Goods("韶关红茶", 3, 180);
        smallWhiteBag.add(sp);
        sp = new Goods("景德镇瓷器", 1, 380);
        mediumBag.add(sp);
        mediumBag.add(smallRedBag);
        sp = new Goods("李宁牌运动鞋", 1, 198);
        BigBag.add(sp);
        BigBag.add(smallWhiteBag);
        BigBag.add(mediumBag);
        System.out.println("您选购的商品有：");
        BigBag.show();
        s = BigBag.calculation();
        System.out.println("要支付的总价是：" + s + "元");
    }
}

interface Articles {
    float calculation();
    void show();

    void add(Articles articles);
    void remove(Articles articles);
    Articles getArticles(int index);
}

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

// 数值构建
class Bags implements Articles {
    private String name;
    public String getName() {
        return name;
    }

    private ArrayList<Articles> bags = new ArrayList<>();

    public Bags(String name) {
        this.name = name;
    }

    @Override
    public void add(Articles articles) {
        this.bags.add(articles);
    }

    @Override
    public void remove(Articles articles) {
        this.bags.remove(articles);
    }

    @Override
    public Articles getArticles(int index) {
        return bags.get(index);
    }

    @Override
    public float calculation() {
        float ret = 0;
        for (Articles bag : bags) {
            ret += bag.calculation();
        }
        return ret;
    }

    @Override
    public void show() {
        for (Articles bag : bags) {
            bag.show();
        }
    }

}
