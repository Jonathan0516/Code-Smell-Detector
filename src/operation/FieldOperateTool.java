package operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FieldOperateTool {
    public List<String> getFields(String fileName) throws IOException {

        String className = "";
        String fieldName = "";
        String line = "";
        List<String> fields = new ArrayList<String>();
        BufferedReader br = ReadTool.read(fileName);
        ClassOperateTool cot = new ClassOperateTool();

        while((line = br.readLine()) != null){
            if(line.contains("class")){
                className = line;
                className = cot.reduceClassName(className);
            }
            else if(line.contains("{")  && line.contains("class") == false || line.contains("}")){
                break;
            }
            else if(line.contains("int") || line.contains("String") || line.contains("bool")
                    || line.contains("long") || line.contains("long") || line.contains("float")
                    || line.contains("double")) {
                fieldName = line;
                fieldName = reduceFieldName(fieldName);
                fieldName = fieldName.toLowerCase();
                fields.add(fieldName);
            }
        }

        return fields;
    }

    public String reduceFieldName(String name){

        name = name.replace("=", "");
        name = name.replace("0", "");
        name = name.replace("String", "");
        name = name.replace("double", "");
        name = name.replace("int", "");
        name = name.replace("boolean", "");
        name = name.replace("bool", "");
        name = name.replace("long", "");
        name = name.replace("float", "");
        name = name.replace("public", "");
        name = name.replace("private", "");
        name = name.replace("protected", "");
        name = name.replace(";", "");
        name = name.replace(" ", "");
        name = name.replace("	", "");

        return name;
    }
}
