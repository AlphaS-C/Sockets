package co.uniquindio.progr3.seg.sockets;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {


	ServerSocket serverComunicacion;
	int puerto;

	private ObjectInputStream flujoEntradaComunicacion;
	private ObjectOutputStream flujoSalidaComunicacion;
	private Database db;


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

			flujoEntradaComunicacion = new ObjectInputStream(socketComunicacion.getInputStream());
			flujoSalidaComunicacion = new ObjectOutputStream(socketComunicacion.getOutputStream());

			iniciarHiloClienteServidor();
		}

	}

	private void iniciarHiloClienteServidor() {

		HiloClienteServidor hiloClienteServidor = new HiloClienteServidor();
		hiloClienteServidor.inicializar(flujoEntradaComunicacion,flujoSalidaComunicacion,this);
		hiloClienteServidor.start();
	}


	public Database getDb() {
		return db;
	}


	public void setDb(Database db) {
		this.db = db;
	}

	

}
