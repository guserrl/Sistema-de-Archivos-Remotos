import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
public static void main(String[] args) {
	//List<Usuario> l = new ArrayList<>();
	ExecutorService pool = Executors.newCachedThreadPool();
	File root = new File("root");root.mkdir();File prueba= new File(root,"prueba");prueba.mkdir();
	try {
		ServerSocket ss = new ServerSocket(5555);
		while(true) {
			Socket s = ss.accept();
			pool.execute(new atenderPeticion(s,root));
			//atender peticion
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
