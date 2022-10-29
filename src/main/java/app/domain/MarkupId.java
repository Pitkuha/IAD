package app.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class MarkupId implements Serializable {
    private String sessionId;
    @ManyToOne
    private Text text;

    public MarkupId(String sessionId, Text text) {
        this.sessionId = sessionId;
        this.text = text;
    }

    public MarkupId() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

}
