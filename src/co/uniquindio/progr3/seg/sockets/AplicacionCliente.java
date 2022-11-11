package co.uniquindio.progr3.seg.sockets;

public class AplicacionCliente {


	public static void main(String[] args) {


		Cliente cliente = new Cliente("localhost",9999);
		cliente.setUsuario(new Usuario("admin", "admin"));
		cliente.iniciarCliente();

		Cliente cliente2 = new Cliente("localhost",9999);
		cliente2.setUsuario(new Usuario("usuario", "123"));
		cliente2.iniciarCliente();
		
		Cliente cliente3 = new Cliente("localhost",9999);
		cliente3.iniciarCliente();
	}
}
