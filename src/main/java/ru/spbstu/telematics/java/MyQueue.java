package ru.spbstu.telematics.java;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Queue;

public class MyQueue<K>{
    private final int INC_CAPACITY = 16;
    private int size; // максимальный размер очереди (увеличивается при необходимости)
    private Object[] arr; // массив, в котором будут храниться элементы
    private int numb; // количество добавленных элементов

    public MyQueue(){
        this.size = 16;
        this.arr = new Object[size];
        this.numb = 0;
    }

    public K element(){
        K elem = peek();
        if (elem != null)
            return elem;
        else
            throw new NoSuchElementException();
    }

    public void resize() {
        size += INC_CAPACITY;
        Object[] oldQueue = arr.clone();
        arr = new Object[size];
        System.arraycopy(oldQueue, 0, arr, 0, numb);
    }

    public void offer(K obj){
        if(numb >= size)
            resize();
        arr[numb++] = obj;
    }

    public K peek(){
        if(numb == 0)
            return null;
        return (K) arr[0];
    }

    public int size(){
        return numb;
    }

    boolean add(K obj){
        try{
            arr[numb++] = obj;
            return true;
        } catch(Exception e){
            throw new IllegalStateException("Queue full");
        }
    }

    public K poll(){
        if(numb == 0)
            return null;
        K elem = (K) arr[0];
        if (numb - 1 >= 0) System.arraycopy(arr, 1, arr, 0, numb - 1);
        arr[numb - 1] = null;
        numb -= 1;
        return elem;
    }

    public K remove(){
        K elem = poll();
        if (elem != null)
            return elem;
        else
            throw new NoSuchElementException();
    }

    private abstract class MyIterator<K> implements Iterator<K> {
        K next;
        int index;

        public MyIterator() {
            index = 0;
            next = (K) arr[index];
        }

        @Override public final boolean hasNext() {
            return next != null;
        }

        @Override public K next() {
            K current = next;
            if (current == null)
                throw new NoSuchElementException();
            if(index < size-1) next = (K) arr[++index];
            else next = null;
            return (current);
        }

    }
    public MyIterator<K> iterator(){
        return new MyIterator<K>() {};
    }


    public static void main(String[] args){    }

}


