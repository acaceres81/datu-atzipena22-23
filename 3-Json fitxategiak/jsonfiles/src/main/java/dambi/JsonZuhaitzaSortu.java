package dambi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class JsonZuhaitzaSortu {
        public static void main(String[] args) throws FileNotFoundException {

                JsonObject model = Json.createObjectBuilder()
                                .add("firstName", "Duke")
                                .add("lastName", "Java")
                                .add("age", 18)
                                .add("streetAddress", "100 Internet Dr")
                                .add("city", "JavaTown")
                                .add("state", "JA")
                                .add("postalCode", "12345")
                                .add("phoneNumbers", Json.createArrayBuilder()
                                                .add(Json.createObjectBuilder()
                                                                .add("type", "mobile")
                                                                .add("number", "111-111-1111"))
                                                .add(Json.createObjectBuilder()
                                                                .add("type", "home")
                                                                .add("number", "222-222-2222")))
                                .build();
                System.out.println(model);

                try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("data/Irteera2.json"))) {

                        jsonWriter.writeObject(model);
                }
        }

}
