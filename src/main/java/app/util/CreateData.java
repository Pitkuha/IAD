package app.util;

import app.domain.Text;
import app.repository.MarkupRepository;
import app.repository.TextRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreateData {
    private final MarkupRepository markupRepository;
    private final TextRepository textRepository;

    public CreateData(MarkupRepository markupRepository, TextRepository textRepository) {
        this.markupRepository = markupRepository;
        this.textRepository = textRepository;
    }

    @PostConstruct
    public void addInTables(){
        if (textRepository.findAll().isEmpty()){
            textRepository.save(new Text("Хуй1"));
            textRepository.save(new Text("Хуй2"));
            textRepository.save(new Text("Хуй3"));
            textRepository.save(new Text("Хуй4"));
            textRepository.save(new Text("Хуй5"));
        }
    }
}
