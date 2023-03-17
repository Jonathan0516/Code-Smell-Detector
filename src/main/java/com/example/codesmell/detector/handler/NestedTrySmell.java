package com.example.codesmell.detector.handler;

import com.example.codesmell.detector.operation.ReadTool;

import java.io.*;
import java.util.Stack;

public class NestedTrySmell {
    static long lastposition = 00;
    static int line = 0;
    String str;
    public String nestedTry(String fileName) throws IOException {
        String s;
        int classNo=0;
        BufferedReader br = ReadTool.read(fileName);
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            line++;
            if (s.matches("(.*)try(.*)")) {
//            	System.out.println(line);
                File file = new File(fileName);
                FileInputStream fis = new FileInputStream(file);
                int numberOfLine = 0;
                int i = 0;
                while(numberOfLine < line){
                    if(fis.read() == '\n'){
                        numberOfLine++;
                    }
                    i++;
                }
                lastposition = i;
                sb.append(findingNestedTry(file,lastposition, line));
            }
        }
        if(sb.toString()!=null){
            str = sb.toString();
        }else{
            str = "No found Smelle";
        }
        return str;
    }

    public String findingNestedTry(File file, long lastpos, int line){
//		System.out.println(line);
//		System.out.println(lastpos);
        int lineCount = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();

        Stack st = new Stack();
        st.push("Asd");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            fis.skip(lastpos-2);
            char current;
            do{
                current = (char) fis.read();
                if(current == '{') {
                    st.push(current);
                    count++;
                }
                if(current == '}') {
                    st.pop();
                    count--;
                    if(count == 0){
                        st.pop();
                    }
                }
                if((current == '\n') && (st.peek() != "Asd")) {
                    lineCount++;
                }
            }while(!st.empty());
            for (int i = 0; i < lineCount + line; i++) {

                String s =  br.readLine();
                if((i+1) > line){
                    if(s.matches("(.*)try(.*)")){
                        sb.append("\nNested Try Found at: "+ (i+1) + " line\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
