package mx.com.jros;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mx.com.jros.tuts.commos.LoopTaskA;

public class UsingCachedThreadPool {
	
	public static void main(String[] args) {
		ExecutorService executor=  Executors.newFixedThreadPool(6);

		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
		
		executor.shutdown();
	}
	
}
