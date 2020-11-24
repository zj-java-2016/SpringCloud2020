package com.sunniwell.net;

import cn.hutool.core.bean.BeanUtil;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zj
 * @date ：Created in 2020/11/5 8:55
 * @description：
 * @version: $
 */
@SpringBootApplication
public class SpringMain {

    public static void main(String[] args) {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);

        System.out.println(o);
        System.out.println(weakReference);
        o = null;
        System.gc();
        System.out.println("===========");
        System.out.println(o);
        System.out.println(weakReference.get());
    }

    private static void springTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(IntUtils.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++){
            Integer integer = map.get(target - nums[i]);
            if(integer == null){
                map.put(nums[i], i);
            }else {
                int[] is = {integer, i};
                return is;
            }
        }
        return null;
    }
}
