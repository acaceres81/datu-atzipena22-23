package dambi.marshall;

import java.io.File;


import dambi.business.MendiBat;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class MendiBatSortu {
    
    public static void main( String[] args ){
        try{
            MendiBat hernio = new MendiBat();
            hernio.setIzena("Hernio");
            hernio.setAltuera(1075);
            hernio.setProbintzia("Gipuzkoa");

            JAXBContext jaxbContext = JAXBContext.newInstance( MendiBat.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            jaxbMarshaller.marshal( hernio, new File( "mendiBat.xml" ) );
            jaxbMarshaller.marshal( hernio, System.out );


        }catch (JAXBException e) {
            e.printStackTrace();
        }
        
    }
}
