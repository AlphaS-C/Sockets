package co.uniquindio.progr3.seg.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class HiloClienteServidor extends Thread{


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
		try {
			
			Usuario usuarioRecibido = recibirUsuario();
			System.out.println("Usuario recibido: " + usuarioRecibido.getUsuario() + "-" + usuarioRecibido.getContraseña());

			System.out.println("Validando...");
			// Envio como string la respuesta de la base de datos
			enviarString(servidor.getDb().validar(usuarioRecibido));
			System.out.println("Respuesta enviada!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Funcion que recibe un usuario del flujo de Entrada
	private Usuario recibirUsuario() throws ClassNotFoundException, IOException {
		return (Usuario) flujoEntradaComunicacion.readObject();
	}

	// Funcion que envia un String por el flujo de Salida
	private void enviarString(String mensaje) {
		try {
			flujoSalidaComunicacion.writeObject(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
