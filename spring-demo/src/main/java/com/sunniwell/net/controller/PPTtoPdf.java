package com.sunniwell.net.controller;

/**
 * @author ：zj
 * @date ：Created in 2020/11/16 17:12
 * @description：
 * @version: $
 */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;

public class PPTtoPdf {
    // 32指定为ppt转pdf
    private static final Integer FILE_TO_PDF_OPERAND = 32;

    public static void main(String[] args) {
        PPTtoPdf iJacob = new PPTtoPdf();
        iJacob.toPdf("D:\\Temp\\patch\\asdf.pptx", "D:\\Temp\\patch\\a .pdf");
    }

    public void toPdf(String sourcePath, String targetPath) {
        ActiveXComponent app = null;
        Dispatch ppt = null;
        try {
            ComThread.InitSTA();
            app = new ActiveXComponent("PowerPoint.Application");
            Dispatch ppts = app.getProperty("Presentations").toDispatch();

            ppt = Dispatch.call(ppts, "Open", sourcePath, true, true, false).toDispatch();
            Dispatch.call(ppt, "SaveAs", targetPath, FILE_TO_PDF_OPERAND );
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

