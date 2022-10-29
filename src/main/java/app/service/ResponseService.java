package app.service;

import app.DTO.Data;
import app.DTO.GetResponse;
import app.DTO.PostResponse;
import app.domain.Markup;
import app.domain.MarkupId;
import app.domain.Text;
import app.repository.MarkupRepository;
import app.repository.TextRepository;
import app.util.HttpUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@EnableScheduling
public class ResponseService {
    private final TextRepository textRepository;
    private final MarkupRepository markupRepository;
    private final JWTService jwtService;

    public ResponseService(TextRepository textRepository, MarkupRepository markupRepository, JWTService jwtService) {
        this.textRepository = textRepository;
        this.markupRepository = markupRepository;
        this.jwtService = jwtService;
    }

    public GetResponse getText(String sessionId, HttpServletRequest request) {
        boolean success;
        String message;
        Text t = textRepository.getTextWithoutSessionId(sessionId);
        if (t != null) {
            success = true;
            message = "ok";
        } else {
            success = false;
            message = "not ok";
        }
        String jwtKey = jwtService.createToken(t.getTextId(), sessionId, HttpUtil.getRequestIP(request));
        return new GetResponse(success, message, new Data(jwtKey, t.getText()));
    }

    public PostResponse postText(String sessionId, HttpServletRequest request, String text, String token) {
        DecodeJWT decodeJWT = jwtService.decodeJWT(token);
        Text t = textRepository.findAllById(decodeJWT.getTextId());
        MarkupId markupId = new MarkupId(sessionId, t);
        Date date = new Date();
        Markup markup = new Markup(markupId, HttpUtil.getRequestIP(request), new Date(), text, null);
        PostResponse postResponse;
        if (decodeJWT != null) {
            postResponse = new PostResponse(true, "ok");
        } else {
            postResponse = new PostResponse(false, "not ok");
        }
        markupRepository.save(markup);
        return postResponse;
    }

    //1000 - 1 секунда
    @Scheduled(fixedDelay = 1000)
    public static void huy(){
        System.out.println("huy");
    }
}
