package cs455.scaling.threadpool;

import java.util.Arrays;

import cs455.scaling.tasks.Task;


public class ThreadPoolManager implements Runnable{
	ThreadPool threadPool;


	public ThreadPoolManager(int numberOfThreads){
		threadPool = new ThreadPool(numberOfThreads);

	}

	public void findThread(Runnable t){
		//		System.out.println("ThreadPoolManager: Find Thread");

		//	for(Worker w : threadPool.workers){
		//			System.out.print(w.id + "   ");
		//	}System.out.println();

		for(int i = 0; i < threadPool.workers.length; i++){

			if(threadPool.workers[i].isIdle()){
				//				System.out.println(i + " "+ threadPool.workers[i].isIdle());

				Worker w = threadPool.workers[i];
				System.out.println("Worker id: " + w.id);

				synchronized(w.lock){
					w.setCurrentTask((Task)t);
					w.lock.notify();
				}

				break;
			}
		}

		//		boolean found = false;
		//		while (!found){
		//			
		//			if(threadPool.idleWorkers.size() != 0){
		//				synchronized(threadPool.idleWorkers){
		//					found = true;
		//					Worker w = threadPool.idleWorkers.pop();
		//					synchronized(threadPool.busyWorkers){
		//						threadPool.busyWorkers.add(w);
		//					}
		//					
		//					
		//				}
		//			}
		//			
		//			
		//		}


	}

	public void execute(Runnable r){
		synchronized(threadPool.workToDo){
			threadPool.workToDo.add(r);
		}
	}

	public void pollTaskQueue(){
		int ctr = 0;

		while(true){
			//System.out.println("ThreadPoolManager: pollTaskQueue \n");

			synchronized(threadPool.workToDo){
				if(threadPool.workToDo.size() != 0){
					ctr++; 
					//				System.out.println("How many times is findThread being called  " + ctr);

					//				System.out.println("Work queue size: " + threadPool.workToDo.size());

					Runnable r = threadPool.workToDo.pop();
					findThread(r);

				}
			}

		}
	}



	public void run() {
		pollTaskQueue();
	}


}
