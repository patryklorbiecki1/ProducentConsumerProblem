package org.example;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        int size = 5;
        Vector vector = new Vector();
        Thread producer = new Thread(new Producer(size,vector),"Producer");
        Thread consumer = new Thread(new Consumer(size,vector),"Consumer");

        producer.start();
        consumer.start();

        System.out.println("Hello world!");
    }
}