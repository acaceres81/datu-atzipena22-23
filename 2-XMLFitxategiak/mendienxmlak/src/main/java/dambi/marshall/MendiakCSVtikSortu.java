package dambi.marshall;

import dambi.business.MendiBat;
import java.io.FileNotFoundException;
import dambi.business.Mendiak;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MendiakCSVtikSortu {


    static BufferedReader inputStream = null;
    static String artxiboa = "Mendiak.csv";

    public static void main(String[] args) throws IOException, JAXBException {

        Mendiak mendiak = new Mendiak();

        try {
            inputStream = new BufferedReader(new FileReader(artxiboa));

            String l;
            while ((l = inputStream.readLine()) != null) {
                // Split each line on the ; and add it to an Array of strings
                String[] arrOfStr = l.split(";");

                // skip the first line because it´s the header.
                if (arrOfStr[0].equals("MENDIA")) {
                    continue;
                    // create a mendia for each line
                } else {
                    MendiBat mendia = new MendiBat(arrOfStr[0], Integer.parseInt(arrOfStr[1]), arrOfStr[2]);
                    // Add Mendia to Mendiak object
                    mendiak.add(mendia);
                }
                System.out.println(mendiak.toString());
            }

        }catch(FileNotFoundException e){
            System.out.println("the file your are looking for does not exist");

        } 
        
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        /* marshaling of java objects in xml (output to file and standard output) */
        JAXBContext jaxbContext = JAXBContext.newInstance(Mendiak.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(mendiak, new File("mendiDanak.xml"));
        jaxbMarshaller.marshal(mendiak, System.out);

    }
}
