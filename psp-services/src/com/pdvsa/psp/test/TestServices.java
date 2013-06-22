package com.pdvsa.psp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.service.IUserService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:META-INF/spring-base.xml")
//public class TestServices {
//
//	@Autowired
//	IUserService userService;
//
//	@Test
//	public void testUserService() {
//		List<Rol> roles = userService.getAllRoles();
//		System.out.println(roles.size());
//	}
//
//}
