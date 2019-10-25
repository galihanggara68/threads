package threads;

import java.util.ArrayList;
import java.util.List;

//class TestThread implements Runnable{
//	public void run(){
//		for(int i = 0; i < 10; i++){
//			System.out.println("i="+i);
//		}
//	}
//}
//
//class MyThread extends Thread{
//	private SyncThread st;
//	public MyThread(String name, SyncThread s){
//		super(name);
//		this.st = s;
//	}
//	
//	public void run(){
//		System.out.print(Thread.currentThread().getName());
//		st.printList();
//	}
//}
//
//class SyncThread {
//	private List<String> list;
//	
//	public SyncThread(){
//		list = new ArrayList<String>();
//		list.add("Test");
//		list.add("Test 2");
//		list.add("Test 3");
//		list.add("Test 4");
//		list.add("Test 5");
//		list.add("Test 6");
//	}
//	
//	public void printList(){
//		//synchronized (list) {
//			for(int i = 0; i < list.size(); i++){
//				System.out.println(list.get(i));
//			}
//		//}
//	}
//}

class Orang extends Thread{
	private String[] keranAir; // Data yang dishare ke semua Thread
	
	public Orang(String name, String[] keranAir){
		super(name); // Memberikan nama ke Thread
		this.keranAir = keranAir; // Set data yang akan dishare
	}
	
	public void run(){
		printData();
	}
	
	private void printData(){
		synchronized (keranAir) {
			// Merubah data String dengan nama Thread yang sedang mengakses data
			keranAir[0] = Thread.currentThread().getName() + " 1";
			keranAir[1] = Thread.currentThread().getName() + " 2";
			keranAir[2] = Thread.currentThread().getName() + " 3";
			// Cetak data
			System.out.println(keranAir[0]);
			System.out.println(keranAir[1]);
			System.out.println(keranAir[2]);
		}
	}
}

public class MainThread {

	public static void main(String[] args) {
		String[] keranAir = {"Test", "Test 2", "Test 3"};
		Orang t1 = new Orang("T1", keranAir);
		Orang t2 = new Orang("T2", keranAir);
		Orang t3 = new Orang("T3", keranAir);
		t1.start();
		t2.start();
		t3.start();
	}

}
