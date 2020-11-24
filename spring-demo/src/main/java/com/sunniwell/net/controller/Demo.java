package com.sunniwell.net.controller;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Demo {

    public void ppt2img(String srcFilePath, String imgFilePath) throws Exception {
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

    public static void main(String[] args) throws Exception {
        new Demo().ppt2img("C:\\Users\\Administrator\\Desktop\\ppt\\1.pptx", "C:\\Users\\Administrator\\Desktop\\ppt\\aaa.png");
    }
    
}