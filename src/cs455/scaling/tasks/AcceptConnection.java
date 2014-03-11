package cs455.scaling.tasks;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import cs455.scaling.client.ClientInfo;
import cs455.scaling.threadpool.Worker;

public class AcceptConnection implements Task{

	private SelectionKey key;
	private Selector selector;
	
	
	public AcceptConnection(Selector selector, SelectionKey key){
		this.key = key;
		this.selector = selector;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void execute(Worker worker) {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		
		SocketChannel socketChannel = null;
		try {
			socketChannel = serverSocketChannel.accept();
			Socket socket = socketChannel.socket();
			
			ClientInfo client = new ClientInfo(socketChannel);
			
			
			socketChannel.configureBlocking(false);
					
			socketChannel.register(selector, SelectionKey.OP_READ, client);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Accepting incoming connection");
		
		
		worker.setIdle(true);
	
	}

}
