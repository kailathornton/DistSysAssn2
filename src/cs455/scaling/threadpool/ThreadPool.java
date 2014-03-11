package cs455.scaling.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;

public class ThreadPool {
	
	private int numThreads;
	protected Worker[] workers;
	private Thread[] workerThreads;
	
//	protected LinkedList<Worker> busyWorkers;
//	protected LinkedList<Worker> idleWorkers;

	boolean isStopped;

	protected LinkedList<Runnable> workToDo;

	public ThreadPool(int num){
		numThreads = num;
		
		workers = new Worker[num];
//		busyWorkers = new LinkedList<Worker>();
//		idleWorkers = new LinkedList<Worker>();
		
		workerThreads = new Thread[num];

		for(int i = 0; i < numThreads; i++){
			Worker worker = new Worker(i);
			workers[i] = worker;
//			idleWorkers.add(worker);
			workerThreads[i] = new Thread(worker);
		}

		workToDo = new LinkedList<Runnable>();
		isStopped = false;
		startThreads();
	}

	//works should be started OUTSIDE of the constructor

	public void startThreads(){

		for(Thread t : workerThreads){
			t.start();
		}

	}



	public synchronized void stop(){
		isStopped = true;
		for(Thread t : workerThreads){
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



}
