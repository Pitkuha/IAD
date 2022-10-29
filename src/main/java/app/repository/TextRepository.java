package app.repository;

import app.domain.Text;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
//    @Query("select t from Text join Markup m where m.markupId.sessionId <> :sessionId")
//    @Query("select t from Text t left join Markup m on t.id = m.markupId.text.id where (m.markupId.sessionId <> :sessionId or m.markupId.sessionId is null)")
    @Query(value = "select * from Text t left join Markup m on t.id = m.text_id where (m.session_id <> :sessionId or m.session_id is null) limit 1", nativeQuery=true)
//    @Query(value = "select * from Text t join Markup m where m.sessionId <> :sessionId", nativeQuery=true)
    Text getTextWithoutSessionId(@Param("sessionId") String sessionId);

}
