package es.deusto.BSPQ20_E2.Netflix.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import es.deusto.BSPQ20_E2.Netflix.client.Client;

/**
 * @author Annette
 *
 */

public class Psystem extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket tcpSocket;

	private final static Logger LOGGER = Logger.getLogger(Psystem.class.getName());


	public Psystem(Socket socket) {
		try {
			this.tcpSocket = socket;
			this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			LOGGER.error("Oops IO error:" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void run() {
		List<Object> args = null;
		try {
			args = (List<Object>) this.in.readObject();
			LOGGER.info("Psystem - Received order -> '" + args.get(0) + "'");
			LOGGER.info("Psystem - " + args.get(0) + " " + args.get(1) + ", msg received.");
			this.out.writeObject("to be done method selected");
			
		} catch (EOFException e) {
			LOGGER.error("Oops EOF error" + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("Oops IO error:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			LOGGER.error("Oops casting error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				LOGGER.error("OOps IO error:" + e.getMessage());
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