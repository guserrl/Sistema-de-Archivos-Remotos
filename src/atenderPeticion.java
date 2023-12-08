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

	try {
		//System.out.println("Antes de i");
		int i = si.readInt();//System.out.println("Despues de i");
			if(i==1) {//listar
				//System.out.println("Cosa a listar");
				this.so.writeObject(f);
				so.flush();
			}else if(i==2) {//Descargar
				//Primero lee el nombre
				String archivo = si.readLine();
				try(BufferedInputStream b = new BufferedInputStream(new FileInputStream(new File(this.f,archivo)))) {
					byte buff[] = new byte[1024];
					int leido = b.read(buff);
					while(leido!=-1) {
						so.write(buff, 0, leido);
						leido = b.read(buff);
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}so.flush();
			}else if(i==3) {//Subir
				String archivo = si.readLine(); //Leo el nombre 
				try(DataOutputStream b = new DataOutputStream(new FileOutputStream(new File(this.f,archivo)))) {
					byte buff[] = new byte[1024];
					int leido = si.read(buff);
					while(leido!=-1) {
						b.write(buff, 0, leido);
						leido = si.read(buff);
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
