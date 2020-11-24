package com.sunniwell.net;

import com.sun.xml.internal.ws.resources.SenderMessages;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：zj
 * @date ：Created in 2020/11/14 9:11
 * @description：
 * @version: $
 */
@Component
public class MyRejectPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        boolean offer = executor.getQueue().offer(r);
        if(!offer){
            synchronized (this){
                boolean offer1 = executor.getQueue().offer(r);
                if (!offer1) {
                    System.out.println("=====进行扩容");
                    executor.setMaximumPoolSize(500);
                    executor.getQueue().offer(r);
                }
            }
        }
    }
}
