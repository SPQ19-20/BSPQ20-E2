package dip2;

	import java.io.IOException;
	import java.net.ServerSocket;

	public class Main {

		public static void main(String args[]) {
			if (args.length < 1) {
				System.err.println("Port unavailable");
				System.exit(1);
			}
			int port = Integer.parseInt(args[0]);
			try (ServerSocket ss = new ServerSocket(port);) {
				System.out.println("Server waiting...");
				while (true) {
					new Psystem(ss.accept());
				}
			} catch (IOException e) {
				System.err.println("Main - IO error:" + e.getMessage());
			}
		}
	}

