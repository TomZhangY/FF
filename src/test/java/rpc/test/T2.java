package rpc.test;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class T2 {
public static void main(String[] args) {
	ExecutorService  ser  = null;
	ser = Executors.newSingleThreadExecutor();
	Runnable task =  new Runnable() {
		@Override
		public void run() {
			do {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}while(true);
		}
	};
	long start = new Date().getTime();
	try {
		Future<?> future = ser.submit(task);
//		Future<?> future1 = ser.submit(task);
		future.get(2000, TimeUnit.MILLISECONDS);
//		future1.get(2000, TimeUnit.MILLISECONDS);
	} catch(InterruptedException e) {
		System.out.println("1");
		e.printStackTrace();
	} catch(ExecutionException e){
		System.out.println("2");
		e.printStackTrace();
	} catch (TimeoutException e) {
		System.out.println("3");
		System.out.println(" timeout ");
		e.printStackTrace();
	}
	long end = new Date().getTime();
	System.out.println("Time =" + (end - start));
	if(ser != null) {
		ser.shutdown();
	}
	System.out.println(ser.isTerminated());
	System.out.println(ser.isShutdown());
	
System.out.println(new Double("150.00").intValue()*100);
}
}
