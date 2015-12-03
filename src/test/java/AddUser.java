import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hunter.dao.FileDao;
import com.hunter.model.File;
import com.hunter.model.LoginUser;
import com.hunter.service.DBService;
import com.hunter.util.Utils;
/**
 * 在一个实体文件中，注解要么全部放在字段上，要么全部放在get方法上，不能混合使用！
 * @author hun
 * @Description: TODO
 * @date 2015年11月26日 下午9:11:30
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"}) 
public class AddUser {
	@Resource(name="dBService")
	private DBService dBService;
	@Resource(name="fileDao")
	private FileDao fileDao;
	/*@Resource
	private DataSource dataSource; 
	@Test
	public void testDataSource() throws Exception{
		
		System.out.println(dataSource.getConnection());
	}*/
	@Test
	public void test1(){
		boolean v=dBService.getLoginUser("admin", "admin");
		System.out.println(v);
	}
	/*@Test
	public void Test2(){
		dBService.getUserFile("qq");
	}*/
	@Test
	public void Test(){
		LoginUser o = new LoginUser();
		o.setDescription("111111");
		o.setUsername("admin");
		o.setPassword("admin");
		//o.setFiles(files);
		dBService.saveUser(o);
	}
	@Test
	public void TestFile(){
		for (int i = 0; i < 30; i++) {
			Date creationDate= new Date(new java.util.Date().getTime());
			
			File file = new File();
			file.setCreationDate(creationDate);
			file.setFilename("ww"+i);
			file.setSize(123456);
			file.setUrl("qqqqq");
			dBService.saveFile(file, "admin");
		}
		
		
	}
	@Test
	public void TestFindFile(){
		List<Object> list = dBService.getUserFile("qq", 1, 5);
		JSONArray json = new JSONArray();
		for (Object object : list) {
			JSONObject jo = new JSONObject();
			File f=(File)object;
			jo.put("filename", f.getFilename());
			jo.put("size", f.getSize());
			jo.put("creationDate", f.getCreationDate());
			System.out.println(f.getCreationDate());
			json.add(jo);
			//System.out.println(f.getFilename());
		}
		System.out.println(json.toJSONString());
		//Map<String ,Object> jsonMap = new HashMap<String,Object>();
		//jsonMap.put("total",1);
		//jsonMap.put("rows", list);
		//String json = JSON.toJSONString(list);
		//System.out.println(json);
		//log.info(json);
		//Utils.write2PrintWriter(json);
	}

}
