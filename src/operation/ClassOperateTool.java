package operation;

import operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;

public class ClassOperateTool {
    public String reduceClassName(String name) {

        name = name.replace("{", "");
        name = name.replace("	", "");

        return name;
    }
    public int countLinesInClass(String fileName) throws IOException {

        int lines = 0;
        String name = "";
        String line = "";
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null){
            if(line.contains("class")){
                name = line;
                name = reduceClassName(name);
                while((line = br.readLine()) != null){
                    lines++;
                }
                lines--;
            }
        }
        return lines;
    }

    public int countFieldsInClass(String fileName) throws IOException {

        int fields = 0;
        String name;
        String line = "";
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null){
            if(line.contains("class")){
                name = line;
                name = reduceClassName(name);
            }
            else if(line.contains("{")  && line.contains("class") == false || line.contains("}")){
                break;
            }
            else if(line.contains("int") || line.contains("String") || line.contains("bool")
                    || line.contains("long") || line.contains("long") || line.contains("float")
                    || line.contains("double")) {
                fields++;
            }
        }
        return fields;
    }

    //counts number of methods in a class
    public int countMethodsInClass(String fileName) throws IOException {

        String line = "";
        String name;
        int methods = 0;
        BufferedReader br = ReadTool.read(fileName);

        while((line = br.readLine()) != null) {
            if(line.contains("class")){
                name = line;
                MethodOperateTool ot = new MethodOperateTool();
                name = reduceClassName(name);
            }
            else if((line.contains("public") || line.contains("private") || line.contains("protected"))
                    && line.contains(";") == false && line.contains("class") == false) {
                methods++;
            }
        }
        return methods;
    }

}
