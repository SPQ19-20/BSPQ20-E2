package es.deusto.BSPQ20_E2.Netflix.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Annette
 *
 */

public class Psystem extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket tcpSocket;

	

	public Psystem(Socket socket) {
		try {
			this.tcpSocket = socket;
			this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("Oops IO error:" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void run() {
		List<Object> args = null;
		try {
			args = (List<Object>) this.in.readObject();
			System.out.println("Psystem - Received order -> '" + args.get(0) + "'");
			System.out.println("Psystem - " + args.get(0) + " " + args.get(1) + ", msg received.");
			this.out.writeObject("to be done method selected");
			
		} catch (EOFException e) {
			System.err.println("Oops EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Oops IO error:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Oops casting error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("OOps IO error:" + e.getMessage());
			}
		}
	}

	public void meth1() {
//		TODO 
	}

	public void meth2() {
//		TODO
	}

	public void meth3() {
//		TODO
	}
}