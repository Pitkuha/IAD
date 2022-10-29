package app.repository;

import app.domain.Text;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    @Query(value = "select * from Text t left join Markup m on t.id = m.text_id where (m.session_id <> :sessionId or m.session_id is null) limit 1", nativeQuery=true)
    Text getTextWithoutSessionId(@Param("sessionId") String sessionId);

    Text findAllById(long id);
}
