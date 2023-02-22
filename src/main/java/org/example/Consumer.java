package org.example;

import java.util.Vector;

public class Consumer implements Runnable{
    private final int SIZE;
    private final Vector queue;

    public Consumer(int size, Vector queue) {
        SIZE = size;
        this.queue = queue;
    }
    private int consume() throws InterruptedException{
        while (queue.isEmpty()) {
            synchronized (queue) {
                System.out.println("Queue is empty, wait");
                queue.wait();
            }
        }
            synchronized (queue){
                queue.notifyAll();
                return (Integer) queue.remove(0);
            }
    }

    @Override
    public void run() {
        while (true){
            try{
                System.out.println("Consumed " + consume());
                Thread.sleep(50);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
