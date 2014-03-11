package cs455.scaling.tasks;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import cs455.scaling.threadpool.Worker;

public class Read implements Task{
	
	private SelectionKey key;
	private Selector selector;
	
	public Read(Selector selector, SelectionKey key){
		this.key = key;
		this.selector = selector;
	}

	public void run() {
		
	}

	public void execute(Worker worker) {
		
	
	}
	
	
	
}
