package dambi.unmarshal;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import dambi.business.Mendiak;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Unmarshal {

    public static void main( String[] args )
    {
        try
        {

            File file = new File( "hirumendi.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance( Mendiak.class );

            /**
             * the only difference with the marshaling operation is here
             */
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Mendiak  mendiak = (Mendiak)jaxbUnmarshaller.unmarshal( file );
            System.out.println( mendiak );

        }
        catch( JAXBException e )
        {
            e.printStackTrace();
        }

    }
    
}
