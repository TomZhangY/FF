package rpc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TTTT {

	public static void main(String[] args) {
		
		ExecutorService executor1 =  Executors.newCachedThreadPool();
		ExecutorService executor2 =  Executors.newFixedThreadPool(10);
		ExecutorService executor3 =  Executors.newScheduledThreadPool(10);
		ExecutorService executor4 =  Executors.newSingleThreadExecutor();
		
System.out.println(Runtime.getRuntime().availableProcessors());
ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(20);

ScheduledExecutorService scheduledExecutorService =
Executors.newScheduledThreadPool(5);

ScheduledFuture scheduledFuture =
	scheduledExecutorService.schedule(new Callable() {
		public Object call() throws Exception {
			System.out.println("Executed!");
			return "Called!";
		}
	},
			5,
			TimeUnit.SECONDS);

try {
	System.out.println("result = " + scheduledFuture.get());
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ExecutionException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

//ExecutorService ee = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler)
	}

}
