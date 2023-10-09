package dambi;

import javax.json.JsonValue;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonString;


public class JsonZuhaitzaSortuBi {
    public static void navigateTree(JsonValue tree, String key) {
        if (key != null)
           System.out.print("Key " + key + ": ");
        switch(tree.getValueType()) {
           case OBJECT:
              System.out.println("OBJECT");
              JsonObject object = (JsonObject) tree;
              for (String name : object.keySet())
                 navigateTree(object.get(name), name);
              break;
           case ARRAY:
              System.out.println("ARRAY");
              JsonArray array = (JsonArray) tree;
              for (JsonValue val : array)
                 navigateTree(val, null);
              break;
           case STRING:
              JsonString st = (JsonString) tree;
              System.out.println("STRING " + st.getString());
              break;
           case NUMBER:
              JsonNumber num = (JsonNumber) tree;
              System.out.println("NUMBER " + num.toString());
              break;
           case TRUE:
           case FALSE:
           case NULL:
              System.out.println(tree.getValueType().toString());
              break;
        }
     }
    
}
