/**   
 * Copyright © 2019 公司名. 八维
 * 
 * @Title: UserTest.java 
 * @Prject: gjh-redis-test
 * @Package: test 
 * @Description: TODO
 * @author:  葛家辉  
 * @date: 2019年9月6日 上午10:04:40 
 * @version: V1.0   
 */
package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gejiahui.common.utils.DateUtil;
import com.gejiahui.common.utils.RandomUtil;
import com.gejiahui.common.utils.StringUtil;

import gjh.domain.User;

/** 
 * @ClassName: UserTest 
 * @Description: TODO
 * @author: 葛家辉
 * @date: 2019年9月6日 上午10:04:40  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class UserTest {
	@Resource
	RedisTemplate<String, User> redisTemplate;
	

	@Test
	public void create() {
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 100000; i++) {
			User user = new User(i, StringUtil.randomChineseString(3), StringUtil.randomSex(),
					RandomUtil.random13Phone(), 
					StringUtil.randomEmile(), DateUtil.randomDate(18, 70));
			redisTemplate.opsForValue().set(user.getId()+"", user);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("10万条数据用时:"+(end - start)+"毫秒");
		
	}
}
