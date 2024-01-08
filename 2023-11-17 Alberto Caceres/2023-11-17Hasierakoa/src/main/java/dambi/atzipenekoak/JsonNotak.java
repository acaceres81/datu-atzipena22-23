package dambi.atzipenekoak;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

import dambi.pojoak.Nota;
import dambi.pojoak.Notak;

public class JsonNotak {

    public String strFileIn;
    public String strFileOut;

    /**
     * Konstruktoreak parametro bakarra jasotzen badu,
     * sarrera fitxategiaren izena jaso dugula suposatuko dugu.
     */
    public JsonNotak(String strFile) {
        strFileIn = strFile;
    }

    /**
     * Konstruktoreak parametro bi jasotzen baditu,
     * lehengoa, sarrera fitxategiaren izena dela eta bigarrena irteerakoarena
     * direla suposatuko dugu.
     * Sarrera fitxategirik erabiliko ez badugu, kate hutsa erabiliko dugu lehen
     * parametro moduan.
     */
    public JsonNotak(String strFileIn, String strFileOut) {
        this.strFileIn = strFileIn;
        this.strFileOut = strFileOut;
    }

   
    public Notak irakurri() {
        Notak notak = new Notak();
        

        try {
            // Read the jason file and add it to a JsonArray
                JsonReader jsonReader = Json.createReader(new FileReader(strFileIn));
                JsonArray jsonArray = jsonReader.readArray();
            
            // CReate Notak object from the data we collected in the JsonArray
            for(int i = 0; i<jsonArray.size(); i++){
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                Nota nota = new Nota();
                 nota.setId(jsonObject.getInt("id"));
                 nota.setIkaslea(jsonObject.getString("ikaslea"));
                 nota.setData(jsonObject.getString("data"));
                 nota.setNota((double)(jsonObject.getInt("nota")));
                 notak.add(nota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notak;
    }

   
}
