package handler;

import operation.MethodOperateTool;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LongMethodSmell {


    public String longMethod(String fileName) throws IOException {
        int smellyMethods = 0;
        MethodOperateTool ot = new MethodOperateTool();
        Map<String, Integer> map = ot.countLinesInMethods(fileName);
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        sb.append("\nList of methods and LoC: \n");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if((int) pair.getValue() >= 10) {
                sb.append(pair.getKey() + " = " + pair.getValue()
                        + " (This method has 10 or more loC and is possibly smelly!)");
                smellyMethods++;
            }
            else{
                sb.append(pair.getKey() + " = " + pair.getValue());
            }
        }
        sb.append("\nNumber of smelly methods = " + smellyMethods);
        return sb.toString();
    }
}
