package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    private void printTest(int i, MyQueue<String> first, Queue<String> second) {
        System.out.println("Test " + i);
        //////////// iterator check
        Iterator<String> itr1 = first.iterator();
        Iterator<String> itr2 = second.iterator();
        String[] test1 = new String[first.size()];
        String[] test2 = new String[second.size()];
        int j = 0;
        System.out.print("Iterator-based print expected: ");
        while(itr2.hasNext()){
            test2[j++] = itr2.next();
            System.out.print(test2[j-1] + "; ");
        }
        System.out.println();
        j = 0;
        System.out.print("Iterator-based print actual: ");
        while(itr1.hasNext()){
            test1[j++] = itr1.next();
            System.out.print(test1[j-1] + "; ");
        }
        System.out.println();
        Assert.assertArrayEquals(test1, test2);
        //////////// peek() check
        String el1 = first.peek(), el2 = second.peek();
        System.out.println("Peek expected: " + el2 + ", actual: " + el1);
        Assert.assertEquals(el1, el2);
        //////////// element() check
        try {
            el1 = first.element();
            el2 = second.element();
            Assert.assertEquals(el1, el2);
            System.out.println("Element expected: " + el2 + ", actual: " + el1);
        } catch(Exception e1){
            System.out.println("Element expected: class java.util.NoSuchElementException" + ", actual: " + e1.getClass());
            Assert.assertThrows(e1.getClass(), second::element);
        }
        //////////// poll() check
        el1 = first.poll();
        el2 = second.poll();
        System.out.println("Poll expected: " + el2 + ", actual: " + el1);
        Assert.assertEquals(el1, el2);
        //////////// remove() check
        try {
            el1 = first.remove();
            el2 = second.remove();
            Assert.assertEquals(el1, el2);
            System.out.println("Remove expected: " + el2 + ", actual: " + el1);
        } catch(Exception e1){
            System.out.println("Remove expected: class java.util.NoSuchElementException" + ", actual: " + e1.getClass());
            Assert.assertThrows(e1.getClass(), second::remove);
        }
        //////////// size() check
        System.out.println("Size expected: " + second.size() + ", actual: " + first.size());
        Assert.assertEquals(first.size(), second.size());
        System.out.println("\n");
    }

    public void test0(){
        MyQueue<String> x = new MyQueue<>();
        x.offer("one");
        x.offer("two");
        x.offer("three");
        Queue<String> y = new LinkedList<>();
        y.offer("one");
        y.offer("two");
        y.offer("three");
        printTest(0, x, y);
    }

    public void test1(){
        MyQueue<String> x = new MyQueue<>();
        Queue<String> y = new LinkedList<>();
        printTest(1, x, y);
    }

    public void test2(){
        MyQueue<String> x = new MyQueue<>();
        Queue<String> y = new LinkedList<>();
        for(int i = 0; i < 16; i++){
            x.offer(String.valueOf(i));
            y.offer(String.valueOf(i));
        }
        printTest(2, x, y);
    }
}
