package com.sunniwell.net.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

/**
 * @author ：zj
 * @date ：Created in 2020/11/6 18:00
 * @description：
 * @version: $
 */
@RestController
public class TestController {

    @GetMapping
    public String getTest(){
        try {
            String srcFilePath = "C:\\Users\\Administrator\\Desktop\\ppt\\1.pptx";
            String imgFilePath = "C:\\Users\\Administrator\\Desktop\\ppt\\aaa";
            ppt2img(srcFilePath, imgFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }

    @GetMapping("/linux")
    public String getTestLinux(){
        try {
            String srcFilePath = "/home/zhoujin/ppt/1.pptx";
            String imgFilePath = "/home/zhoujin/ppt/1";
            ppt2img(srcFilePath, imgFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }

    public static void ppt2img(String srcFilePath, String imgFilePath) throws Exception {
        ActiveXComponent app = null;
        Dispatch ppt = null;
        try {
            ComThread.InitSTA();
            app = new ActiveXComponent("PowerPoint.Application");
            Dispatch ppts = app.getProperty("Presentations").toDispatch();
            ppt = Dispatch.call(ppts, "Open", srcFilePath, false, true).toDispatch();
            Dispatch.call(ppt, "SaveAs", imgFilePath, 17);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ppt != null) {
                Dispatch.call(ppt, "Close");
            }
            if (app != null) {
                app.invoke("Quit");
            }
            ComThread.Release();
        }
    }
}
