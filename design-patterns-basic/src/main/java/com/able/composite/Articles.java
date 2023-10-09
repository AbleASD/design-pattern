package com.able.composite;

interface Articles {
    float calculation();
    void show();

    void add(Articles articles);
    void remove(Articles articles);
    Articles getArticles(int index);
}