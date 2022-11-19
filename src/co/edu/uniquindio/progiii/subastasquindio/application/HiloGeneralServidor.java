package co.edu.uniquindio.progiii.subastasquindio.application;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.persistencia.Persistencia;

public class HiloGeneralServidor extends Thread {

	
	String accion;
	Object objeto;
	String respuesta;
	
	
	
	public HiloGeneralServidor(String accion) {
		super();
		this.accion = accion;
	}
	
	

	public HiloGeneralServidor(String accion, Object objeto) {
		super();
		this.accion = accion;
		this.objeto = objeto;
	}



	@Override
	public void run() {
		
		switch (accion) {
		case "Serializar": {
			serializar();
			break;
		}
		
		case "CargarXML":
			cargarXML();
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + accion);
		}
	}

	private synchronized void serializar() {
		
		Persistencia.guardarRecursoCasaSubastasXML( (CasaSubastas) objeto);
		System.out.println("Datos serializados con éxito.");
		respuesta = "Datos guardados en el servidor!";
	}

	private synchronized void cargarXML() {
		
		objeto = Persistencia.cargarRecursoCasaSubastasXML();
		System.out.println("Datos cargados con exito");
	}
	
	
	public CasaSubastas getXML() {
		return (CasaSubastas)objeto;
	}


	public String getRespuesta() {
		return respuesta;
	}

	
}
