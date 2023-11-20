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

import dambi.pojoak.Ikaslea;
import dambi.pojoak.Ikasleak;
import dambi.pojoak.Nota;
import dambi.pojoak.Notak;


public class JsonIkasleak {

    public String strFileIn;
    public String strFileOut;

    /**
     * Konstruktoreak parametro bakarra jasotzen badu,
     * sarrera fitxategiaren izena jaso dugula suposatuko dugu.
     */
    public JsonIkasleak(String strFile) {
        strFileIn = strFile;
    }

    /**
     * Konstruktoreak parametro bi jasotzen baditu,
     * lehengoa, sarrera fitxategiaren izena dela eta bigarrena irteerakoarena
     * direla suposatuko dugu.
     * Sarrera fitxategirik erabiliko ez badugu, kate hutsa erabiliko dugu lehen
     * parametro moduan.
     */
    public JsonIkasleak(String strFileIn, String strFileOut) {
        this.strFileIn = strFileIn;
        this.strFileOut = strFileOut;
    }

    public int idatzi(Ikasleak ikasleak) throws FileNotFoundException {
        int ikasleKopurua = 0;
        
        // declare the jasonArraybuilder and add the ikasleak
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < ikasleak.getIkasleak().size();i++){
            JsonObject ikaslObject = Json.createObjectBuilder()
                .add("ikaslea", ikasleak.getIkasleak().get(i).getIkaslea())
                .add("batazbestekoNota", ikasleak.getIkasleak().get(i).getBatezbestekoa())
                .build();
                jsonArrayBuilder.add(ikaslObject);
                ikasleKopurua++;
        }

        JsonArray jsonArray = jsonArrayBuilder.build();

        try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(strFileOut))) {
            jsonWriter.writeArray(jsonArray);
        }

        return ikasleKopurua;

    }

    /**
     * ADI: Notak objetu bat jasotzen du metodo honek
     * @param notak
     * @return
     */
    public int idatzi(Notak notak) {
        Ikasleak ikasleak = new Ikasleak();
        ikasleak = notak.getIkasleenBB(); //METODO HAU ERE OSATU BEHAR DUZU DAGOKION LEKUAN
        
        int notaKopurua = 0;
        JsonArray model = null;
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Nota n : notak.getNotak()) {
            jab.add(Json.createObjectBuilder()
                    .add("id", n.getId())
                    .add("ikaslea", n.getIkaslea())
                    .add("data", n.getData())
                    .add("ikasgaia", n.getIkasgaia())
                    .add("nota",n.getNota())
                    .build());
            notaKopurua++;
            
        }
        model=jab.build();

        try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(strFileOut))) {
            jsonWriter.writeArray(model);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Fitxategia sortzerakoan arazoak.");
        }
        return notaKopurua;

    }
}
