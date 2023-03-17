package testClass;

import java.io.File;

public class NestedTrySmellTest {
    public void test(File file, long lastpos, int line){
        try {
            System.out.println(1);
            try{
                System.out.println(1);
            }catch (Exception e){

            }
        } catch (Exception e) {

        }
    }
}
