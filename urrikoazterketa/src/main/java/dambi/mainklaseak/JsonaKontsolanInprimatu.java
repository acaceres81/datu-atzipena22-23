package dambi.mainklaseak;

import java.io.FileNotFoundException;

import dambi.atzipenekoak.Jsona;
import dambi.pojoak.Notak;

/**
 * Programa hau ez aldatu!!!
 * Exekutatzerakoan zera inprimatu behar du kontsolatik:
 *   Nota[1, agirrezabala.peru, 2022-02-28, program, 5]
 *   Nota[2, arceredillo.adrian, 2022-02-28, program, 6]
 *   Nota[3, arginzoniz.joseba, 2022-02-28, program, 7]
 *   ...
 * 
 * Aldatu beharko duzuna, Jsona.irakurri metodoa da.
 */
public class JsonaKontsolanInprimatu {
    public static void main(String[] args) throws FileNotFoundException {
        Jsona jsona= new Jsona("data/Notak.json");
        Notak notak = jsona.irakurri();
        System.out.println(notak);
    }
}
