import cs455.scaling.tasks.AddTask;
import cs455.scaling.threadpool.ThreadPoolManager;


public class testProg {

	public static void main(String[] args){
		
		
		
		ThreadPoolManager manager = new ThreadPoolManager(3);
		Thread manThread = new Thread(manager);
		
		manThread.start();
		
		for(int i = 0; i < 10; i++){
			Runnable r = new AddTask(i, 1, 2);
			manager.execute(r);

		}
		
		
		
		
		
	}
	
	
}
