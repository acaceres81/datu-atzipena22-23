package dambi.unmarshal;

import java.io.File;


import dambi.business.MendiBat;
import dambi.business.Mendiak;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Unmarshal {

    public static void main(String[] args) throws JAXBException {
        Mendiak mendiak = new Mendiak();
        try {
            File file = new File("mendiDanak.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Mendiak.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            mendiak = (Mendiak) jaxbUnmarshaller.unmarshal(file);
            System.out.println(mendiak);

            Mendiak gipuzkoakomendiak = new Mendiak();
            Mendiak arabakomendiak = new Mendiak();
            Mendiak bizkaikomendiak = new Mendiak();
            Mendiak nafarroakomendiak = new Mendiak();
            for (int i = 0; i < mendiak.getMendiak().size(); i++) {
                MendiBat mendiBat = mendiak.getMendiak().get(i);
                if (mendiBat.getProbintzia().equals("Gipuzkoa")) {
                    gipuzkoakomendiak.add(mendiBat);
                }else if(mendiBat.getProbintzia().equals("Araba")) {
                    arabakomendiak.add(mendiBat);
                }else if(mendiBat.getProbintzia().equals("Bizkaia")) {
                    bizkaikomendiak.add(mendiBat);
                }else if(mendiBat.getProbintzia().equals("Nafarroa")) {
                    nafarroakomendiak.add(mendiBat);
                }
            }

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(gipuzkoakomendiak, new File("gipuzkoakoMendiak.xml"));
            jaxbMarshaller.marshal(gipuzkoakomendiak, System.out);

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(arabakomendiak, new File("arabakoMendiak.xml"));
            jaxbMarshaller.marshal(arabakomendiak, System.out);

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(bizkaikomendiak, new File("bizkaikoMendiak.xml"));
            jaxbMarshaller.marshal(bizkaikomendiak, System.out);

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(nafarroakomendiak, new File("nafarroakoMendiak.xml"));
            jaxbMarshaller.marshal(nafarroakomendiak, System.out);

            Mendiak mendiakOinetan = new Mendiak();
            for (int i = 0; i < mendiak.getMendiak().size(); i++) {
                MendiBat mendiBat = mendiak.getMendiak().get(i);
                int altuera = (int) (mendiBat.getAltuera() * 3.28084);
                mendiBat.setAltuera(altuera);
                mendiakOinetan.add(mendiBat);

            }

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(mendiakOinetan, new File("mendiakOinetan.xml"));
            jaxbMarshaller.marshal(mendiakOinetan, System.out);



        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
