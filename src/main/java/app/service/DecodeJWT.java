package app.service;

public class DecodeJWT {
    private long textId;
    private String sessionId;
    private String ip ;

    public DecodeJWT(long textId, String sessionId, String ip) {
        this.textId = textId;
        this.sessionId = sessionId;
        this.ip = ip;
    }

    public long getTextId() {
        return textId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getIp() {
        return ip;
    }
}
