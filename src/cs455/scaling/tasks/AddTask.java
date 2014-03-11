package cs455.scaling.tasks;

import cs455.scaling.threadpool.Worker;


public class AddTask implements Task {

	private int first;
	private int second;
	private int index;
	
	public AddTask(int index, int first, int second){
		this.first = first;
		this.second = second;
		this.index = index;
	}
	
	@Override
	public void execute(Worker worker) {
		System.out.println("AddTask execute method");
		int total = this.first + this.second;
		System.out.println("AddTaskID: " + index + " total:  " + total);
		
		worker.setIdle(true);
	}
	
	
	public String getType(){
		return "addTask";
	}

	public void run() {
		
	}


}
