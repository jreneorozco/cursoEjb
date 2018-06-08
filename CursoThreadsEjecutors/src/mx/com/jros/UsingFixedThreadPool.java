package mx.com.jros;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mx.com.jros.tuts.commos.LoopTaskA;

public class UsingFixedThreadPool {
	
	public static void main(String[] args) {
		
		System.out.println("################### START ##########################");
		ExecutorService executor=  Executors.newCachedThreadPool();

		
		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
		executor.execute(new LoopTaskA());
//		executor.execute(new LoopTaskA());
//		executor.execute(new LoopTaskA());
//		executor.execute(new LoopTaskA());
		
		executor.shutdown();
		System.out.println("################### DONE ##########################");
	}
	
}
