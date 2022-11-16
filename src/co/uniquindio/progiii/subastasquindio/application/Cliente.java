package co.uniquindio.progiii.subastasquindio.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;

public class Cliente {


	// puerto y host

	CasaSubastas subastasQuindio;
	int puerto;
	String host;

	// socket cliente
	Socket socketComunicacion;

	// flujos de entrada y salida
	ObjectOutputStream flujoSalida;
	ObjectInputStream flujoEntrada;

	public Cliente(String host, int puerto) {
		this.puerto = puerto;
		this.host = host;
	}


	public void iniciarCliente() {
		try {
			crearConexion();
//			flujoSalida = new ObjectOutputStream(socketComunicacion.getOutputStream());
			flujoEntrada = new ObjectInputStream(socketComunicacion.getInputStream());
			subastasQuindio = recibirCasa();
			flujoEntrada.close();
			socketComunicacion.close();
			System.out.println("Recibiendo XML...");
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	private CasaSubastas recibirCasa() throws ClassNotFoundException, IOException {
		return (CasaSubastas) flujoEntrada.readObject();
	}
	


	private void crearConexion() throws IOException {
		socketComunicacion = new Socket(host,puerto);
	}


	public CasaSubastas getSubastasQuindio() {
		return subastasQuindio;
	}


	public void setSubastasQuindio(CasaSubastas subastasQuindio) {
		this.subastasQuindio = subastasQuindio;
	}


}
