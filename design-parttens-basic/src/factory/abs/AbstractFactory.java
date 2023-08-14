package factory.abs;

import factory.Reciver;
import factory.Sender;

public abstract class AbstractFactory {
    public abstract Sender createSender();
    public abstract Reciver createReciver();
}
