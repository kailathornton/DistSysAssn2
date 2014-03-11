package cs455.scaling.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class Server implements Runnable{
	private String hostName;
	private int portNum;
	private int threadPoolSize;
	
	private ServerSocketChannel serverChannel; //this is the channel incoming connections is on
	private Selector selector; //selector to be monitored
	
	private ByteBuffer readBuffer;	//read into an 8kb byte buffer
	
	

	public Server(int portNum, int threadPoolSize){
		
		this.portNum = portNum;
		this.threadPoolSize = threadPoolSize;
		
		this.selector = this.selectorSetup();
		this.readBuffer = ByteBuffer.allocate(8192);
		
	}

	private Selector selectorSetup(){
		Selector select = null;
		try {
			select = SelectorProvider.provider().openSelector();
			
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			
			serverChannel.socket().bind(new InetSocketAddress(portNum));
			
			serverChannel.register(select, SelectionKey.OP_ACCEPT);		//allows the selector to monitor this channel
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
				
		return select;		
		
	}
	
	
	//reading data
	//write data into the buffer
	//buffer.flip()
	//read data from the buffer
	//buffer.clear()

	public void run() {
		while(true){
			
			try {
				selector.select(); 		//sits waiting for connection
				
				Iterator selectKeys = selector.selectedKeys().iterator();
				
				while(selectKeys.hasNext()){
					SelectionKey key = (SelectionKey)selectKeys.next();
					selectKeys.remove();
					
					if(!key.isValid()) continue;
					
					if(key.isAcceptable()){
						//accept(key) as a task for my threadpool
						
					}else if(key.isReadable()){
						//read(key) as a task for threadpool
					}
					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}


	public static void main(String[] args){
		Server server = new Server(25384, 100);
		Thread t1 = new Thread(server);
		
		t1.start();
		
	}

}
