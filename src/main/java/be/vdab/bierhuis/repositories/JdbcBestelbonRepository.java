package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class JdbcBestelbonRepository implements BestelbonRepository {
    private final SimpleJdbcInsert insert;
    private final JdbcTemplate template;
    public JdbcBestelbonRepository(JdbcTemplate template) {
        insert = new SimpleJdbcInsert(template).withTableName("bestelbonnen").usingGeneratedKeyColumns("id");
        this.template = template;
    }
    @Override
    public long create(Bestelbon entry) {
      return insert.executeAndReturnKey(Map.of("naam", entry.getNaam(), "straat", entry.getStraat(), "huisNr", entry.getHuisNr(),"postcode", entry.getPostcode(),"gemeente", entry.getGemeente())).longValue();
    }
}
