package com.able.factory.abs;

import com.able.factory.Reciver;
import com.able.factory.Sender;

public abstract class AbstractFactory {
    public abstract Sender createSender();
    public abstract Reciver createReciver();
}
