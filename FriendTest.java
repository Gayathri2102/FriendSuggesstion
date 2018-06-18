import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class FriendTest {
  private static String testInput0 = "end";
  private static String [] testOutput0 = {};

  private static String testInput1 = "Alice joins\n" +
                                     "Carol joins\n" +
                                     "Bob joins\n" +
                                     "Bob friends Alice\n" +
                                     "Bob friends Carol\n" +
                                     "Alice leaves\n" + 
                                     "Bob leaves\n" + 
                                     "Carol leaves\n" + 
                                     "end";
  private static String [] testOutput1 = { 
                     "Alice and Carol should be friends" };

  private static String testInput2 = "Alice joins\n" +
                                     "Carol joins\n" +
                                     "Bob joins\n" +
                                     "Bob friends Alice\n" +
                                     "Bob friends Carol\n" +
                                     "Bob unfriends Carol\n" +
                                     "Bob friends Carol\n" +
                                     "Alice leaves\n" + 
                                     "Bob leaves\n" + 
                                     "Carol leaves\n" + 
                                     "end";
  private static String [] testOutput2 = { 
                     "Alice and Carol should be friends",
                     "Alice and Carol should be friends" };


  private static String testInput3 = "Alice joins\n" +
                                     "Carol joins\n" +
                                     "Bob joins\n" +
                                     "Bob friends Alice\n" +
                                     "Bob friends Carol\n" +
                                     "Dave joins\n" +
                                     "Dave friends Bob\n" +
                                     "Alice leaves\n" + 
                                     "Bob leaves\n" + 
                                     "Carol leaves\n" + 
                                     "Dave leaves\n" + 
                                     "end";
  private static String [] testOutput3 = { 
                     "Alice and Carol should be friends",
                     "Alice and Dave should be friends",
                     "Carol and Dave should be friends" };


  private static String testInput4 = "Alice joins\n" +
                                     "Carol joins\n" +
                                     "Bob joins\n" +
                                     "Bob friends Alice\n" +
                                     "Bob friends Carol\n" +
                                     "Dave joins\n" +
                                     "Dave friends Bob\n" +
                                     "Carol leaves\n" +
                                     "Carol joins\n" +
                                     "Carol friends Alice\n" +
                                     "Alice leaves\n" + 
                                     "Bob leaves\n" + 
                                     "Carol leaves\n" + 
                                     "Dave leaves\n" + 
                                     "end";
  private static String [] testOutput4 = { 
                     "Alice and Carol should be friends",
                     "Alice and Dave should be friends",
                     "Carol and Dave should be friends",
                     "Bob and Carol should be friends" };

  private static String testInput5 = "Alice joins\n" +
                                     "Carol joins\n" +
                                     "Bob joins\n" +
                                     "Bob friends Alice\n" +
                                     "Bob friends Carol\n" +
                                     "Dave joins\n" +
                                     "Dave friends Bob\n" +
                                     "Eve joins\n" +
                                     "Eve friends Bob\n" +
                                     "Dave leaves\n" +
                                     "Dave joins\n" +
                                     "Dave friends Bob\n" +
                                     "Dave unfriends Bob\n" +
                                     "Dave friends Bob\n" +
                                     "Fred joins\n" +
                                     "Alice friends Fred\n" +
                                     "Bob friends Fred\n" +
                                     "Carol friends Fred\n" +
                                     "Bob leaves\n" +
                                     "Fred leaves\n" +
                                     "Alice friends Carol\n" +
                                     "Alice leaves\n" + 
                                     "Carol leaves\n" + 
                                     "Dave leaves\n" + 
                                     "end";
  private static String [] testOutput5 = { 
                                     "Alice and Carol should be friends", 
                                     "Alice and Dave should be friends", 
                                     "Carol and Dave should be friends", 
                                     "Alice and Eve should be friends", 
                                     "Carol and Eve should be friends", 
                                     "Dave and Eve should be friends", 
                                     "Alice and Dave should be friends", 
                                     "Carol and Dave should be friends", 
                                     "Dave and Eve should be friends", 
                                     "Alice and Dave should be friends", 
                                     "Carol and Dave should be friends", 
                                     "Dave and Eve should be friends", 
                                     "Bob and Fred should be friends", 
                                     "Carol and Fred should be friends", 
                                     "Dave and Fred should be friends", 
                                     "Eve and Fred should be friends", 
                                     "Alice and Carol should be friends" };

  private static Scanner mkTest( String input ) {
    return new Scanner( input );
  }
     
  private static ArrayList<String> mkOutput( String [] output ) {
    ArrayList<String> al = new ArrayList<String>();

    for( String s : output ) {
      al.add( s );
    }
    return al;
  }
     
  private static boolean doTest( String input, String [] output ) {
    Tester t = new Friend();
    ArrayList<String> al = t.compute( mkTest( input ) );
    System.out.println( "Input: " );
    System.out.println( input );
    System.out.println( "Generated output" );
    for( String s : al ) {
      System.out.println( s );
    }
    System.out.println( "Expected output" );
    for( String s : output ) {
    	  System.out.println( s );
    }
    System.out.println( "---------------------------------------------------" );
    return al != null && al.equals( mkOutput( output ) );
  }

  @Test
  void testEmpty() {
    assertTrue( doTest( testInput0, testOutput0 ), "Empty test" );
  }

  @Test
  void test1() {
    assertTrue( doTest( testInput1, testOutput1 ), "Basic recommendation" );
  }

  @Test
  void test2() {
    assertTrue( doTest( testInput2, testOutput2 ), "Repeat recommendation" );
  }

  @Test
  void test3() {
    assertTrue( doTest( testInput3, testOutput3 ), "Multiple recommendation" );
  }

  @Test
  void test4() {
    assertTrue( doTest( testInput4, testOutput4 ), "Lots of users leave" );
  }

  @Test
  void test5() {
    assertTrue( doTest( testInput5, testOutput5 ), "Lots of recommendations" );
  }

}
