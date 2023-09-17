package com.able.prototype;

import java.util.ArrayList;

public class Prototype implements Cloneable {
    public String name;

    CloneTarget target = null;

    public ArrayList<CloneTarget> list;
}
