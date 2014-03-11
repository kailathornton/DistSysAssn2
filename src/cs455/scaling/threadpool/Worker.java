package cs455.scaling.threadpool;

import cs455.scaling.tasks.Task;

public class Worker implements Runnable{

	//only have access to ONE task at a time
	//threadManager can hand them a currentTask
	protected Object lock = new Object();

	volatile private boolean idle;
	private Task currentTask;
	public int id;

	public Worker(int id){
		this.id = id;
		idle = true;
		currentTask = null;
	}


	@Override
	public void run() {

		while(!isStopped()){	

			synchronized(lock){
				while(idle){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(currentTask != null){
					synchronized(currentTask){
						currentTask.execute(this);
						currentTask = null;
						setIdle(true);
					}
				}
			}

		}
	}

	synchronized public boolean isStopped(){
		return false;
	}


	synchronized public boolean isIdle(){
		return idle;
	}

	synchronized public void setIdle(boolean flag){
		idle = flag;
	}

	public void setCurrentTask(Task t){
		setIdle(false);
		currentTask = t;
	}

}
