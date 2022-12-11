package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    long count();

//    @Query(value = "SELECT a FROM Article a WHERE a.artikelnummer = :artikelnummer")
//    Optional<Article> getArticleByArtikelNummer(@Param("artikelnummer") String artikelnummer);

    Optional<Article> getArticleByArtikelnummer(String id);
}
