import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Principal {

public static void main(String[] args) {
	try {
		Socket s = new Socket("localhost",5555);
		Scanner sc = new Scanner(System.in);
		int i = -1;
		try {
			ObjectOutputStream so = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream si = new ObjectInputStream(s.getInputStream());
			
			do {
				System.out.println("1.Listar archivos");
				System.out.println("2.Descargar archivo");
				System.out.println("3.Subir archivo");
				i = sc.nextInt();
				
				switch(i) {
				case 1:{
					listar(so, si);
				}break;
				case 2:{
					descargar(so,si);
				}break;
				case 3:{
					subir(so, si);
				}
				}
			}while(i!=-1);
		}catch(IOException e) {
			e.printStackTrace();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void listar(ObjectOutputStream so,ObjectInputStream si) {
	try {
		so.writeInt(1);
		so.flush();
		File f = (File) si.readObject();
		for(File f2 : f.listFiles()) {
			if(f2.isDirectory()) System.out.println("Dir: "+f2.getName());
			else if(f2.isFile()) System.out.println("File: "+f2.getName());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void descargar(ObjectOutputStream so,ObjectInputStream si) {
	System.out.println("Archivo a descargar?");
	Scanner sc = new Scanner(System.in);
	String archivo = sc.nextLine();
	try {
		so.writeInt(2);
		so.writeBytes(archivo+"\n");
		so.flush();
		//ahora viene el archivo
		File f = (File) si.readObject();
		byte buff[] = new byte[1024];
		try(BufferedOutputStream b = new BufferedOutputStream(new FileOutputStream(f));
				BufferedInputStream bi = new BufferedInputStream(new FileInputStream(f)))	
				{
			int leidos = bi.read(buff);
			while(leidos!=-1) {
				b.write(buff, 0, leidos);
				System.out.println("antes leer ");
				leidos=bi.read(buff);
				System.out.println("despues de leer");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Termine");
	}
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

public static void subir(ObjectOutputStream so,ObjectInputStream si) {
	System.out.println("Archivo a subir?");
	Scanner sc = new Scanner(System.in);
	String archivo = sc.nextLine();
	try {
		so.writeInt(3);
		so.writeBytes(archivo+"\n");
		File f = new File(archivo);
		so.writeObject(f);
		so.reset();
		so.flush();
}catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}