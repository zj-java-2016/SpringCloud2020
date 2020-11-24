package com.sunniwell.net;

import cn.hutool.core.thread.ThreadUtil;
import org.redisson.RedissonCountDownLatch;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author ：zj
 * @date ：Created in 2020/11/5 8:55
 * @description：
 * @version: $
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public A() {
        System.out.println("----A created success");
    }

    public static synchronized void getA() {
        System.out.println(Thread.currentThread().getName() + "=========进入getA");
        ThreadUtil.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "=========进入getA===执行完成");
    }

    public static synchronized void getC() {
        System.out.println(Thread.currentThread().getName() + "=========进入getC");
    }

    String abc = "abcdefg";
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    volatile int num = 0;//0 : a线程 1：b线程 2：c线程
    public void printFrequency(int frequency, int state, Condition current, Condition next) {
        lock.lock();
        try {
            int len = abc.length() / 2;
            for (int j = 0; j <= len; j++) {
                while (num % 2 != state){
                    current.await();
                }
                if(num % 2 == 0){
                    System.out.println("线程：" + Thread.currentThread().getName() + "，打印" +  abc.charAt(j));
                }else {
                    System.out.println("线程：" + Thread.currentThread().getName() + "，打印" + abc.charAt(abc.length() -j -1));
                }
                num++;
                next.signal();
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 3, 3000, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 50; i++){
                 final int j = i;
                Future<Integer> submit = threadPoolExecutor.submit(() -> {
                    //System.out.println("执行的线程=" + Thread.currentThread().getName() + ", 打印数值=" + j);
                    return j;
                });
                boolean cancelled = submit.isCancelled();
                System.out.println(cancelled);
                if(submit.isDone()){
                    Integer integer = submit.get();
                }
            }
        }catch (RejectedExecutionException r){
            System.out.println("自定义异常执行失败线程=" + Thread.currentThread().getName());
        } catch (Exception e){
            e.getMessage();
            System.out.println("执行失败线程=" + Thread.currentThread().getName());
        }finally {
            System.out.println("当前线程大小=" +threadPoolExecutor.getMaximumPoolSize());
        }

        /*A a = new A();
        new Thread(() ->{
            a.printFrequency(5, 0, a.condition1, a.condition2);
        }, "AA").start();
        new Thread(() ->{
            a.printFrequency(10, 1, a.condition2, a.condition1);
        }, "BB").start();*/

        /*int h = 0;
        while (h < 10) {
            List<Integer> list = new CopyOnWriteArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            List<Integer> list3 = new ArrayList<>();
            List<Integer> list4 = new ArrayList<>();

            for (int i = 0; i < 100000; i++) {
                list2.add(i);
            }*/

            /*long timeMillis2 = System.currentTimeMillis();
            for (int j = 0; j < list2.size(); j++) {
                ThreadUtil.sleep(1);
                list3.add(j);
            }
            System.out.println(System.currentTimeMillis() - timeMillis2);
            list3.clear();
            long timeMillis1 = System.currentTimeMillis();
            list2.stream().forEach(l -> {
                ThreadUtil.sleep(1);
                list3.add(l);
            });
            System.out.println(System.currentTimeMillis() - timeMillis1);
            list4.clear();*/
            /*long timeMillis = System.currentTimeMillis();
            list2.parallelStream().forEach(l -> {
                list.add(l);
            });
            System.out.println(System.currentTimeMillis() - timeMillis);
            System.out.println(list.size() == list2.size());*/
    }
        /*ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        A a = new A();
        new Thread(() -> {
            System.out.println("线程a进入");
            getA();
            System.out.println("线程a退出");
        }, "a").start();

        new Thread(() -> {
            System.out.println("线程b进入");
            getC();
            System.out.println("线程b退出");
        }, "b").start();*/

        /*String s = new String("1") + new String("23");
        String intern = s.intern();
        String s1 = "123";
        System.out.println(s == intern);*/
    //System.out.println(s.intern() == s1);

        /*String str2 = new String("str")+new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str2==str1);*/

        /*String str2 = new String("str")+new String("01");
        String str1 = "str01";
        str2.intern();
        System.out.println(str2==str1);*/

        /*String s = "58" + "tongcheng";

        StringBuffer s3 = new StringBuffer("58").append("tongcheng");
        String s4 = s3.toString();
        String s5 = s4.intern();
        System.out.println(s5 == s4);

        StringBuffer tongcheng = new StringBuffer().append("58").append("tongcheng");
        String s7 = tongcheng.toString();
        String intern2 = s7.intern();
        System.out.println(intern2 == s7);

        System.out.println(s4 == s7);*/
    //}
}
