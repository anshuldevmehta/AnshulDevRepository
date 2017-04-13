package com.springinaction.springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringConfigurationTestMain 
{
  public static void main(String s[])
  {
	  
  
	ApplicationContext context = new ClassPathXmlApplicationContext("com/springinaction/springidol/spring-config-juggler.xml");
	Performer performer = (Performer) context.getBean("duke");
	performer.perform();
	Performer testingPerformer = (Performer) context.getBean("dhoni");
	testingPerformer.perform();
  }
}
