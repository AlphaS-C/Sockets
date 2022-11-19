package co.edu.uniquindio.progiii.subastasquindio.application;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

public class AplicacionCliente {


	public static void main(String[] args) {
		
		// ESTO ES UN EJEMPLO DE LOS PEDAZOS DE CODIGO QUE IRIAN DENTRO DEL SINGLETON

		System.out.println("Iniciando aplicacion cliente local");
		
		// PRIMER EJEMPLO, PIDE EL XML CON EL CODIGO "CargarXML"
		
		System.out.println("Cargando XML");
		System.out.println("Creando hilo cargador");
		HiloGeneral cargador = new HiloGeneral("CargarXML");
		cargador.start();
		try {
			cargador.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		CasaSubastas casa = cargador.getXML();
		System.out.println("XML Cargado");
		
		System.out.println("Cargando primer usuario del XML: "+casa.getListaUsuarios().get(0));
		
		// SEGUNDO EJEMPLO: VALIDA UN USUARIO Y DA UNA RESPUESTA:
		
		System.out.println("Creando usuario nuevo");
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("uwu");
		usuario.setContrasena("contra123");
		
		System.out.println("Creando hilo validador");
		HiloGeneral validator = new HiloGeneral("ValidarUsuario", usuario);
		validator.start();
		try {
			validator.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta del servidor: " + validator.getRespuesta());
		
		// TERCER EJEMPLO: PEDIMOS SERIALIZAR EL XML, ES DECIR, GUARDARLO EN EL SERVIDOR
		
		System.out.println("Serializando XML...");
		HiloGeneral serializador = new HiloGeneral("Serializar", casa);
		serializador.start();
		
		try {
			serializador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
