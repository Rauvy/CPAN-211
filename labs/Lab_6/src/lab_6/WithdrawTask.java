/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_6;

/**
 *
 * @author ri713
 */
public class WithdrawTask implements Runnable {

    // the values never change once the task starts, so I made them final
    private final BankAccount account;
    private final double amount;
    private final int iterations;

    public WithdrawTask(BankAccount account, double amount, int iterations) {
        this.account = account;
        this.amount = amount;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            account.withdraw(amount); // withdraw the money from the account
            try {

                Thread.sleep(500); // Pause execution for 500ms (0.5 seconds) to simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace(); // print the exception if happened
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
