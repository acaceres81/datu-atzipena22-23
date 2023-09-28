package dambi.marshall;

import java.io.File;

import dambi.business.MendiBat;
import dambi.business.Mendiak;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class HiruMendiSortu {
    public static void main(String[] args) {

        try {
            MendiBat hernio = new MendiBat();
            hernio.setIzena("Hernio");
            hernio.setAltuera(1075);
            hernio.setProbintzia("Gipuzkoa");

            MendiBat ipitze = new MendiBat();
            ipitze.setIzena("Hernio");
            ipitze.setAltuera(1062);
            ipitze.setProbintzia("Araba");

            MendiBat txamantxoia = new MendiBat();
            txamantxoia.setIzena("Txamantxoia");
            txamantxoia.setAltuera(1945);
            txamantxoia.setProbintzia("Nafarroa");

            Mendiak mendiak = new Mendiak();
            mendiak.add(hernio);
            mendiak.add(ipitze);
            mendiak.add(txamantxoia);

            JAXBContext jaxbContext = JAXBContext.newInstance(Mendiak.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            /* marshaling of java objects in xml (output to file and standard output) */
            jaxbMarshaller.marshal(mendiak, new File("hirumendi.xml"));
            jaxbMarshaller.marshal(mendiak, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
