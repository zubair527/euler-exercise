package com.euler.practise;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jetbrains.annotations.NotNull;

public class ServiceExecutorsEx {

  static <T, Z> Z min(Z a){
    return a;
  }

  public static void main(String[] args) {

    ExecutorService executor = Executors.newFixedThreadPool(2);
    ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(Executors.newFixedThreadPool(2));

    Callable c1 = new CountsNum();
/*
    Future f1 = executor.submit(c1);
    try {
      System.out.println(f1.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
*/
    try {
       /*
        Using future task which turns a Callable into both a Future and Runnable
       */
      FutureTask futureTask = new FutureTask(c1);
      Thread thread = new Thread(futureTask);
      thread.start();
      Thread.sleep(200); // this will cause the above thread to star before cancel is called.
      futureTask.cancel(false); //will generate the exception bcz task will get
      //interupted... however if false is passed...it will not cancel the task.
      System.out.println("Thread Woke-up ===========================================");

      if (futureTask.isCancelled() == false)
        System.out.println(futureTask.get());

      if (futureTask.isDone()){
        System.out.println("isDone");
        System.out.println(futureTask.get());
      }

    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }


  static class CountsNum implements Callable<Double> {

    @Override
    public Double call() throws Exception {
      Double result = 0.0;
      for (double i = 0; i < 999999999; i++) {
        result = result + i;
       // System.out.println("value of i: "+i);
      }
      return result;
    }
  }

  //================================ FutureTask ========================

}
