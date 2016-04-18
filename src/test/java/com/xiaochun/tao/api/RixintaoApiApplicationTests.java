package com.xiaochun.tao.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.DigestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RixintaoApiApplication.class)
@WebAppConfiguration
public class RixintaoApiApplicationTests {

	@Test
	public void contextLoads() {
		int appid=123434;
		String data="{\"username\":\"duduba\",\"password\":\"ilovedudu\"}";
		int salt=3293232;
		String token="[5)JL\\\">jTD$B+!mP`tM|eS^E-_#03gHa?v{nC<wq";
		
		StringBuilder md5String = new StringBuilder();
		md5String.append(appid).append(data).append(salt).append(token);
		String md5 = DigestUtils.md5DigestAsHex(md5String.toString().getBytes());
		
		System.out.println(token + "-->" + md5);
	}

}
