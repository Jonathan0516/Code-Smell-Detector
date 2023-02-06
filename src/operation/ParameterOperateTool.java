package operation;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterOperateTool {
    public Map<String, List<String>> getParameters(String fileName) throws IOException {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        String line = "";
        String name = "";
        String parameters = "";
        BufferedReader br = ReadTool.read(fileName);
        MethodOperateTool mot = new MethodOperateTool();
        while((line = br.readLine()) != null) {
            List<String> pl = new ArrayList();
            if((line.contains("public") || line.contains("private") || line.contains("protected"))
                    && !line.contains(";") && !line.contains("class")) {
                name = line;
                name = mot.reduceMethodName(name);
                if(!line.contains("()")){
                    parameters = line.substring(line.indexOf("(")+1,line.indexOf(")"));
                    parameters = reduceParameters(parameters);
                    parameters = parameters.toLowerCase();
                    parameters = parameters.replace(" ", "");
                    for(String parameter : parameters.split(",")) {
                        pl.add(parameter);
                    }
                    map.put(name, pl);
                }
                else {
                    map.put(name, pl);
                }
            }
        }
        return map;
    }

    public String reduceParameters(String name) {

        name = name.replace("String", "");
        name = name.replace("double", "");
        name = name.replace("int", "");
        name = name.replace("boolean", "");
        name = name.replace("bool", "");
        name = name.replace("long", "");
        name = name.replace("float", "");

        return name;
    }
}
