package unusedCheck;
import operation.ReadTool;

import operation.MethodOperateTool;
import operation.ParameterOperateTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParameterUnused {
    public void unusedParameters(String fileName) throws IOException {

        ParameterOperateTool pot = new ParameterOperateTool();
        MethodOperateTool mot = new MethodOperateTool();
        //get all parameters
        Map<String, List<String>> parametersMap = pot.getParameters(fileName);
        //get all methods
        Map<String, String> methodsMap = mot.storeMethods(fileName);
        List<String> parameters = new ArrayList();
        List<String> unusedParameters = new ArrayList();
        BufferedReader br = ReadTool.read(fileName);
        int parameterUsed = 0;

        Iterator it = parametersMap.entrySet().iterator();
        while (it.hasNext()) {
            parameterUsed = 0;
            Map.Entry pair = (Map.Entry)it.next();
            parameters = (List<String>) pair.getValue();
            Iterator it2 = methodsMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry pair2 = (Map.Entry)it2.next();
                String stringPair = (String) pair2.getValue();
                if(pair.getKey().equals(pair2.getKey())) {
                    for (int i = 0; i < parameters.size(); i++) {
                        if(stringPair.contains(parameters.get(i))) {
                            parameterUsed++;
                        }
                    }
                }
            }
            if(parameterUsed < parameters.size()) {
                unusedParameters.add((String) pair.getKey());
            }
        }

        for (int i = 0; i < unusedParameters.size(); i++) {
            System.out.println("\n" + unusedParameters.get(i) + " has unused parameters!");
        }
    }
}
