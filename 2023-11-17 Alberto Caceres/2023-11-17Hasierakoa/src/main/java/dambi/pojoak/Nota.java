package dambi.pojoak;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType( propOrder = { "data", "id","ikaslea", "ikasgaia", "nota" } )
@XmlRootElement( name = "Nota" )
public class Nota {

    int id = 0;
    String ikaslea;
    LocalDate data;
    String ikasgaia;
    double nota;

    public int getId(){
       return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getIkaslea() {
        return ikaslea;
    }

    @XmlElement(name = "ikaslea")
    public void setIkaslea(String ikaslea) {
        this.ikaslea = ikaslea;
    }

    public String getData() {
        return data.toString();
    }

    @XmlElement(name = "data")
    public void setData(String data) {
        this.data = LocalDate.parse(data);
    }

    public String getIkasgaia() {
        return ikasgaia;
    }

    @XmlElement(name = "ikasgaia")
    public void setIkasgaia(String ikasgaia) {
        this.ikasgaia = ikasgaia;
    }
    
    public double getNota() {
        return nota;
    }

    @XmlElement(name = "nota")
    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Nota["+ id+", "+ikaslea+", "+data+", "+ikasgaia+", "+nota+"]";
    }

}
