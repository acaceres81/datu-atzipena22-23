package dambi.xsd;

import java.io.File;
import java.time.LocalDate;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import dambi.business.Country;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class JaxBExampleXSD {
     public static void main( String[] args ) throws Exception
    {
        Country spain = new Country();
        spain.setName( "Spain" );
        spain.setCapital( "Madrid" );
        spain.setContinent( "Europe" );
        spain.setFoundation( LocalDate.of( 1469, 10, 19 ) );
        spain.setPopulation( 45000000 );



        SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        Schema schema = sf.newSchema( new File( "countries.xsd" ) );

        JAXBContext jaxbContext = JAXBContext.newInstance( Country.class );

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        marshaller.setSchema( schema );
        marshaller.marshal( spain, System.out );

    }
}
