package com.able.chain.responsibility.demo4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Below is an example of this pattern in Java.
 * In this example we have different roles, each having a fixed purchasing limit and a successor.
 * Every time a user in a role receives a purchase request that exceeds his or her limit,
 * the request is passed to his or her successor.
 * Created by Landy on 2019/1/6.
 * @see <a href="https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern">Chain-of-responsibility_pattern</a>
 */
public class CheckAuthority {
    public static void main(String[] args) {
        ManagerPPower managerPPower = new ManagerPPower();
        DirectorPPower directorPPower = new DirectorPPower();
        VicePresidentPPower vicePresidentPPower = new VicePresidentPPower();
        PresidentPPower presidentPPower = new PresidentPPower();

        managerPPower.setSuccessor(directorPPower);
        directorPPower.setSuccessor(vicePresidentPPower);
        vicePresidentPPower.setSuccessor(presidentPPower);

        try {
            while(true) {
                System.out.println("Enter the amount to check who should approve your expenditure.");
                System.out.print(">");
                double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
                managerPPower.processRequest(new PurchaseRequest(d, "General"));
            }
        }catch (Exception e) {
            System.exit(1);
        }
    }
}



/**
 * The PurchasePower abstract class with a concrete processRequest method
 */
abstract class PurchasePower {
    protected static final double Base = 500;

    protected PurchasePower successor;

    abstract protected double getAllowable();

    abstract protected String getRole();

    public void setSuccessor(PurchasePower successor) {
        this.successor = successor;
    }

    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < this.getAllowable()) {
            System.out.println(this.getRole() + " will approve $" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}

// Four implementations of the abstract class above: Manager, Director, Vice President, President

class ManagerPPower extends PurchasePower {

    @Override
    protected double getAllowable() {
        return Base * 10;
    }

    @Override
    protected String getRole() {
        return "Manager";
    }

}

class DirectorPPower extends PurchasePower {
    @Override
    protected double getAllowable() {
        return Base * 20;
    }

    @Override
    protected String getRole() {
        return "Director";
    }


}

class VicePresidentPPower extends PurchasePower {
    @Override
    protected double getAllowable() {
        return Base * 40;
    }

    @Override
    protected String getRole() {
        return "Vice President";
    }


}

class PresidentPPower extends PurchasePower {

    @Override
    protected double getAllowable() {
        return Base * 60;
    }

    @Override
    protected String getRole() {
        return "President";
    }

    
}

// The following code defines the PurchaseRequest class that keeps the request data in this example.

class PurchaseRequest {
    private double amount;
    private String purpose;

    public PurchaseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}
