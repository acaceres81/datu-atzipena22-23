package dambi.batazbestekoak;

import java.io.FileNotFoundException;

import dambi.atzipenekoak.Csva;
import dambi.pojoak.Notak;

public class IkasleenBatazbestekoa {
    public static void main(String[] args) throws FileNotFoundException {

        Csva csv = new Csva("data/Notak.csv", "data/BatazbestekoNotaDanak");

        Notak notak = csv.irakurri();
        int kalifikazioa = 0;
        Notak ikaslearenNotak = new Notak();
        double notafinala = 0;
        String izena;

        System.out.println("IKASLEA        BATEZBESTEKOA");
        System.out.println("============================");

        if (notak != null) {
            for (int i = 0; i < notak.getNotak().size(); i++) {
                if (i ==0 || i > 0 && !notak.getNotak().get(i).getIkaslea().equals(notak.getNotak().get(0).getIkaslea())) {
                    izena = notak.getNotak().get(i).getIkaslea();
                    for (int j = 0; j < notak.getNotak().size(); j++) {
                        String ikaslea = notak.getNotak().get(j).getIkaslea();
                        if (ikaslea.equals(izena)) {
                            kalifikazioa += notak.getNotak().get(i).getNota();
                            ikaslearenNotak.add(notak.getNotak().get(i));
                        }
                    }
                    int notaKopuroa = csv.idatzi(ikaslearenNotak);
                    notafinala = (double) kalifikazioa / (double) (notaKopuroa);
                }else{
                    break;
                }
                
                
                System.out.println(izena + "   " + notafinala);
            }
        }
    }
}