package dambi.business;


import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Mendiak" )
public class Mendiak {
    
    List<MendiBat> mendiak;

    public List<MendiBat> getMendiak() {
        return mendiak;
    }

    @XmlElement( name = "Mendia" )
    public void setMendiak(List<MendiBat> mendiak) {
        this.mendiak = mendiak;
    }

    
    public void add (MendiBat mendia){
        if(this.mendiak == null){
            this.mendiak = new ArrayList<MendiBat>();
        }
        this.mendiak.add( mendia );
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        for( MendiBat mendi: this.mendiak){
            str.append(mendi.toString());
        }

        return str.toString();
    }

    

    
}
