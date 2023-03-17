package com.example.codesmell.detector.unusedCheck;

import com.example.codesmell.detector.operation.MethodOperateTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MethodUnused {
    public String unusedMethods(String fileName, String fileName2) throws IOException {

        MethodOperateTool mot = new MethodOperateTool();
        Map<String,String> methods1 = mot.storeMethods(fileName);
        Map<String,String> methods2 = mot.storeMethods(fileName2);
        List<String> unusedMethods = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int numUnusedMethods = 0;
        int usedInFirstClass = 0;
        int usedInSecondClass = 0;

        Iterator It = methods1.entrySet().iterator();
        while (It.hasNext()) {
            usedInFirstClass = 0;
            usedInSecondClass = 0;
            Map.Entry pair = (Map.Entry)It.next();
            Iterator it2 = methods1.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry pair2 = (Map.Entry)it2.next();
                if(pair.getKey().equals(pair2.getKey())) {
                    String stringPair = (String) pair2.getValue();
                    if(stringPair.contains((String) pair.getKey())){
                        usedInFirstClass++;
                    }
                }
            }
            Iterator it3 = methods2.entrySet().iterator();
            while (it3.hasNext()) {
                Map.Entry pair2 = (Map.Entry)it3.next();
                if(pair.getKey().equals(pair2.getKey())) {
                    String stringPair = (String) pair2.getValue();
                    if(stringPair.contains((String) pair.getKey())){
                        usedInSecondClass++;
                    }
                }
            }
            if(usedInFirstClass == 0 && usedInSecondClass == 0){
                unusedMethods.add((String) pair.getKey());
                numUnusedMethods++;
            }
        }

        if(unusedMethods.size() > 0) {
            sb.append("unused methods: ");
            for (int i = 0; i < unusedMethods.size(); i++) {
                sb.append(unusedMethods.get(i));
            }
        }
        else {
            sb.append("No unused methods");
        }
        return sb.toString();
    }
}
