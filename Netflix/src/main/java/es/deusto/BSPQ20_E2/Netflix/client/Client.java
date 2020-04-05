package es.deusto.BSPQ20_E2.Netflix.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.deusto.BSPQ20_E2.Netflix.client.gui.Login;

	public class Client  {

		private String ip;
		private String port;

		public Client (String ip, String port) {
			super();
			this.ip = ip;
			this.port = port;
		}


		public void searchMS(String cStr) {
			List<Object> args = new ArrayList<Object>(Arrays.asList("Order", cStr));
			String str = "";
			try (Socket socket = new Socket(this.ip, Integer.parseInt(this.port))) {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(args);
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				str = (String) in.readObject();
				
			} catch (Exception e) {
				System.err.println("Oops: " + e.getMessage());
			}
			System.out.println("Client says hi -- " + str);
		}
		
		
	}
