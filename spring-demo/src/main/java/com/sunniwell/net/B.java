package com.sunniwell.net;

import com.aspose.slides.ISlide;
import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author ：zj
 * @date ：Created in 2020/11/5 8:56
 * @description：
 * @version: $
 */
public class B {

    private A a;

    public A getA(){
        return a;
    }

    public void setA(A a){
        this.a = a;
    }

    public B(){
        System.out.println("----B created success");
    }

    private static InputStream license;
    public static boolean getLicense() {
        boolean result = false;
        license = B.class.getClassLoader().getResourceAsStream("license.xml");
        if (license != null) {
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        }
        return result;
    }

    /**
     * 转Image
     *
     * @return
     */
    public static String convertImage() {
        // 验证License
        if (!getLicense()) {
            return "验证License失败";
        }
        String fileName = "C:\\Users\\Administrator\\Desktop\\ppt\\1.pptx";
        File file = new File(fileName);
        if (!file.exists()) {
            return "转换文件不存在";
        }
        String filePath = file.getParent()+File.separator;
        String dest = filePath + getFileNameNoEx(file.getName())+"_JPG";
        File destPath = new File(dest);
        if (!destPath.exists()) {
            destPath.mkdir();
        }
        try {
            FileInputStream fileInput = new FileInputStream(fileName);
            Presentation pres = new Presentation(fileInput);
            int i;
            for (i = 0; i < pres.getSlides().size(); i++) {
                ISlide slide = pres.getSlides().get_Item(i);
                int height = (int)(pres.getSlideSize().getSize().getHeight()-150);
                int width = (int)(pres.getSlideSize().getSize().getWidth()-150);
                BufferedImage image = slide.getThumbnail(new java.awt.Dimension(width, height));
                //每一页输出一张图片
                File outImage = new File(dest+File.separator + (i+1) + ".JPG");
                ImageIO.write(image, "JPG", outImage);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "转换成功";
    }
    /**
     * 获取文件名，去除扩展名的
     *
     * @param filename
     * @return
     */
    private static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static void main(String[] args) {
        convertImage();
    }


}
