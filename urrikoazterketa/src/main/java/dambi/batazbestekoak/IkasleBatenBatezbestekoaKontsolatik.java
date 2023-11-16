package dambi.batazbestekoak;

import java.io.FileNotFoundException;
import java.util.Scanner;

import dambi.atzipenekoak.Csva;
import dambi.pojoak.Nota;
import dambi.pojoak.Notak;

public class IkasleBatenBatezbestekoaKontsolatik {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Zein ikasleren batezbestekoa nahi duzu kalkulatu?");
        String izena = in.nextLine();    

        Csva csv = new Csva("data/Notak.csv", "data/BatazbestekoNotak");

        Notak notak = csv.irakurri();
        int kalifikazioa = 0;
        Notak ikaslearenNotak = new Notak();

        if(notak != null){
            for (Nota nota : notak.getNotak()){
                if(nota != null){
                    String ikaslea = nota.getIkaslea();
                    if(ikaslea.equals(izena)){
                        kalifikazioa += nota.getNota();
                        ikaslearenNotak.add(nota);
                    }
                }
            }
        }
            int notaKopuroa = csv.idatzi(ikaslearenNotak);
            double notafinala = (double) kalifikazioa/ (double) (notaKopuroa );

            System.out.println(izena + " ikaslearen batezbesteko nota " + notafinala + " da");
       

    }
}
