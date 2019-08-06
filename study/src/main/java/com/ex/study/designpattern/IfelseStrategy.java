package com.ex.study.designpattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 测试模式封装Ifelse
*/
public class IfelseStrategy {
    @Autowired
    private static InnerCommandContext context;

    public static void main(String[] args) throws ClassNotFoundException {
        InnerCommand command = context.getInstance();
        command.process("all");
    }
}

interface InnerCommand{
    /**
     * 执行
     * @param msg
     */
    void process(String msg);
}

@Service
class PrintCommand implements InnerCommand{
    @Override
    public void process(String msg) {
        System.out.println("print:"+msg);
    }
}

@Service
class CloseCommand implements InnerCommand{
    @Override
    public void process(String msg) {
        System.out.println("close:"+msg);
    }
}

@Component
class InnerCommandContext{
    /**
     * 获取执行器实例
     * @return
     * @throws ClassNotFoundException
     */
    public InnerCommand getInstance() throws ClassNotFoundException {
        return  (InnerCommand) SpringBeanFactory.getBean(Class.forName("com.ex.study.designpattern.PrintCommand"));
    }
}

@Component
final class SpringBeanFactory implements ApplicationContextAware{
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> c){
        return context.getBean(c);
    }


    public static <T> T getBean(String name,Class<T> clazz){
        return context.getBean(name,clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}


