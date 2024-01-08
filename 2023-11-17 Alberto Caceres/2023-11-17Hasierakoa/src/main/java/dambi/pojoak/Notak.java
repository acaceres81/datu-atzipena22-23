package dambi.pojoak;

import java.util.ArrayList;
import java.util.List;

import dambi.atzipenekoak.Csva;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Notak")
public class Notak {

    List<Nota> notak;

    public List<Nota> getNotak() {
        return notak;
    }

    @XmlElement(name = "Nota")
    public void setNotak(List<Nota> notak) {
        this.notak = notak;
    }

    public void add(Nota nota) {
        if (this.notak == null) {
            this.notak = new ArrayList<Nota>();
        }
        this.notak.add(nota);

    }

    /**
     * Hau da erabili dezakezun algoritmoetako bat:
     * Zera errepikatu noten zerrenda hutsik egon arte:
     * - Noten zerrendako "lehen" ikaslea hartu, bere datuak hasieratuz (batura,
     * kontadorea)
     * eta zerrenda errebisatu atzenerarte bere noten bila.
     * - Bere nota bat aurkitutakoan batu, zenbatu eta aurkitutako nota ezabatu
     * egingo dugu traba egin ez dezan
     * - Azken notara heltzerakoan, ikasleen zerrendan gehitu eta berriz hasi.
     * 
     */
    public Ikasleak getIkasleenBB() {

        Ikasleak ikasleak = new Ikasleak();
        Ikaslea ikaslea = new Ikaslea();
        Csva csv = new Csva("datuak/Notak.csv");
        String izena;
        int kalifikazioa = 0;
        Ikasleak ikaslearenNotak = new Ikasleak();
        double notafinala = 0;

        if (notak != null) {
            for (int i = 0; i < notak.size(); i++) {
                if (i ==0 || i > 0 && !notak.get(i).getIkaslea().equals(notak.get(0).getIkaslea())) {
                    izena = notak.get(i).getIkaslea();
                    for (int j = 0; j < notak.size(); j++) {
                        String ikasleastr = notak.get(j).getIkaslea();
                        if (ikaslea.equals(izena)) {
                            kalifikazioa += notak.get(i).getNota();
                        }
                    }
                    notafinala = kalifikazioa / notak.size();
                    
                    ikaslea.setIkaslea(izena);;
                    ikaslea.setBatezbestekoa(notafinala);
                    
                    
                } else {
                    break;
                }
                ikaslearenNotak.add(ikaslea);
            }         
        }
        return ikaslearenNotak;
    }

    /**
     * Ikasle baten nota guztien batezbestekoa kalkulatzen du.
     * 
     * @param ikaslea Adibidez, madariaga.idoia
     * @return Ikaslea motako objektu bat, atributo bezala batezbestekoa kalkulatuta
     *         daukana.
     */
    public Ikaslea getIkaslearenBB(String strIkaslea) {
        int guztira = 0;// ikasleari dagozkion noten batura joango gara gehitzen hemen
        int notaKopurua = 0;// ikasle honi dagazkion nota kopurua joango gara kontatzen hemen
        for (Nota n : notak) {
            if (n.getIkaslea().equals(strIkaslea)) {
                guztira += n.getNota();
                notaKopurua++;
            }
        }
        return new Ikaslea(strIkaslea, guztira / notaKopurua);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (Nota n : this.notak) {
            str.append(n.toString());
            str.append("\n");
        }
        return str.toString();
    }

}
