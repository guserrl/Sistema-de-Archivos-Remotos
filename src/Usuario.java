import java.io.File;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable{
	private String nombre;
	private String ip;
	private int puerto;
	public Usuario(String nombre, String ip, int puerto) {
		super();
		this.nombre = nombre;
		this.ip = ip;
		this.puerto = puerto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPuerto() {
		return puerto;
	}
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	
}
