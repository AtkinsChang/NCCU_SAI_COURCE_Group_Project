package edu.nccu.plsm.saiproject.entities;

import java.util.Date;

public class Message {

    private Date created;
    private String name;
    private String email;
    private String message;
    private long uniqueId;

    public Message(Date created, String name, String email, String message, long uniqueId) {
        this.created = created;
        this.name = name;
        this.email = email;
        this.message = message;
        this.uniqueId = uniqueId;
    }

    public long getUniqueId() {
        return uniqueId;
    }
    public String toHtml() {
        return "<div class=\"panel panel-default\"><div class=\"panel-heading\"><h3 class=\"panel-title\">"
                + name + "</h3><footer>"
                + created + "</footer></div><div class=\"panel-body\"><p>"
                + message + "</p></div></div>";
    }

    @Override
    public String toString() {
        return "<span class='created'>CREATED: " + created
                + "</span> <span class='uniqueId'>ID: " + uniqueId
                + "</span> <span class='email'>Email: " + email
                + "</span> <span class='name'>NAME: " + name
                + "</span> <span class='message'>MESSAGE: " + message + "</span>";
    }
}
