package co.uniquindio.progr3.seg.sockets;

import java.io.IOException;

public class AplicacionServer {

	public static void main(String[] args) {

		// creo la base de datos, donde van a estar los usuarios a comparar
		Database db = new Database();
		// inicializo los valores iniciales de la base de datos
		db.initialize();
		Servidor servidor = new Servidor(9999);
		// agrego la base de datos al servidor
		servidor.setDb(db);
		try {
			servidor.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
