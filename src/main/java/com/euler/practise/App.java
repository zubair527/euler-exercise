package com.euler.practise;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList();
    for (int i = 0; i < 1000; i++) {
      numbers.add(i, i);
    }

    ArrayList<Integer> myList = filteredItems(numbers, (int a) -> a%3==0 || a%5 ==0);
    System.out.println(myList.stream().mapToInt(Integer::intValue).sum());

    printSum(numbers, new CheckMultiple());
  }

  public static void printSum(ArrayList<Integer> elementsArray, Divisibility testChecker){
    Integer sum = 0;
    for (Integer num: elementsArray){
      sum = sum + testChecker.test(num);
    }
    System.out.println("Sum is: " +sum);
  }

  interface Divisibility{
    int test(int num);
  }

  static class CheckMultiple implements Divisibility{
    public int test(int num){
      int result = 0;
      if(num %3 ==0 || num % 5 ==0){
        result = num;
      }
      return result;
    }
  }
/*
1- Create an interface
2- Create a static method which takes the interface as parameter and calls the method on object from collection.
3- Call the method in main function and write a lambda expression.
 */
  //============================================== Another Approach ================================================
static ArrayList<Integer> filteredItems(ArrayList<Integer> numbers, FactorPredicate numberPredicate){
    ArrayList<Integer> filtered = new ArrayList<>();
    for (Integer a: numbers){
      if(numberPredicate.test(a))
        filtered.add(a);
    }
    return filtered;
  }


  interface FactorPredicate{
    boolean test(int a);
  }
}
