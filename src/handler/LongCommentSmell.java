package handler;

import operation.ClassOperateTool;
import operation.MethodOperateTool;
import operation.ReadTool;

import java.io.BufferedReader;
import java.io.IOException;

public class LongCommentSmell {


    public String countCommentsInClass(String fileName) throws IOException {

        String line = "";
        String name = "";
        int comments = 0;
        BufferedReader br = ReadTool.read(fileName);
        StringBuilder sb = new StringBuilder();
        ClassOperateTool cot = new ClassOperateTool();
        MethodOperateTool mot = new MethodOperateTool();

        while((line = br.readLine()) != null) {
            if(line.contains("class")){
                name = line;
                name = cot.reduceClassName(name);
            }
            else if(line.contains("//")) {
                comments++;
            }
        }
        if (comments < 15) {
//            sb.append("\n" + name + "has " + comments + " comments");
        }
        else {
            sb.append("\n" + name + "has " + comments + " comments (This class has 15 or more comments and is possibly smelly!)");
        }
        return sb.toString();
    }
}
