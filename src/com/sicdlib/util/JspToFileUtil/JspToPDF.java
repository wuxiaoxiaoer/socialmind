package com.sicdlib.util.JspToFileUtil;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by DeMH on 2017/11/21.
 */
public class JspToPDF {

    public static void main(String[] args) {
//        htmlCodeComeFromFile("C:\\Users\\DeMH\\Desktop\\yelp_db.txt", "C:\\Users\\DeMH\\Desktop\\1.pdf");
        htmlCodeComeString("C:\\Users\\DeMH\\Desktop\\yelp_db.txt", "C:\\Users\\DeMH\\Desktop\\1.pdf");
    }

    public static void htmlCodeComeFromFile(String filePath, String pdfPath) {
        Document doc = new Document();
        StyleSheet st = new StyleSheet();
        st.loadStyle("body", "leading", "16,0");
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));

        doc.open();
        ArrayList p = HTMLWorker.parseToList(new FileReader(filePath), st);
        for(int k = 0; k < p.size(); ++k) {
            doc.add((Element) p.get(k));
        }
        doc.close();
        System.out.println("文档创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void htmlCodeComeString(String htmlCode, String pdfPath) {
        Document doc = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
            doc.open();
            // 解决中文问题
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            Paragraph t = new Paragraph(htmlCode, FontChinese);
            doc.add(t);
            doc.close();
            System.out.println("文档创建成功");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
