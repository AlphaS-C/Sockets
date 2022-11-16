package co.edu.uniquindio.progiii.subastasquindio.application;

public class AplicacionCliente {


	public static void main(String[] args) {

		// Creo un cliente que va a simular el proyecto de SubastasQuindio Principal
		Cliente cliente = new Cliente("localhost",9999);
		// El programa será un hilo.
		cliente.iniciarCliente();
		// En el hilo, se cargan los valores desde el archivo persistencia
		System.out.println("Comprobando que se recibio el XML:" + "\n" + "Imprimiento el primer usuario del archivo");
		// Imprimimos, desde el objeto cliente (creado localmente aca), los valores que hay en
		// la base de datos guardada en persistencia para validar que la informacion se transfirio
		// de manera correcta.
		System.out.println(cliente.getSubastasQuindio().getListaUsuarios().get(0));
	}
}
