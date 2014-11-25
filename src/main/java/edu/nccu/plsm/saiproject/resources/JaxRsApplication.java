package edu.nccu.plsm.saiproject.resources;

import edu.nccu.plsm.saiproject.entities.Message;
import edu.nccu.plsm.saiproject.entities.MessageHtmlWriter;
import edu.nccu.plsm.saiproject.entities.MessageListWriter;
import edu.nccu.plsm.saiproject.entities.MessageWriter;
import edu.nccu.plsm.saiproject.exceptions.NotFoundExceptionMapper;
import edu.nccu.plsm.saiproject.filters.ResponseFilter;
import edu.nccu.plsm.saiproject.interceptors.ValidCharacterInterceptor;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/messageboard")
public class JaxRsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(MessageBoardRootResource.class);
        classes.add(MessageHolderSingletonBean.class);
        classes.add(NotFoundExceptionMapper.class);
        classes.add(MessageWriter.class);
        classes.add(MessageListWriter.class);
        classes.add(MessageHtmlWriter.class);
        classes.add(Message.class);
        classes.add(ResponseFilter.class);
        classes.add(ValidCharacterInterceptor.class);
        return classes;
    }
}