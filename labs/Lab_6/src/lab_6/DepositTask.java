/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_6;

/**
 *
 * @author ri713
 */

// simulates depositing money into a bank account
public class DepositTask implements Runnable {
    // the values never change once the task starts, so I made them final
    private final BankAccount account; // bank account where the money will be deposited
    private final double amount; // amount of money to deposit
    private final int iterations; // number of times to deposit the money

    public DepositTask(BankAccount account, double amount, int iterations) {
        this.account = account;
        this.amount = amount;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            account.deposit(amount); // deposit the money into the account
            try {

                Thread.sleep(500); // Pause execution for 500ms (0.5 seconds) to simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BankAccount getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public int getIterations() {
        return iterations;
    }
}
