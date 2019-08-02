package com.euler.practise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class StreamsExercise {
      public static void main(String[] args){
        String content = "";
        try {
          content = new String(Files.readAllBytes(Paths.get("C:\\Users\\zubairm4\\Documents\\dev-ops-notes.txt")), StandardCharsets.UTF_16);
        }catch ( IOException e){
          System.out.println("File not found");
        }
        Objects.requireNonNull(content, "File content must not  be null");
        List<String> words = Arrays.asList(content.split("\\PL+"));

        long count =0;
        for (String aWord: words) {
          if(aWord.length() > 2)
            count++;
        }


        long streamCount = words.stream().filter((w)-> w.length()>2).count();
        // OR it can be written as:
       // long aCount = Stream.of(content.split("\\PL+")).count();
        System.out.println("Word count by stream: "+ streamCount);
        System.out.println("Word Count: "+ count);

        UnaryOperator<Integer> aFunc = (a)-> a+2 ;
        //above can also be written as
        //Function<Integer, Integer> aFunc = (a) -> a+2;
        //System.out.println(aFunc.apply(3));

        Stream<Integer> aStream = Stream.iterate(0, a->a+1).limit(2); // first it will print 0
        // then it will print 1. Mean that a+1 is evaluated after seeding the stream.
        Stream<Integer> bStream = Stream.iterate(0, a->aFunc.apply(a)  ).limit(2);

        aStream.forEach(a->System.out.println(a));
        bStream.forEach(a->System.out.println(a));

      }
}
