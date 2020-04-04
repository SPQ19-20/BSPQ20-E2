package dip2;

public class Test {
	
	public static void main(String[] args) {
		
		Thread thr1 = new Thread(new Runnable() {
			
			
			public void run() {
				String[] p = { "10072" };
				Main.main(p);
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
			e.printStackTrace();
		}
		try {
			thr2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
