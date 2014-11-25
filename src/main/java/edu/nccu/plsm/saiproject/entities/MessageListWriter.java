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
import java.net.URI;
import java.util.List;

@Stateless
@Provider
public class MessageListWriter implements MessageBodyWriter<List<Message>> {

    @Context
    private javax.inject.Provider<UriInfo> ui;


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
        return listClass == Message.class;
    }

    public long getSize(List<Message> messages, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) {
        return -1;
    }

    public void writeTo(List<Message> messages, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType,
                        MultivaluedMap<String, Object> arg5, OutputStream outputStream) throws IOException, WebApplicationException {
        for (Message message : messages) {
            outputStream.write(message.toString().getBytes());
            URI mUri = ui.get().getAbsolutePathBuilder().path(Long.toString(message.getUniqueId())).build();
            outputStream.write((" <a href='" + mUri.toASCIIString() + "'>link</a>").getBytes());
            outputStream.write((" <a href='javascript:deleteMessage(" + message.getUniqueId() + ")'>DELETE</a><br />").getBytes());
        }
    }
}
