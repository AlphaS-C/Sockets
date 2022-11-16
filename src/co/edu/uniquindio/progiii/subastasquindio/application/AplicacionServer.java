package co.edu.uniquindio.progiii.subastasquindio.application;

import java.io.IOException;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.persistencia.Persistencia;

public class AplicacionServer {

	public static void main(String[] args) {

		System.out.println("Cargando XML...");
		CasaSubastas subastasQuindio = Persistencia.cargarRecursoCasaSubastasXML();
		
		// inicializo los valores iniciales de la base de datos
		Servidor servidor = new Servidor(9999);
		servidor.setCasa(subastasQuindio); 
		try {
			servidor.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
