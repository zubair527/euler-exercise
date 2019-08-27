package com.euler.practise;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SpliteratorEx extends RecursiveTask<Long>
{
    public static void main(String[] args){
        System.out.println("Fork Join -> No. of default threads: "+ Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");

        long start = System.nanoTime();
        Long aSum = LongStream.rangeClosed(0, 999999998).parallel().reduce(0L, Long::sum);

        // the parallel executes faster then previous one.
        //Long aSum = LongStream.rangeClosed(0, 999999998).parallel().reduce(0L, Long::sum);

        //Long aSum = Stream.iterate(0L, a ->a+1).limit(999999999).parallel().reduce(0L, Long::sum);
        //Integer aSum = Stream.iterate(0, a ->a+1).peek(System.out::println).limit(9999).reduce(0, Integer::sum);
        long duration = (System.nanoTime() - start)/ 1_000_000;
        System.out.println("Sum " +aSum + " Duration: "+ duration);
    }

    @Override
    protected Long compute() {
        return null;
    }

    ForkJoinPool
}
