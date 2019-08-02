package com.euler.practise;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ThreadLocalsEx {
  public static void main(String[] args) throws InterruptedException{

    // java 8 way with lambda expression
    final ThreadLocal<SimpleDateFormat> usDateFormatter = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd"));

    // anonymous class way
    final ThreadLocal<SimpleDateFormat> usDateFormatter2 = new ThreadLocal<SimpleDateFormat>(){
      protected SimpleDateFormat initalValue(){
          return new SimpleDateFormat("dd-MM-YYYY");
        }

    };

    SimpleDateFormat aFormatter = new SimpleDateFormat("yyyy-MM-dd");

    Runnable r = ()->{  // we can assign this because it match consumer functional interface
      String dateStamp = usDateFormatter.get().format(new Date());
      double randomNum = Math.random() * 100D;
      System.out.println(randomNum +" : " + dateStamp);
      try {
        System.out.println("Going to sleep");
        Thread.sleep(90);
        System.out.println("WokeUP");
      }catch (InterruptedException e){
        System.out.println(e);
      }
    };

   Thread t1 = new Thread(r);
   Thread t2 = new Thread(r);

   t1.start();
   t2.start();

/*
    for (long i=0; i< 99999; i++){
     System.out.println("Value of i: "+ i);
     if (i == 999)
       t1.join(); // the main thread which is executing loop leaves execution at this point.
    }
*/

   // The thread which calls the join() method will go to wait, in this case its the main()
    // which is creating t1 and t2.
    t1.join();
    System.out.println("-==============");
   t2.join();

    AtomicInteger aInt = new AtomicInteger(0);
    long id = aInt.incrementAndGet();
    long someValue = aInt.updateAndGet((x) -> Math.max(x, 4));

  }
}
