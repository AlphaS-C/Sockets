package co.uniquindio.progiii.subastasquindio.application;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;

public class Servidor {

	ServerSocket serverComunicacion;
	int puerto;

	private ObjectInputStream flujoEntradaComunicacion;
	private ObjectOutputStream flujoSalidaComunicacion;
	private CasaSubastas subastasQuindio;


	public Servidor(int puerto) {
		this.puerto = puerto;
	}


	public void runServer() throws IOException{
		serverComunicacion = new ServerSocket(puerto);
		while(true){

			System.out.println("<----------------Servidor iniciado----------------------------");
			Socket socketComunicacion = null;

			socketComunicacion = serverComunicacion.accept();

			System.out.println("Conexion establecida");

//			flujoEntradaComunicacion = new ObjectInputStream(socketComunicacion.getInputStream());
			
			// Flujo de entrada en null debido a que al crearse un flujo de entrada,
			// este debe escribirse con algun dato, sino, el servidor se quedá esperando a que
			// se escriba uno. En este caso, no vamos a recibir objetos, por lo que no hace falta
			// tener un flujo de entrada. Quitar en caso de necesitar recibir datos.
			flujoEntradaComunicacion = null;
			flujoSalidaComunicacion = new ObjectOutputStream(socketComunicacion.getOutputStream());
			iniciarHiloClienteServidor();
		}

	}

	private void iniciarHiloClienteServidor() {

		HiloClienteServidor hiloClienteServidor = new HiloClienteServidor();
		hiloClienteServidor.inicializar(flujoEntradaComunicacion,flujoSalidaComunicacion,this);
		hiloClienteServidor.start();
	}


	public CasaSubastas getCasa() {
		return subastasQuindio;
	}


	public void setCasa(CasaSubastas subastasQuindio) {
		this.subastasQuindio = subastasQuindio;
	}

	

}
