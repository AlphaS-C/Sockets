package co.uniquindio.progr3.seg.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {


	// puerto y host

	Usuario usuario;
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
		this.usuario = new Usuario("Usuario de prueba por defecto", "922928");
	}


	public void iniciarCliente() {
		try {
			
			crearConexion();
			flujoSalida = new ObjectOutputStream(socketComunicacion.getOutputStream());
			flujoEntrada = new ObjectInputStream(socketComunicacion.getInputStream());
		
			enviarUsuario(usuario);
			
			System.out.println(recibirRespuesta());

			flujoEntrada.close();
			socketComunicacion.close();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Funcion que envia un usuario por el flujo de Salida
	private void enviarUsuario(Usuario usuario) {
		try {
			System.out.println("Enviando usuario " + usuario.getUsuario() + "-" + usuario.getContraseña());
			flujoSalida.writeObject(usuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// retorna un objeto de tipo String del flujo de Entrada
	private String recibirRespuesta() throws ClassNotFoundException, IOException {
		return (String) flujoEntrada.readObject();
	}


	private void crearConexion() throws IOException {
		socketComunicacion = new Socket(host,puerto);
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
