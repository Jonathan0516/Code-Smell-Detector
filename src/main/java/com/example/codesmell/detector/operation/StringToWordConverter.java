package com.example.codesmell.detector.operation;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StringToWordConverter {
    
    public static void Converter() throws IOException {
        // 生成字符串内容
        String content = "这是一段文本内容";
        String savePath = "/Program Files/";
        String fileName = "output.docx";
        // 创建Word文档
        File wordDoc = new File(savePath + fileName);
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);

        // 将文档导出到文件
        FileOutputStream fos = new FileOutputStream(wordDoc);
        document.write(fos);
        fos.close();
        document.close();

        System.out.println("Word文档已成功生成！");
    }
}
