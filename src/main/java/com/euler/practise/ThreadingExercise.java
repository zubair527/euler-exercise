package com.euler.practise;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadingExercise implements Thread.UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println(t.getName());
    System.out.println(e);
  }

  public static void main(String[] args)  {

    Thread threadOne = new ThreadOne();
    RunnableOne runner = new RunnableOne();
    Thread threadTwo = new Thread(runner);

    // untill the new incremented (add 1) value is not provided it can't be interrupted.
    // thread safe.
    AtomicInteger aInt = new AtomicInteger(0);
    long id = aInt.incrementAndGet();
    long someValue = aInt.updateAndGet((x) -> Math.max(x, 4));

    // Java 8 way
    Runnable runnableTwo = () -> {
      try {
        for (int i = 0; i <= 100; ++i) {
          System.out.println("Value from Lambda-Style: " + i);
          if (i == 98) {
            throw new InterruptedException("Interrupt at 99");
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

   // Thread threadThree = new Thread(runnableTwo);
    threadTwo.start();
    //threadThree.start();
    //threadOne.start();

  }
/*
Java does preemptive scheduling..os allocates a time slice to thread and when there are high priority
threads in Runable state it Pre-empts a running thread and runs a higher priority thread.

While Mobile OS usually does co-oparative scheduling.
 */

  static class RunnableOne implements Runnable {

    @Override
    public void run() {
      try {
        for (int i = 0; i <= 100; ++i) {
          System.out.println(Thread.currentThread().getState() + " Value from RunnableOne-Class: " + i);
          if (Thread.currentThread().isInterrupted()) {
            System.out.println("Thread interputed at i: " + i);
            break;
          }
          if(i == 25)
            Thread.yield();
          if (i > 50) {
            //throw new InterruptedException("Interrupt at 99");
            // if sleep is call on an interrupted thread an exception is thrown: java.lang.InterruptedException: sleep interrupted
            Thread.currentThread().interrupt();
            Thread.sleep(500);
          }
        }
      } catch (InterruptedException e) {
        System.out.println("Exception: "+ e);

      }
    }
  }
}
//============================

  class ThreadOne extends Thread {

    public  void myFunc(){
      int a =0;
      Object t = new Object();
      synchronized(t){
        notifyAll();
        a = a +1;

      }
    }

    public void run() {
      for (int i = 0; i <= 100; ++i) {
        System.out.println("Value from ThreadOne-Class: " + i);
      }
    }
  }