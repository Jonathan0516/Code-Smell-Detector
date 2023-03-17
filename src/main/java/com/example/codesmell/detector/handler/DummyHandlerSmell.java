package com.example.codesmell.detector.handler;

import com.example.codesmell.detector.operation.ClassOperateTool;
import com.example.codesmell.detector.operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;

public class DummyHandlerSmell {


    public String countLine(String fileName) throws IOException {

        String className;
        String line = "";
        BufferedReader br = ReadTool.read(fileName);
        ClassOperateTool cot = new ClassOperateTool();


        while((line = br.readLine()) != null){
            if(line.contains("class")){
                className = line;
                className = cot.reduceClassName(className);
                break;
            }
        }

        int lines = cot.countLinesInClass(fileName);

        StringBuilder sb = new StringBuilder();

        if (lines == 2) {
            sb.append("Dummy Handler found");
            sb.append("\r\n");
        }

        return sb.toString();
    }
}