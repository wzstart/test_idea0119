package com.jk.consumer.portal.test;

import com.jk.consumer.portal.bean.Student;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 业精于勤荒于嬉,行成于思毁于随
 *
 * @Date 2019/1/16 21:55
 * @Created by wuzhuang
 */
public class Test01 {

    @Test
    public void test01() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }

    @Test
    public void test02() {

    }

    class StudnetExtend extends Student {

        //只要是抽象方法 一定要被子类重写
        @Override
        public void study() {

        }
    }

}

