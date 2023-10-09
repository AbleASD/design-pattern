package com.able.composite;

import java.util.ArrayList;

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