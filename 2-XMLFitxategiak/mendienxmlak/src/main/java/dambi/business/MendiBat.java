package dambi.business;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType( propOrder = { "izena", "altuera", "probintzia" } )
@XmlRootElement( name = "Mendia" )

public class MendiBat {
    
    
    
    public MendiBat() {
    }

    String izena, probintzia;
    int altuera;

    public String getIzena() {
        return izena;
    }
    public String getProbintzia() {
        return probintzia;
    }
    public int getAltuera() {
        return altuera;
    }
    

    @XmlElement( name = "Izena")
    public void setIzena(String izena) {
        this.izena = izena;
    }

    @XmlElement( name = "Probintzia")
    public void setProbintzia(String probintzia) {
        this.probintzia = probintzia;
    }

    @XmlElement( name = "Altuera")
    public void setAltuera(int altuera) {
        this.altuera = altuera;
    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer("Izena:" + getIzena() + "\n");
        str.append("Altuera:" + getAltuera() + "\n");
        str.append("Probintzia:" + getProbintzia() + "\n");


        return str.toString();
    }



    


}
