package com.example.codesmell.detector.handler;

import com.example.codesmell.detector.operation.MethodOperateTool;
import com.example.codesmell.detector.operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LongParameterSmell {

    public Map<String, Integer> countParameters(String fileName) throws IOException {

        Map<String, Integer> map = new HashMap<String, Integer>();
        String line = "";
        String name = "";
        int smellyMethods = 0;
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null) {
            int wordCount = 0;
            if((line.contains("public") || line.contains("private") || line.contains("protected"))
                    && line.contains(";") == false && line.contains("class") == false) {
                name = line;
                MethodOperateTool ot = new MethodOperateTool();
                name = ot.reduceMethodName(name);
                for(int i = 0; i < line.length(); ++i) {
                    char currentChar = line.charAt(i);
                    if(line.contains("()")) {
                        break;
                    }
                    else if(currentChar == ',' || currentChar == ')') {
                        wordCount += 1;
                    }
                }
                map.put(name, wordCount);
            }
        }


        return map;
    }
    public String longParameter(String fileName) throws IOException {
        int smellyMethods = 0;
        Map<String, Integer> map = countParameters(fileName);
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        sb.append("\nList of methods and number of parameters: \n");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if((int) pair.getValue() >= 4) {
                sb.append(pair.getKey() + " = " + pair.getValue()
                        + " (This method has 4 or more parameters and is possibly smelly!)");
                smellyMethods++;
            }
            else {
                sb.append(pair.getKey() + " = " + pair.getValue());
            }
        }
        sb.append("Number of smelly methods = " + smellyMethods);
        return sb.toString();
    }
}
