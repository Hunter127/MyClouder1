import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hunter.util.HUtils;


public class HadoopTest {
	/**
	 * 测试读取配置文件
	 */
	@Test
	public void TestGetProperties(){
		//HUtils.getProperties();
		
	}
	@Test
	public void TestHDFS(){
		HUtils.getFs();
	}
	@Test
	public void TestUpload(){
		HUtils.upload("D:\\hadoop/4.exe", "/007/6.exe");
		
	}
	/**
	 * 测试创建文件夹
	 */
	@Test
	public void Testmkdir(){
		HUtils.mkdir("/0071");
	}

	@Test
	public void Testdown(){
		HUtils.downLoad("/007/8.exe", "D:\\hadoop/1");
	}

}
