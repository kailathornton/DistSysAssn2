package cs455.scaling.tasks;

import cs455.scaling.threadpool.Worker;

public interface Task extends Runnable{

	public void execute(Worker worker);
	
}
