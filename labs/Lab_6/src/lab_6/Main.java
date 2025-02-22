/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab_6;

/**
 *
 * @author ri713
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    // This file creates multiple threads to simulate depositing and withdrawing
    // money from a bank account
    public static void main(String[] args) {
        BankAccount account = new BankAccount(); // create a new bank account

        // number of threads that will deposit and withdraw money
        int numDepositThreads = 10;
        int numWithdrawThreads = 10;

        // arrays to store the threads for depositing and withdrawing money
        Thread[] depositThreads = new Thread[numDepositThreads];
        Thread[] withdrawThreads = new Thread[numWithdrawThreads];

        // creating deposit threads
        for (int i = 0; i < numDepositThreads; i++) {
            // each thread will deposit $5000, 10 times
            depositThreads[i] = new Thread(new DepositTask(account, 5000, 10), "DepositThread-" + (i + 1));
            depositThreads[i].start(); // start the thread
        }

        // creating withdraw threads
        for (int i = 0; i < numWithdrawThreads; i++) {
            // each thread will withdraw $3000, 10 times
            withdrawThreads[i] = new Thread(new WithdrawTask(account, 3000, 10), "WithdrawThread-" + (i + 1));
            withdrawThreads[i].start(); // start the thread
        }

        // wait for all deposit threads to finish
        for (Thread t : depositThreads) {
            try {
                t.join(); // wait for the thread to finish before continuing
            } catch (InterruptedException e) {
                e.printStackTrace(); // print the exception if happened
            }
        }

        // wait for all withdraw threads to finish
        for (Thread t : withdrawThreads) {
            try {
                t.join(); // wait for the thread to finish before continuing
            } catch (InterruptedException e) {
                e.printStackTrace(); // print the exception if happened
            }
        }

        System.out.println("Final Balance: $" + account.getBalance()); // print the final balance

    }

}
