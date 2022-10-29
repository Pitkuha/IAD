package app.repository;

import app.domain.Markup;
import app.domain.MarkupId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkupRepository extends JpaRepository<Markup, MarkupId> {
}
