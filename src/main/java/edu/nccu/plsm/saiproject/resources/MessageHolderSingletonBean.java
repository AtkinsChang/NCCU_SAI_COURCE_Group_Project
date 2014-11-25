package edu.nccu.plsm.saiproject.resources;


import edu.nccu.plsm.saiproject.entities.Message;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class MessageHolderSingletonBean {

    AtomicLong currentId = new AtomicLong(0L);
    private LinkedList<Message> list = new LinkedList<>();
    private int maxMessages = 1000;

    public MessageHolderSingletonBean() {
        super();
    }

    public List<Message> getMessages() {
        return new LinkedList<>(list);
    }

    private long getNewId() {
        return currentId.getAndIncrement();
    }

    @Lock(LockType.WRITE)
    public Message addMessage(String name, String email, String msg) {
        return addMessage(name, email, msg, new Date());
    }

    @Lock(LockType.WRITE)
    private Message addMessage(String name, String email, String msg, Date date) {
        Message m = new Message(date, name, email, msg, getNewId());
        list.addFirst(m);
        return m;
    }

    public Message getMessage(long uniqueId) {
        for (Message m : list) {
            if (m.getUniqueId() == uniqueId) {
                return m;
            }
        }
        return null;
    }

    @Lock(LockType.WRITE)
    public boolean deleteMessage(int uniqueId) {
        for (Message m : list) {
            if (m.getUniqueId() == uniqueId) {
                list.remove(m);
                return true;
            }
        }
        return false;
    }
}
