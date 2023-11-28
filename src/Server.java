import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
public static void main(String[] args) {
	List<Usuario> l = new ArrayList<>();
	ExecutorService pool = Executors.newCachedThreadPool();
	try {
		ServerSocket ss = new ServerSocket(5555);
		while(true) {
			//atender peticion
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
