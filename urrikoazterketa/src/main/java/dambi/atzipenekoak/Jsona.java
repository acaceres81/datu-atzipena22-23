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

public class Jsona {

    public String strFileIn;
    public String strFileOut;

    /**
     * Konstruktoreak parametro bakarra jasotzen badu,
     * sarrera fitxategiaren izena jaso dugula suposatuko dugu.
     */
    public Jsona(String strFile) {
        strFileIn = strFile;
    }

    /**
     * Konstruktoreak parametro bi jasotzen baditu,
     * lehengoa, sarrera fitxategiaren izena dela eta bigarrena irteerakoarena
     * direla suposatuko dugu.
     * Sarrera fitxategirik erabiliko ez badugu, kate hutsa erabiliko dugu lehen
     * parametro moduan.
     */
    public Jsona(String strFileIn, String strFileOut) {
        this.strFileIn = strFileIn;
        this.strFileOut = strFileOut;
    }

    /** Osatu ezazu metodo hau
     * @throws FileNotFoundException
    *
    */	 
    public Notak irakurri() throws FileNotFoundException {
        Notak notak = new Notak();
        try {
        // Read the jason file and add it to a JsonArray
            JsonReader jsonReader = Json.createReader(new FileReader(strFileIn));
            JsonArray jsonArray = jsonReader.readArray();
            

            // create the Product objects from the data in the JsonArray
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                // si tenemos un constructor con los atributos, lo pondriamos como en la erronka.
                //Aqui como el constructor es sin atributos, se hace un set por cada atributo.
                Nota nota = new Nota();
                nota.setId(jsonObject.getInt("id"));
                nota.setIkaslea(jsonObject.getString("ikaslea"));
                nota.setData(jsonObject.getString("data"));
                nota.setIkasgaia(jsonObject.getString("ikasgaia"));
                nota.setNota(jsonObject.getInt("nota"));
                notak.add(nota);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fitxategia ez da existitzen");
        } catch (NullPointerException e) {
            System.out.println("Ez da fitxategia sortu ez dagoelako notarik.");
        }
        return notak;
    }

    public int idatzi(Notak notak) {
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
