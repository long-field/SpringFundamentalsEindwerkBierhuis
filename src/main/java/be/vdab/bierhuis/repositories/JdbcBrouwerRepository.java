package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Adres;
import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.domain.Gemeente;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBrouwerRepository implements BrouwerRepository{
    private final JdbcTemplate template;
    public JdbcBrouwerRepository(JdbcTemplate template) {
        this.template = template;
    }
    private final RowMapper<Brouwer> brouwerMapper = (result, rowNum) ->
            new Brouwer(result.getLong("id"), result.getString("naam"),
                    new Adres(result.getString("straat"),result.getString("huisNr"),
                            new Gemeente(result.getInt("postcode"),result.getString("gemeente"))),result.getBigDecimal("omzet"));
    @Override
    public List<Brouwer> findAll() {
        var sql = "select * from brouwers order by id";
        return template.query(sql, brouwerMapper);
    }
    @Override
    public Optional<Brouwer> findById(long brouwerId) {
        try{
            var sql = "select * from brouwers where id = ?";
            return Optional.of(template.queryForObject(sql, brouwerMapper, brouwerId));
        }
        catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
}
