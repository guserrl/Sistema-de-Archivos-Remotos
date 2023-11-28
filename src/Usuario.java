import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
//A lo mejor dejar esta clase con otro nombre y hacer una clase usuario a part con el nombre y home 
public class Usuario {
private String nombre;
private File home;
private Socket s;
private ObjectOutputStream so;
private ObjectInputStream si;

public Usuario(String nombre, File home, Socket s) {
	super();
	this.nombre = nombre;
	this.home = home;this.home.mkdir();	
	this.s = s;
	//Asociamos 
	//En principal se conectara al server y el creara home y todo
	try {
		this.so = new ObjectOutputStream(this.s.getOutputStream());
		this.si = new ObjectInputStream(this.s.getInputStream());
	}catch(IOException e) {
		e.printStackTrace();
	}
}

public void conexion() {
	//Envio home y lo crea
	try {
		//opcion 0 
		so.writeInt(0);
		so.writeObject(home);//file home a crear, asegurarme cuando lo crea sea nombr/home en principal
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void creaDirectorio() {
	System.out.println("Fichero a crear");
	Scanner sc = new Scanner(System.in);
	String f = sc.nextLine();
	File dir = new File(this.home,f);
	try {
		//1 crear dir
		so.writeInt(2);
		this.so.writeObject(dir);//Esto lo lee el server y el creara el fichero
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void creaFichero() {
	System.out.println("Fichero a crear");
	Scanner sc = new Scanner(System.in);
	String f = sc.nextLine();
	File fich = new File(this.home,f);
	try {
		//2. crear fichero
		so.writeInt(2);
		this.so.writeObject(fich);//Esto lo lee el server y el creara el fichero
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void subirArchivo() {
	System.out.println("Escribe archivo a subir:");
	Scanner sc = new Scanner(System.in);
	String s = sc.nextLine();
	File f = new File(s);
	try {
		so.writeInt(3);
		so.writeObject(f);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public void subirCarpeta() {
	System.out.println("Escribe directorio a subir:");
	Scanner sc = new Scanner(System.in);
	String s = sc.nextLine();
	File f = new File(s);
	try {
		so.writeInt(4);
		so.writeObject(f);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void mostrar() {
	try {
		so.write(5);
		File f = (File) si.readObject();
		for(File f2 : f.listFiles()) {
			if(f2.isDirectory()) System.out.println("Dir: ");
			if(f2.isFile()) System.out.println("File: ");
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void mostrarFile() {
	try {
		so.writeInt(6);
		File f = (File) si.readObject();
		//rafndomaccesfile y leer y mostrar?
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void mostrarDir() {
	
}

public void leerFile() {
	
}
//Crear directorios
//Crear ficheros
//Subir carpeta o directorio a home
//Enviar fichero a alguien
//Esperar y recibir un fichero
//en el servidor un xml que almacene los usuarios que han usado el server
//ver archivos en dir y leer file
}
