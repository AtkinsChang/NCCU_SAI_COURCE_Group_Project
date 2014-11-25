package edu.nccu.plsm.saiproject.entities;

import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Stateless
@Provider
public class MessageHtmlWriter implements MessageBodyWriter<List<String>> {

    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) {
        return verifyGenericType(type);
    }

    private boolean verifyGenericType(Type genericType) {
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        final ParameterizedType pt = (ParameterizedType) genericType;

        if (pt.getActualTypeArguments().length > 1) {
            return false;
        }

        if (!(pt.getActualTypeArguments()[0] instanceof Class)) {
            return false;
        }

        final Class listClass = (Class) pt.getActualTypeArguments()[0];
        return listClass == String.class;
    }

    public long getSize(List<String> messages, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) {
        return -1;
    }

    public void writeTo(List<String> messages, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType,
                        MultivaluedMap<String, Object> arg5, OutputStream outputStream) throws IOException, WebApplicationException {
        for (String message : messages) {
            outputStream.write(message.getBytes());
        }
    }
}
