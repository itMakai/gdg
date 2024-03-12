public class hello {
	int counter = 1;
	static int N;

	public void printOddNumber() {
		synchronized (this) {
			while (counter < N) {
				while (counter % 2 == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(counter + " ");
				counter++;
				notify();
				
			}
			
		}
		System.out.println();
	}

	public void printEvenNumber() {
		synchronized (this) {
			while (counter < N) {
				while (counter % 2 == 1) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(counter + " ");
				counter++;
				notify();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println();
		N = 10;
		hello mt = new hello();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mt.printEvenNumber();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mt.printOddNumber();
			}
		});

		t1.start();
		t2.start();
	}
		
}