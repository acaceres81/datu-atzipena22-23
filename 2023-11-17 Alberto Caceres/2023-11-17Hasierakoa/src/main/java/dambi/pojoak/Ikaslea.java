package dambi.pojoak;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"ikaslea","batezbestekoa"})
@XmlRootElement(name = "ikaslea")
public class Ikaslea {
    String ikaslea;    
    double batezbestekoa;
    
    public Ikaslea() {
    }
    public Ikaslea(String ikaslea, double batezbestekoa) {
        this.ikaslea = ikaslea;
        this.batezbestekoa = batezbestekoa;
    }
    public String getIkaslea() {
        return ikaslea;
    }

    @XmlElement(name = "ikaslea")
    public void setIkaslea(String ikaslea) {
        this.ikaslea = ikaslea;
    }
    
    public double getBatezbestekoa() {
        return batezbestekoa;
    }

    @XmlElement(name = "batezbestekoa")
    public void setBatezbestekoa(double batezbestekoa) {
        this.batezbestekoa = batezbestekoa;
    }
}
