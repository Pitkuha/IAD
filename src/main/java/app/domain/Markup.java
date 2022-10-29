package app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Markup")
public class Markup implements Serializable {
    @EmbeddedId
    MarkupId markupId;
    private String ip;
    private Date date;
    private String markup;


    public Markup() {
    }

    public Markup(MarkupId markupId, String ip, Date date, String markup) {
        this.markupId = markupId;
        this.ip = ip;
        this.date = date;
        this.markup = markup;
    }

    public MarkupId getMarkupId() {
        return markupId;
    }

    public void setMarkupId(MarkupId markupId) {
        this.markupId = markupId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }
}

