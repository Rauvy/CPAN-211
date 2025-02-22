/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_6;

/**
 *
 * @author ri713
 */
public class BankAccount {
    private double balance;
    private final double INSURANCE_LIMIT = 100000; // max balance allowed in the account

    public BankAccount() { // creating a constructor to initialize the balance to 0
        this.balance = 0;
    }

    public synchronized void deposit(double amount) {
        if (balance + amount > INSURANCE_LIMIT) { // check if the balance will exceed the insurance limit
            try {
                System.out.println(Thread.currentThread().getName() +
                        " Deposit stopped, waiting for withdrawal. Current Balance: $" + balance);
                wait(); // wait for the balance to decrease
            } catch (InterruptedException e) {
                e.printStackTrace(); // print the exception if happened
            }
        }
        balance += amount; // deposit the money
        System.out.println(Thread.currentThread().getName() +
                " Deposited: $" + amount + " New Balance: $" + balance); // print the deposit information
        notifyAll(); // notify all threads that are waiting
    }

    public synchronized void withdraw(double amount) {
        while (balance < amount) {
            try {
                System.out.println(Thread.currentThread().getName() +
                        " Withdraw stopped, wait for deposit. Current Balance: $" + balance);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() +
                " Withdraw $" + amount + " Balance: $" + balance);
        notifyAll();
    }

    public synchronized double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getINSURANCE_LIMIT() {
        return INSURANCE_LIMIT;
    }
}
