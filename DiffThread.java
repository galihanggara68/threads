package threads;

class Sepatu{
	String status;
}

// Threads 
class Outsole extends Thread{
	private Sepatu sepatu;
	
	public Outsole(String name, Sepatu sepatu){
		super(name);
		this.sepatu = sepatu;
	}
	
	public void run(){
		try{
			sepatu.status = "Outsole Terpasang";
			Thread.sleep(2000);
			synchronized(sepatu){
				sepatu.notify();
			}
		}catch(Exception e){}
	}
}

class Emboss extends Thread{
	private Sepatu sepatu;
	
	public Emboss(String name, Sepatu sepatu){
		super(name);
		this.sepatu = sepatu;
	}
	
	public void run(){
		try{
			synchronized (sepatu) {
				sepatu.wait();
			}
		}catch(Exception e){}
		sepatu.status += " Emboss";
		System.out.println(sepatu.status);
		synchronized (sepatu) {
			sepatu.notify();
		}
	}
}

class Joiner extends Thread{
	private Sepatu sepatu;

	public Joiner(String name, Sepatu sepatu){
		super(name);
		this.sepatu = sepatu;
	}
	
	public void run(){
		try{
			synchronized (sepatu) {
				sepatu.wait();
			}
		}catch(Exception e){}
		sepatu.status += " Joined";
		System.out.println(sepatu.status);
	}
}

public class DiffThread {

	public static void main(String[] args){
		Sepatu sepatu = new Sepatu();
		
		Emboss emboss = new Emboss("Emboss", sepatu);
		Joiner joiner = new Joiner("Joiner", sepatu);
		Outsole outsole = new Outsole("Outsole", sepatu);
		
		emboss.start();
		joiner.start();
		outsole.start();
	}
	
}
