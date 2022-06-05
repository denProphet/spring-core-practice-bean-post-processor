package com.company;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TerminatorQuoterTest {
    @Test
    public void testSayQuote() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean("quoter",Quoter.class).sayQuote();
        context.getBean("quoter",Quoter.class).say2();
    }
}