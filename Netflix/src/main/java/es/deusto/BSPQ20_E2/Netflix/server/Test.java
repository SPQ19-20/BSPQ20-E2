package es.deusto.BSPQ20_E2.Netflix.server;

import java.io.IOException;

import es.deusto.BSPQ20_E2.Netflix.client.Client;
/**
 * 
 * @author Annette
 *
 */
public class Test {
	
	public static void main(String[] args) {
		
		Thread thr1 = new Thread(new Runnable() {
			
			
			public void run() {
				String[] p = { "10072" };
				try {
					Main.main(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LOGGER.info(e.getMessage());
				}
			}
		});
		thr1.start();
		
		Thread thr2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Client client = new Client("0.0.0.0", "10072");
				client.searchMS("abc");
			}
		});
		thr2.start();
		
		try {
			thr1.join();
		} catch (InterruptedException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			thr2.join();
		} catch (InterruptedException e) {
			LOGGER.info(e.getMessage());
		}
		
		
	}
}
