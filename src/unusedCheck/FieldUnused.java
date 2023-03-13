package unusedCheck;

import operation.FieldOperateTool;
import operation.MethodOperateTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FieldUnused {
    public String unusedFields(String fileName) throws IOException {
        MethodOperateTool mot = new MethodOperateTool();
        FieldOperateTool fot = new FieldOperateTool();
        List<String> fields = fot.getFields(fileName);
        Map<String,String> methods = mot.storeMethods(fileName);
        List<String> unusedFields = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int NumUnusedFields = 0;
        int used = 0;

        for (int i = 0; i < fields.size(); i++) {
            used = 0;
            Iterator it = methods.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(((String) pair.getValue()).contains(fields.get(i))){
                    used++;
                }
            }
            if(used == 0) {
                unusedFields.add(fields.get(i));
                NumUnusedFields++;
            }
        }

        if(unusedFields.size() > 0) {
            sb.append("\n unused fields: ");
            for (int i = 0; i < unusedFields.size(); i++) {
                sb.append(unusedFields.get(i));
            }
        }
        else {
            sb.append("\n No unused fields");
        }
        return sb.toString();
    }
}
