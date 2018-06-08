package mx.com.jros;

import java.util.concurrent.TimeUnit;

public class QuintaForma {
	
	
	
	public static void main(String[] args) {
		System.out.println("Aqui enpieza !!!");
	Thread t =	new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for (int i=10; i > 0;i--)
				{
					System.out.println(" TICK TICK " + i);
					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
	t.start();
		System.out.println("Aqui termina !!!");
	}
}
