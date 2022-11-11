package co.uniquindio.progr3.seg.sockets;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	
	public Database() {}


	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}


	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	// quemo en memoria 3 usuarios de prueba
	public void initialize() {
        Usuario user = new Usuario("test", "123");
        getListaUsuarios().add(user);

        user = new Usuario("admin", "admin");
        getListaUsuarios().add(user);

        user = new Usuario("user", "321");
        getListaUsuarios().add(user);
	}
	
	
	// Funcion que valida si existe un usuario dentro de la base de datos, devuelve un String
	public String validar(Usuario usuario) {
		String validacion = "Usuario no encontrado en la base de datos";
		for (Usuario usr: listaUsuarios) {
			if (usr.equals(usuario)) validacion = "Usuario encontrado!";
		}
		
		return validacion;
	}
	
}
