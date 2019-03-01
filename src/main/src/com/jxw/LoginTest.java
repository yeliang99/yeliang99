package com.jxw;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {
    @Test
    public  void test1(){
//    1、首先通过new IniSecurityManagerFactory并指定一个ini配置文件来创建一个SecurityManager工厂；
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//    2、接着获取SecurityManager并绑定到SecurityUtils，这是一个全局设置，设置一次即可；
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
//    3、通过SecurityUtils得到Subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1");
//    4、调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录；
//    5、如果身份验证失败请捕获AuthenticationException或其子类，
        try{subject.login(token);}catch (AuthenticationException e){
            System.out.print("登录失败");
        }
        Assert.assertEquals(true,subject.isAuthenticated());
            System.out.print("登录成功");
 //    6、最后可以调用subject.logout退出，其会自动委托给SecurityManager.logout方法退出
        subject.logout();
    }

}
