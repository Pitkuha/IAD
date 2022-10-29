package app.service;

import app.DTO.Data;
import app.DTO.GetResponse;
import app.domain.Text;
import app.repository.MarkupRepository;
import app.repository.TextRepository;
import app.util.HttpUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;

@Service
public class ResponseService {
    private final TextRepository textRepository;
    private final MarkupRepository markupRepository;
    private final JWTService jwtService;

    public ResponseService(TextRepository textRepository, MarkupRepository markupRepository, JWTService jwtService) {
        this.textRepository = textRepository;
        this.markupRepository = markupRepository;
        this.jwtService = jwtService;
    }

    public GetResponse getText(String sessionId, HttpServletRequest request){
        boolean success;
        String message;
        Text t = textRepository.getTextWithoutSessionId(sessionId);
        if (t != null){
            success = true;
            message = "ok";
        } else {
            success = false;
            message = "not ok";
        }
        String jwtKey = jwtService.createToken(t.getTextId(), sessionId, HttpUtil.getRequestIP(request));
        return new GetResponse(success, message, new Data(jwtKey,t.getText()));
    }
}
