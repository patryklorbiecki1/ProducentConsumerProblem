package org.example;

import java.util.Vector;

public class Producer implements Runnable{
    private final int SIZE;
    private final Vector queue;

    public Producer(int size, Vector queue) {
        SIZE = size;
        this.queue = queue;
    }
    private void produce(int i) throws InterruptedException{
        while (queue.size()==SIZE) {
            synchronized (queue) {
                System.out.println("Queue is full, wait");
                queue.wait();
            }
        }
        synchronized (queue){
            queue.add(i);
            queue.notifyAll();
        }
    }
    @Override
    public void run() {
        while (true){
            try{
                for(int i=0;i<SIZE;i++) {
                    System.out.println("Produced: " + i);
                    produce(i);
                }
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

    }
}
