package handler;

import operation.ClassOperateTool;
import operation.MethodOperateTool;
import operation.ReadTool;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class DataClassSmell {
    public String dataClass(String fileName) throws IOException {
        MethodOperateTool mot = new MethodOperateTool();
        ClassOperateTool cot = new ClassOperateTool();
        Map<String,Integer> linesMap = mot.countLinesInMethods(fileName);
        Map<String,String> bodyMap = mot.storeMethods(fileName);
        String className = "";
        String line = "";
        StringBuilder sb = new StringBuilder();
        int numMethods = cot.countMethodsInClass(fileName);
        int setMethods = 0;
        int getMethods = 0;
        int totalMethods = 0;
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null){
            if(line.contains("class")){
                className = line;
                className = cot.reduceClassName(className);
                break;
            }
        }

        Iterator it = linesMap.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue().equals(1)){
                Iterator it2 = bodyMap.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry pair2 = (Map.Entry)it2.next();
                    if(pair.getKey().equals(pair2.getKey())) {
                        String stringPair = (String) pair2.getValue();
                        if(stringPair.contains("this")){
                            setMethods++;
                        }
                        else if(stringPair.contains("return")){
                            getMethods++;
                        }
                    }
                }
            }
        }

        totalMethods = setMethods + getMethods;

        sb.append("\n set Methods: " + setMethods);
        sb.append("Get Methods: " + getMethods);

        if(totalMethods == numMethods){
            sb.append(className + "has only set and/or get methods and is possibly smelly!");
        }
        return sb.toString();
    }
}
