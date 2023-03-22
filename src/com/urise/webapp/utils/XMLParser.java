package com.urise.webapp.utils;

import javax.xml.bind.*;
import java.io.Reader;
import java.io.Writer;

public class XMLParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XMLParser(Class... classesToBeBound){
        try{
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e){
            throw new IllegalArgumentException(e);
        }
    }

    public <T>T unmarshal (Reader reader){
        try{
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e){
            throw new IllegalArgumentException(e);
        }
    }

    public void marshal (Object instance, Writer writer){
        try {
            marshaller.marshal(instance, writer);
        }catch (JAXBException e){
            throw new IllegalArgumentException(e);
        }
    }
}