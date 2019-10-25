package threads;

class Rel{
	String kereta;
	int jarak = 0;
}

class Kereta extends Thread{
	private Rel rel;
	
	public Kereta(String namaKereta, Rel rel){
		super(namaKereta);
		this.rel = rel;
	}
	
	public void run(){
		synchronized(rel){
			rel.kereta = Thread.currentThread().getName();
			for(int i = 0; i < 20; i++){
				rel.jarak = i;
				for(int j = 0; j < rel.jarak; j++){
					System.out.print("=");
				}
				System.out.print("###"+rel.kereta+"###@^>");
				for(int j = 20-1; j > i; j--){
					System.out.print("=");
				}
				System.out.println();
				try{
					Thread.sleep(300);
				}catch(Exception e){}
			}
		}
	}
}

public class KeretaThread {
	
	public static void main(String[] args) {
		Rel relKereta = new Rel();
		Kereta ka1 = new Kereta("KA-001", relKereta);
		Kereta ka2 = new Kereta("KA-002", relKereta);
		Kereta ka3 = new Kereta("KA-003", relKereta);
		Kereta ka4 = new Kereta("KA-004", relKereta);
		
		ka1.start();
		ka2.start();
		ka3.start();
		ka4.start();
	}

}
