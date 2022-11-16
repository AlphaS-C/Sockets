package co.edu.uniquindio.progiii.subastasquindio.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;


public class HiloClienteServidor extends Thread{

	// Flujos de entrada y salida
	private ObjectInputStream flujoEntradaComunicacion;
	private ObjectOutputStream flujoSalidaComunicacion;

	Servidor servidor;


	public void inicializar(ObjectInputStream flujoEntradaComunicacion, ObjectOutputStream flujoSalidaComunicacion,
			 Servidor servidor) {

		this.flujoEntradaComunicacion = flujoEntradaComunicacion;
		this.flujoSalidaComunicacion = flujoSalidaComunicacion;
		this.servidor = servidor;
	}

	@Override
	public void run() {
		
			enviarCasa(servidor.getCasa());
			System.out.println("Enviando XML...");
			System.out.println(servidor.getCasa().getListaUsuarios().get(0));
	}
	
	// envia la casaSubastas al flujo de salida
	private void enviarCasa(CasaSubastas casa) {
		try {
			flujoSalidaComunicacion.writeObject(casa);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// permite enviar un String
	private void enviarString(String mensaje) {
		try {
			flujoSalidaComunicacion.writeObject(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
