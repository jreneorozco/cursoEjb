package mx.com.jros;

import java.util.concurrent.TimeUnit;

public class PrimeraForma {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ...");
		new PrimeraTarea();
		Thread t =new PrimeraTarea();
		System.out.println("Main thread ends here ...");
	}
}

class PrimeraTarea extends Thread{
	private static int count =0;
	private int id;
	
	@Override
	public void run()
	{
		for (int i=10; i > 0;i--)
		{
			System.out.println("<"+id+"> TICK TICK " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public PrimeraTarea()
	{
		this.id=++count;
		this.start();
	}
	
}