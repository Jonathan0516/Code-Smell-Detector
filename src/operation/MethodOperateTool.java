package operation;

import operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MethodOperateTool {
    public String reduceMethodName(String name) {

        name = name.replaceAll("\\(.*\\)", "");
        name = name.replace("public", "");
        name = name.replace("private", "");
        name = name.replace("protected", "");
        name = name.replace("static", "");
        name = name.replace("class", "");
        name = name.replace("void", "");
        name = name.replace("String", "");
        name = name.replace("double", "");
        name = name.replace("int", "");
        name = name.replace("bool", "");
        name = name.replace("long", "");
        name = name.replace("float", "");
        name = name.replace("()", "");
        name = name.replace("{", "");
        name = name.replace("throws IOException", "");
        name = name.replace(" ", "");
        name = name.replace("	", "");
        name = name.toLowerCase();

        return name;
    }


    public static Map<String,String> storeMethods(String fileName) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        String line = "";
        String methodName = "";
        String value = "";
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null){
            int closeBrackets = 0;
            int openBrackets = 0;
            if((line.contains("public") || line.contains("private") || line.contains("protected"))
                    && line.contains(";") == false && line.contains("class") == false){
                methodName = line;
                MethodOperateTool ot = new MethodOperateTool();
                methodName = ot.reduceMethodName(methodName);
                openBrackets++;
                map.put(methodName, "");
                while((line = br.readLine()) != null) {
                    if(line.contains("{")){
                        openBrackets++;
                    }
                    else if(line.contains("}") && closeBrackets < openBrackets) {
                        closeBrackets++;
                        if(openBrackets == closeBrackets){
                            break;
                        }
                    }
                    value = map.get(methodName);
                    value = value + line.toLowerCase();
                    value = value.replace(" ", "");
                    value = value.replace("	", "");
                    map.put(methodName, value);
                }
            }
        }

        return map;
    }
    public Map<String,Integer> countLinesInMethods(String fileName) throws IOException {

        Map<String, Integer> map = new HashMap<String, Integer>();
        String line = "";
        String name = "";
        int smellyMethods = 0;
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null){
            int lines = 0;
            int closeBrackets = 0;
            int openBrackets = 0;
            if((line.contains("public") || line.contains("private") || line.contains("protected"))
                    && line.contains(";") == false && line.contains("class") == false){
                name = line;
                MethodOperateTool ot = new MethodOperateTool();
                name = ot.reduceMethodName(name);
                openBrackets++;
                while((line = br.readLine()) != null) {
                    lines++;
                    if(line.contains("{")){
                        openBrackets++;
                    }
                    else if(line.contains("}") && closeBrackets < openBrackets) {
                        closeBrackets++;
                        if(openBrackets == closeBrackets){
                            lines--;
                            map.put(name, lines);
                            break;
                        }
                    }
                }
            }
        }

        return map;
    }

}
