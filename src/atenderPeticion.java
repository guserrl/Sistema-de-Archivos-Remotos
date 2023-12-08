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
		int i = si.readInt();
			if(i==1) {//listar
				this.so.writeObject(f);
				so.reset();
				so.flush();
			}else if(i==2) {//Descargar
				//Primero lee el nombre
				String archivo = si.readLine();
				so.writeObject(new File(this.f,archivo));
				so.reset();so.flush();
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
