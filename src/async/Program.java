package async;

public class Program {


	static void print() {

		Thread thread = Thread.currentThread();
		String threadName = thread.getName();

		for(int i=0;i<1000;i++) {
			if(thread.isInterrupted()) {
				System.out.println(threadName+ "::::THREAD EXIT@@@@@@@@@@@@@@@@@@@@@@@@@@");
				return;
			}

			System.out.printf("%s : %d\n",threadName,i+1);
		}
	}

	public static void main(String[] args) {


		Thread thread1 = new Thread(()->{
			print();
		});
		thread1.start();
//		System.out.println("===========1==========");
//		System.out.println("ID : "+thread1.getId());
//		System.out.println("Name : "+thread1.getName());
//		System.out.println("Priority : "+thread1.getPriority());
//		System.out.println("State : "+thread1.getState().toString());
//		System.out.println("======================");


		Thread mainThread = Thread.currentThread();
		mainThread.setPriority(Thread.MAX_PRIORITY);

		print();
		thread1.interrupt();

		try {
			thread1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("MAIN EXIT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

	}


}
