package com.ddup.spring.test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PDFWaterMarkTest {
    @Test
    public void pdfTest() throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:/documents/test/abc.pdf"));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        setWaterMark(bos, "D:/download/BaiduNetdiskDownload/SpringMVC面试专题及答案.pdf", dateFormat.format(new Date()));
    }

    private void setWaterMark(BufferedOutputStream bos, String input, String waterMarkName) throws Exception {
        PdfReader pdfReader = new PdfReader(input);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, bos);
        int total = pdfReader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            content = pdfStamper.getUnderContent(i);
            gs.setFillOpacity(0.2f);
            content.beginText();

            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(font, 30);
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, "我爱梁文馨，我爱梁文馨!", 300, 350, 50);

            content.setColorFill(BaseColor.BLACK);
            content.setFontAndSize(font, 8);
            content.showTextAligned(Element.ALIGN_CENTER, "下载时间：" + waterMarkName + "", 300, 10, 0);

            content.endText();
        }
        pdfStamper.close();
        pdfReader.close();
    }
}
