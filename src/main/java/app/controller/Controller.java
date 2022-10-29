package app.controller;

import app.DTO.GetResponse;
import app.service.JWTService;
import app.service.ResponseService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/**")
public class Controller {

    private final JWTService jwtService = new JWTService();

    private final ResponseService responseService;

    public Controller(ResponseService responseService) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        this.responseService = responseService;
    }

    @GetMapping("/check")
    public String showStatus(){
        return "Success";
    }

    @GetMapping("/markup/next")
    public GetResponse getNext(HttpSession session, HttpServletRequest request){
        String sessionId = session.getId();
        return responseService.getText(sessionId, request);
    }

    @PostMapping(value = "/markup/{key}")
    public String record(@PathVariable long key) {
        return "Здарова заебал номер + " + key;
    }
}
