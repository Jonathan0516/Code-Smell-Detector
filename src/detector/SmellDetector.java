package detector;

import handler.*;
import unusedCheck.FieldUnused;
import unusedCheck.MethodUnused;
import unusedCheck.ParameterUnused;

import javax.swing.*;
import java.io.IOException;

public class SmellDetector {

    static LargeClassSmell lc = new LargeClassSmell();
    static LongCommentSmell lcs = new LongCommentSmell();
    static StringBuilder sb = new StringBuilder();
    static LongMethodSmell lms = new LongMethodSmell();
    static LongParameterSmell lps = new LongParameterSmell();
    static DuplicateCodeSmell dcs = new DuplicateCodeSmell();
    static DataClassSmell das = new DataClassSmell();
    static FieldUnused fu = new FieldUnused();
    static MethodUnused mu = new MethodUnused();
    static ParameterUnused pu = new ParameterUnused();

    public static void Detector(String filePath) {
        try {
//            sb.append(lc.largeClass(filePath));
////            sb.append(lcs.countCommentsInClass(filePath));
 sb.append(lms.longMethod(filePath));
////            sb.append(lps.longParameter(filePath));
////            sb.append(dcs.countDuplicateMethods(filePath));
//            sb.append(das.dataClass(filePath));
//            sb.append(fu.unusedFields(filePath));
//            sb.append(pu.unusedParameters(filePath));
//            System.out.println(lc.largeClass(filePath));
//            System.out.println();
//            System.out.println(lcs.countCommentsInClass(filePath));
//            System.out.println();
//            System.out.println(lms.longMethod(filePath));
//            System.out.println();
//            System.out.println(lps.longParameter(filePath));
//            System.out.println();
//            System.out.println(dcs.countDuplicateMethods(filePath));
//            System.out.println();
//            System.out.println(das.dataClass(filePath));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String res = sb.toString();
        if (res == "") {
            JOptionPane.showMessageDialog(null, "No code smell detected");
        } else {
            JOptionPane.showMessageDialog(null, res);
        }
    }
}
