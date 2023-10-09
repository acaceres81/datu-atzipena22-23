package dambi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;


public class JsonZuhaitzaSortuBi {
      public static void main(String[] args) throws FileNotFoundException {
         // Create a JSON object hierarchy using the Json.createObjectBuilder() method
        JsonObject model = Json.createObjectBuilder()
        .add("menu",Json.createObjectBuilder()
            .add("id", "file")
            .add("value", "file")
            .add("popup", Json.createObjectBuilder()
                .add("menuitem", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("value", "new")
                    .add("onclick", "CreateNewDoc()"))
                .add(Json.createObjectBuilder()
                    .add("value", "open")
                    .add("onclick", "OpenDoc"))
                .add(Json.createObjectBuilder()
                    .add("value", "new")
                    .add("onclick", "CreateNewDoc()"))))) 
        .build();
        // Print the JSON object to the console
        System.out.println(model);
       // Write the JSON object to a file named "Irteera.json"
        try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("data/Irteera.json"))) {
        
            jsonWriter.writeObject(model); // model da JsonObjectaren izena
         }

    }
}
    

