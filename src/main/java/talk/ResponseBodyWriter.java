package talk;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Produces("application/xml")
public class ResponseBodyWriter implements MessageBodyWriter<Response> {
 
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == Response.class;
    }
 
    @Override
    public long getSize(Response response, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }
 
    @Override
    public void writeTo(Response response,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
                        throws IOException, WebApplicationException {
 
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            jaxbContext.createMarshaller().marshal(response, entityStream);
        } catch (JAXBException jaxbException) {
            throw new ProcessingException(
                "Error serializing a Response to the output stream", jaxbException);
        }
    }
}