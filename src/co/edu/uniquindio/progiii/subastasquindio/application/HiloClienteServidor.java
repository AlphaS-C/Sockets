package co.edu.uniquindio.progiii.subastasquindio.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.progiii.subastasquindio.exceptions.UserNotFoundException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.WrongPasswordException;
import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;


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
		
			try {
				String accionDelCliente = recibirString();
				
				switch (accionDelCliente) {
				case "ValidarUsuario": {
					
					validarUsuario();
					break;
				}
				
				case "CargarXML" :
					
					cargarXML();
					break;
				
				case "Serializar":
					
					serializar();
					break;
					
				default:
					throw new IllegalArgumentException("Unexpected value: " + accionDelCliente);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
	}
	
	private void serializar() {
		CasaSubastas casaRecibida = null;
		try {
			casaRecibida = recibirCasa();
			HiloGeneralServidor cargador = new HiloGeneralServidor("Serializar", casaRecibida);
			cargador.start();
			cargador.join();
			enviarString(cargador.getRespuesta());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void cargarXML() throws IOException {
		
		System.out.println("Enviando XML a cliente...");
						// Le envia el retorno de la funcion getXML, que llama a un hilo a conseguirla
		flujoSalidaComunicacion.writeObject(getXML());
	}
	
	
	// Funcion que obtiene el XML a traves de hilos
	private CasaSubastas getXML() {
		HiloGeneralServidor cargador = new HiloGeneralServidor("CargarXML");
		cargador.start();
		try {
			cargador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return cargador.getXML();
	}

	private void validarUsuario() throws ClassNotFoundException, IOException {
				CasaSubastas casa = getXML();
				System.out.println("recibiendo usuario");
				Usuario usuarioRecibido = recibirUsuario();

					
				System.out.println("Usuario: " + usuarioRecibido.getNombreUsuario() + "\n" + "Contraseña: " + usuarioRecibido.getContrasena());
					
					try {
						casa.login(usuarioRecibido.getNombreUsuario(), usuarioRecibido.getContrasena());
						enviarString("Usuario encontrado");
						
					} catch (UserNotFoundException e) {
						enviarString("Usuario no encontrado");
					} catch (WrongPasswordException e) {
						enviarString("Contraseña incorrecta");
					}
	}

	private Usuario recibirUsuario() throws ClassNotFoundException, IOException {
			return (Usuario) flujoEntradaComunicacion.readObject();
	}
	
	private CasaSubastas recibirCasa() throws ClassNotFoundException, IOException {
		return (CasaSubastas) flujoEntradaComunicacion.readObject();
}
	
	private String recibirString() throws ClassNotFoundException, IOException {
		return (String) flujoEntradaComunicacion.readObject();
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
