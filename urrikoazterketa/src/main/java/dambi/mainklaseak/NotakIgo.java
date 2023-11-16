package dambi.mainklaseak;

import dambi.atzipenekoak.Csva;
import dambi.atzipenekoak.Xmla;
import dambi.pojoak.Nota;
import dambi.pojoak.Notak;

public class NotakIgo {

    public static void main(String[] arg){
        
        Csva csv = new Csva("data/Notak.csv");
        Notak notak = csv.irakurri();

        Notak notaBerriak = new Notak();

        System.out.println(notak.toString());

        for (Nota nota : notak.getNotak()){
            if(nota.getNota()<10){
                int kalifikazioa = nota.getNota() + 1;
                nota.setNota(kalifikazioa);
            }
            notaBerriak.add(nota);
        }

        Xmla xmla = new Xmla("","data/NotaHobetua.xml");
        xmla.idatzi(notaBerriak);


        

        
    }
    
}
