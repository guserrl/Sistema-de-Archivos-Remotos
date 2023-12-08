import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class atenderPeticion extends Thread{
private Socket s;
private File f;
private ObjectInputStream si;
private ObjectOutputStream so;
public atenderPeticion(Socket s,File f) {
	super();
	this.s = s;
	this.f=f;
	try {
		so = new ObjectOutputStream(s.getOutputStream());
		si = new ObjectInputStream(s.getInputStream());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@Override
	public void run() {
		// TODO Auto-generated method stub
	while(true) {
	try {
		//System.out.println("Antes de i");
		int i = si.readInt();//System.out.println("Despues de i");
			if(i==1) {//listar
				//System.out.println("Cosa a listar");
				this.so.writeObject(f);
				so.reset();
				so.flush();
			}else if(i==2) {//Descargar
				//Primero lee el nombre
				String archivo = si.readLine();
				so.writeObject(new File(this.f,archivo));
				so.reset();so.flush();
//				try(BufferedInputStream b = new BufferedInputStream(new FileInputStream(new File(this.f,archivo)))) {
//					byte buff[] = new byte[1024];
//					int leido = b.read(buff);
//					while(leido!=-1) {
//						so.write(buff, 0, leido);
//						leido = b.read(buff);so.flush();
//					}
//				}catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}so.reset();
			}else if(i==3) {//Subir
				String archivo = si.readLine(); //Leo el nombre 
				File f = (File) si.readObject(); //Leo file
				try(BufferedOutputStream b = new BufferedOutputStream(new FileOutputStream(new File(this.f,archivo)));
						BufferedInputStream bi = new BufferedInputStream(new FileInputStream(f))) {
					byte buff[] = new byte[1024];
					int leido = bi.read(buff);
					while(leido!=-1) {
						b.write(buff, 0, leido);
						leido = bi.read(buff);
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	}
}
