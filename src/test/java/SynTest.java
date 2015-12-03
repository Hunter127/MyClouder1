import com.hunter.util.HUtils;


public class SynTest implements Runnable {

	@Override
	public void run() {
		try{
			HUtils.upload("D:\\hadoop/4.exe", "/007/19.exe");
		}catch (Exception e) {  
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception {
		SynTest s = new SynTest();
		Thread thread = new Thread(s);
		
		Listenter l=new Listenter();
		Thread TT = new Thread(l);
		
		thread.start();
	    
			TT.start();
	
		
		
	}

}
