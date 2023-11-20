package dambi.mainklaseak;


import dambi.atzipenekoak.Csva;
import dambi.atzipenekoak.Xmla;
import dambi.pojoak.Nota;
import dambi.pojoak.Notak;

/**
 * Datu-iturria: Notak.csv
 * Sortutako fitxategia: NotaHobetuak.xml
 * Programa honek nota guztiak puntu bat igoko ditu,
 * kontutan izanik notarik altuena 10 dela.
 * 
 */
public class NotakIgo {
  public static void main(String[] args) {
    
    String fileName = "datuak/Notak.csv";
    Csva csv = new Csva(fileName);

    Notak notak = csv.irakurri();
    double kalifikazioa;
    
    for(int i = 0; i<notak.getNotak().size(); i++){
      if(notak.getNotak().get(i).getNota() >= 9){
        notak.getNotak().get(i).setNota(10);
      }else{
        kalifikazioa = notak.getNotak().get(i).getNota() + 1;
        notak.getNotak().get(i).setNota(kalifikazioa);
      }
    }
    System.out.println(notak.getNotak().size() + " nota idatzi dira xml fitxategian");

    System.out.println(notak.toString());

    Xmla xml = new Xmla("", "datuak/NotaHobetuak.xml");
    xml.idatzi(notak);
   
  }
}