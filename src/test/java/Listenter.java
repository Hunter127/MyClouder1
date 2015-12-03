import com.hunter.util.HUtils;

/**
 * 上传监听器
 * @author hun
 * @Description: TODO
 * @date 2015年11月20日 下午2:27:18
 */
public class Listenter implements Runnable{

	@Override
	public void run() {
		try{
		Thread.sleep(5000);
		while(HUtils.getStatus()){
			int size=HUtils.getSize("/007/19.exe");
			System.out.println((double)size/1024/1024);
		}
		}catch (Exception e) {  
			e.printStackTrace();
		}
		}
		

}
