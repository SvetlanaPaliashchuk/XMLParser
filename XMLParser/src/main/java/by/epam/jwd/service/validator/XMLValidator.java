package by.epam.jwd.service.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {

    private static final String XSD_FILE = "/orders.xsd";

    public XMLValidator() {
    }

    public boolean validateXML(String xmlFilePath) throws  XMLValidatorException {
        try {
            String xsdPath = XMLValidator.class.getClassLoader().getResource("").getPath() + XSD_FILE;
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
        } catch (IOException e) {
            throw new XMLValidatorException("Wrong path", e);
        } catch (SAXException e) {
            throw new XMLValidatorException("Invalid file", e);
        }
        return true;
    }
}