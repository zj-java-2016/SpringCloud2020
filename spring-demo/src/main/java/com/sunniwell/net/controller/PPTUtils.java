package com.sunniwell.net.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.xslf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * @author ：zj
 * @date ：Created in 2020/11/14 18:46
 * @description：
 * @version: $
 */
@Slf4j
public class PPTUtils {

    /**
     * ppt  2007
     *
     * @param sourceFile
     * @param pdfFile
     * @param fileUUID
     * @param savePath
     * @return
     */
    public static JSONArray pptToImage2007(String sourceFile, String pdfFile, String fileUUID, String savePath) {
        XMLSlideShow xmlSlideShow = null;
        try (FileInputStream in = new FileInputStream(sourceFile)) {
            xmlSlideShow = new XMLSlideShow(in);
            Dimension pageSize = xmlSlideShow.getPageSize();
            List<XSLFSlide> slides = xmlSlideShow.getSlides();
            JSONArray ja = new JSONArray();
            for (int i = 0; i < slides.size(); i++) {
                XSLFSlide slide = slides.get(i);
                List<XSLFShape> shapes = slide.getShapes();
                for (XSLFShape shape : shapes) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape sh = (XSLFTextShape) shape;
                        List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();
                        for (XSLFTextParagraph xslfTextParagraph : textParagraphs) {
                            List<XSLFTextRun> textRuns = xslfTextParagraph.getTextRuns();
                            for (XSLFTextRun xslfTextRun : textRuns) {
                                Double size = xslfTextRun.getFontSize();
                                String fontFamily = xslfTextRun.getFontFamily();
                                System.out.println("字体大小=================" + size);
                                System.out.println("字体样式=================" + fontFamily);
                                if ((size <= 0) || (size >= 26040)) {
                                    xslfTextRun.setFontSize(20.0);
                                }
                                // 设置字体样式为宋体
                                // String
                                // family=HSLFTextRunList.get(j).getFontFamily();
                                //xslfTextRun.setFontFamily("宋体");
                            }
                        }
                    }
                }
                //
                // 创建BufferedImage对象，图像的尺寸为原来的每页的尺寸*倍数times
                BufferedImage oneBufferedImage = new BufferedImage(pageSize.width * 4,
                        pageSize.height * 4, BufferedImage.TYPE_INT_RGB);

                Graphics2D oneGraphics2D = oneBufferedImage.createGraphics();
                // 设置转换后的图片背景色为白色
                //oneGraphics2D.setPaint(Color.white);
                oneGraphics2D.scale(4, 4);// 将图片放大times倍
                oneGraphics2D
                        .fill(new Rectangle2D.Float(0, 0, pageSize.width * 4, pageSize.height * 4));
                try {
                    slide.draw(oneGraphics2D);
                } catch (Exception e) {
                    log.error("【Utils:pptToImage2007】ppt渲染失败");
                }
                String pptToPngPath = getPptToPngPath(pdfFile, fileUUID, savePath, oneBufferedImage, i);
                ja.add(pptToPngPath);
            }
            return ja;
        } catch (Exception e) {
            log.error("【Utils:pptToImage2007】ppt 2007图片生成失败={}", e.getMessage(), e);
        } finally {
            if (xmlSlideShow != null) {
                try {
                    xmlSlideShow.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    /**
     * 获取ppt 转图片的地址
     *
     * @param pdfFile
     * @param fileUUID
     * @param savePath
     * @return
     * @throws Exception
     */
    private static String getPptToPngPath(String pdfFile, String fileUUID, String savePath,
                                          BufferedImage img, int i) throws Exception {
        // 图片的保存位置
        String downloadDir = pdfFile + "\\" + fileUUID +
                "\\" + (i + 1) + ".jpg";
        String imageDir = savePath + downloadDir;
        File file = FileUtil.touch(imageDir);
        try (FileOutputStream out = new FileOutputStream(file)) {
            ImageIO.write(img, ImgUtil.IMAGE_TYPE_JPG, out);
        }
        return downloadDir;
    }

    public static void main(String[] args) {
        /*List<String> result = converPPTtoImage(
                "C:\\Users\\Administrator\\Desktop\\ppt\\1.pptx", "C:\\Users\\Administrator\\Desktop\\ppt\\1\\", "jpg", 8);
        for(String s:result){
            System.out.println(s);
        }*/

        JSONArray objects = pptToImage2007(
                "C:\\Users\\Administrator\\Desktop\\ppt\\指纹.pptx", "1", "1",
                "C:\\Users\\Administrator\\Desktop\\ppt\\");
        // testPPT();
        // testPPTX();
    }
}
