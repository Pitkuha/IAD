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
            textRepository.save(new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque dictum lectus sapien. Nam aliquet tincidunt venenatis. Nam a hendrerit purus, sit amet suscipit dui."));
            textRepository.save(new Text("Sed dignissim ornare diam ut rutrum. Nunc lacinia imperdiet risus. Nullam at arcu nunc. Sed hendrerit ut risus maximus fermentum."));
            textRepository.save(new Text("Ut rhoncus turpis vitae est tempus rhoncus. Curabitur libero dolor, mattis nec arcu nec, fermentum tristique turpis. Etiam faucibus commodo est, id tincidunt orci mollis vitae."));
            textRepository.save(new Text("Aliquam nec vestibulum lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eu consequat purus."));
            textRepository.save(new Text("Etiam tincidunt aliquam volutpat. Nunc pellentesque nulla a magna interdum posuere. Cras consequat ac nisl laoreet fermentum. Aenean iaculis, nunc vel malesuada congue, diam purus finibus ex, hendrerit vestibulum velit enim elementum arcu."));
        }
    }
}
