package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.smart.domain.User;

import static org.testng.Assert.*;

import java.util.Date;

@ContextConfiguration("classpath*:/smart-context.xml") // 启动Spring容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
	@Autowired
	private UserService userService;

	@Test
	public void testHasMatchUser() {
		boolean b1 = userService.hasMatchUser("admin", "123456");
		boolean b2 = userService.hasMatchUser("admin", "111111");
		assertTrue(b1);
		assertFalse(b2);
	}

	@Test
	public void testFindUserByUserName() throws Exception {
		for (int i = 0; i < 100; i++) {
			User user = userService.findUserByUserName("admin");
			assertEquals(user.getUserName(), "admin");
		}
	}

	@Test
	public void testAddLoginLog() {
		User user = userService.findUserByUserName("admin");
		user.setUserId(1);
		user.setUserName("admin");
		user.setLastIp("192.168.12.7");
		user.setLastVisit(new Date());
		userService.loginSuccess(user);
	}
}
