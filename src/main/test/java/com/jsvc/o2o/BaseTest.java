package com.jsvc.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName BaseTest
 * @Author sensu
 * @Date 2019/8/22 20:22
 * 配置spring和junit整合， junit启动时加载springIOC容器
 **/
//使用SpringJUnit4ClassRunner这个类来跑test
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class BaseTest {
}
