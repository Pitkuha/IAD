package app.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Text")
public class Text implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public Text() {
    }

    public long getTextId() {
        return id;
    }

    public void setTextId(long textId) {
        this.id = textId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
