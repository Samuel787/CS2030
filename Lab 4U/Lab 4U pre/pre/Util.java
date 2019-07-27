import java.util.*;
import java.util.function.*;

/* Utilities: contains static functions for utilities */
public class Util {
  /* Conversion to String */
  /**
   * Convert double to String
   * @param val The value to be converted
   * @return String representation of the value in 3 dp
   */
  public static String str(double val) {
    return String.format("%.3f", val);
  }

  /**
   * Check if the value is within range
   * @param val The value
   * @param max Maximum value
   * @param min Minimum value
   * @return True if val in [min,max] inclusive, False otherwise
   */
  public static boolean range(int val, int min, int max) {
    return val >= min && val <= max;
  }

  /**
   * Count the array for elements satisfying the predicate
   * @param <T> The type of elements in array
   * @param arr The array to be checked
   * @param check The predicate to test
   * @return The number of elements in array that satisfies the predicate
   */
  public static <T> int count(T[] arr, Predicate<T> check) {
    int counter = 0;
    for(T elem: arr) {
      if(check.test(elem)) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Count the list for elements satisfying the predicate
   * @param <T> The type of elements in list
   * @param list The list to be checked
   * @param check The predicate to test
   * @return The number of elements in list that satisfies the predicate
   * @return
   */
  public static <T> int count(Collection<T> list, Predicate<T> check) {
    int counter = 0;
    for(T elem: list) {
      if(check.test(elem)) {
        counter++;
      }
    }
    return counter;
  }
  
  /**
   * Find the minimum value in an array of integer
   * @param arr The array of integer
   * @return The minimum value
   */
  public static int min(int[] arr) {
    int res = arr[0];
    for(int val: arr) {
      if(val < res) {
        res = val;
      }
    }
    return res;
  }

  /**
   * Find the minimum value in an array of <T> based on some comparator
   * @param <T> The type of elements in array
   * @param arr The array of <T>
   * @param comp The comparator
   * @return The minimum value
   */
  public static <T> T min(T[] arr, BiPredicate<T,T> comp) {
    T res = arr[0];
    for(T elem: arr) {
      if(comp.test(elem, res)) {
        res = elem;
      }
    }
    return res;
  }

  /**
   * Check if all the elements in the array satisfies the predicate
   * @param <T> The type of elements in array
   * @param arr The array to be checked
   * @param check The predicate to test
   * @return True if all elements in array satisfies the predicate, False otherwise
   */
  public static <T> boolean forall(T[] arr, Predicate<T> check) {
    return Util.count(arr, check) == arr.length;
  }

  /**
   * Check if all the elements in the list satisfies the predicate
   * @param <T> The type of elements in list
   * @param list The list to be checked
   * @param check The predicate to test
   * @return True if all elements in list satisfies the predicate, False otherwise
   */
  public static <T> boolean forall(Collection<T> list, Predicate<T> check) {
    return Util.count(list, check) == list.size();
  }

  /**
   * Exponential random variable generator
   * @param rand Random number generator
   * @param rate Rate of arrival
   * @return Exponential random variable
   */
  public static double rng(Random rand, double rate) {
    return -Math.log(rand.nextDouble()) / rate;
  }
}