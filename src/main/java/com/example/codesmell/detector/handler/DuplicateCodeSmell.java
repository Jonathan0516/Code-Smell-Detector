package com.example.codesmell.detector.handler;

import com.example.codesmell.detector.operation.ClassOperateTool;
import com.example.codesmell.detector.operation.MethodOperateTool;
import com.example.codesmell.detector.operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class DuplicateCodeSmell {
    public String countDuplicateMethods(String fileName) throws IOException {

        String line = "";
        String methodName = "";
        String className = "";
        String value = "";
        ClassOperateTool cot = new ClassOperateTool();
        MethodOperateTool mot = new MethodOperateTool();
        int duplicatedMethods = 0;
        BufferedReader br = ReadTool.read(fileName);
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = mot.storeMethods(fileName);

        while((line = br.readLine()) != null){
            if(line.contains("class")){
                className = line;
                className = cot.reduceClassName(className);
                break;
            }
        }

        Iterator it1 = map.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair1 = (Map.Entry)it1.next();
            String stringPair1 = (String) pair1.getValue();
            Iterator it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry pair2 = (Map.Entry)it2.next();
                String stringPair2 = (String) pair2.getValue();
                if(stringPair1.equals(stringPair2) == true && pair1.getKey() != pair2.getKey()){
                    duplicatedMethods++;
                    sb.append("\n" + pair1.getKey() + " and " + pair2.getKey() + " are duplicate methods!");
                }
            }
        }
        if(duplicatedMethods > 0) {
            sb.append("\n" + className + "has " + duplicatedMethods + " duplicated methods and is possibly smelly!");
        }
        else {
            sb.append("\n" + className + "has " + duplicatedMethods + " duplicated methods");
        }
        return sb.toString();
    }
}
