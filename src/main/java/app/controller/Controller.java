package app.controller;

import app.DTO.GetResponse;
import app.DTO.PostResponse;
import app.service.JWTService;
import app.service.ResponseService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@CrossOrigin(origins = "http://localhost:5000", allowCredentials = "true")
@RestController
//@RequestMapping("/api")
public class Controller {

    private final JWTService jwtService = new JWTService();

    private final ResponseService responseService;

    public Controller(ResponseService responseService) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        this.responseService = responseService;
    }


    @GetMapping("/check")
    public String showStatus() {
        return "Success";
    }

    @GetMapping("/markup/next")
    public GetResponse getNext(HttpSession session, HttpServletRequest request) {
        String sessionId = session.getId();
        return responseService.getText(sessionId, request);
    }

    @PostMapping(value = "/markup/{key}", produces = "application/json")
    public PostResponse record(@PathVariable String key, @RequestBody String markup, HttpSession session, HttpServletRequest request) {
        PostResponse postResponse = responseService.postText(session.getId(), request, markup, key);
        return postResponse;
    }
}
