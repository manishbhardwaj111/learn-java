package org.learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomSharedList<T>{
   private final List<T> list;
   private final int capacity;
   private final Lock lock;
   private final Condition nonEmpty;
   private final Condition nonFull;

   public CustomSharedList(int capacity){
      list = new ArrayList<>(capacity);
      this.capacity = capacity;
	  lock = new ReentrantLock();
	   nonEmpty = lock.newCondition();
	   nonFull = lock.newCondition();
   }


   public void add(T item) throws InterruptedException {
      lock.lock();
	  try{
	     while(list.size() == capacity){
			 nonFull.await();
		 }
		 list.add(item);
		  nonEmpty.signal();
	  } finally{
	     lock.unlock();
	  }
   }  

   public T remove() throws InterruptedException {
      lock.lock();
	  try{
	     while(list.isEmpty()){
			 nonEmpty.await();
		 }
		 T item = list.removeFirst();
		  nonFull.signal();
		 return item;
	  } finally{
	     lock.unlock();
	  }
   }    
   
   public static void main(String[] args){
	   CustomSharedList<String> sharedList = new CustomSharedList<>(5);
       new Thread(()->{
		   int i = 0;
	      while(true){
		    System.out.println("Produced "+i);
              try {
                  sharedList.add("ABC " + i++);
				  System.out.println(sharedList);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
	   }).start();
	   
	   new Thread(()->{
	      while(true){
		      try {
				  System.out.println("Consumed "+ sharedList.remove());
				  System.out.println(sharedList);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
	   }).start();
   }

	@Override
	public String toString() {
		return "CustomSharedList{" +
				"list=" + list +
				'}';
	}
}