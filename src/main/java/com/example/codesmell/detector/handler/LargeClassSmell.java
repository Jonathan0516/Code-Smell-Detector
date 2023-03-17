package com.example.codesmell.detector.handler;

import com.example.codesmell.detector.operation.ClassOperateTool;
import com.example.codesmell.detector.operation.MethodOperateTool;
import com.example.codesmell.detector.operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;

public class LargeClassSmell {


    public String largeClass(String fileName) throws IOException {

        String className = "";
        String line = "";
        BufferedReader br = ReadTool.read(fileName);
        MethodOperateTool ot = new MethodOperateTool();
        ClassOperateTool cot = new ClassOperateTool();


        while((line = br.readLine()) != null){
            if(line.contains("class")){
                className = line;
                className = cot.reduceClassName(className);
                break;
            }
        }

        int lines = cot.countLinesInClass(fileName);
        int methods = cot.countMethodsInClass(fileName);
        int fields = cot.countFieldsInClass(fileName);

        StringBuilder sb = new StringBuilder();

        if (methods >= 15) {
            sb.append(className + "has " + methods + " methods");
            sb.append("\r\n");
            sb.append("This class has 15 or more methods and is possibly smelly!");
            sb.append("\r\n");
            sb.append("\r\n");
        }


        if (fields >= 10) {
            sb.append(className + "has " + fields + " fields");
            sb.append("\r\n");
            sb.append("This class has 10 or more fields and is possibly smelly!");
            sb.append("\r\n");
            sb.append("\r\n");
        }

//        System.out.print("\n" + className + "has " + lines + " LoC");

        if (lines >= 200) {
            sb.append(className + "has " + lines + " LoC");
            sb.append("\r\n");
            sb.append("This class has 200 or more loC and is possibly smelly!");
            sb.append("\r\n");
        }

        return sb.toString();
    }
}