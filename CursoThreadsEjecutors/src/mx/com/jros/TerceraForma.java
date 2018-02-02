package mx.com.jros;

import java.util.concurrent.TimeUnit;

public class TerceraForma {

	public static void main(String[] args) {
		System.out.println("Main thread starts here ...");
		new TerceraTarea();
		new TerceraTarea();
		System.out.println("Main thread ends here ...");
	}
}

class TerceraTarea implements  Runnable{
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
	
	public TerceraTarea()
	{
		this.id=++count;
		new Thread(this).start();
	}
	
}