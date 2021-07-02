package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.exceptions.BierNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcBierRepository implements BierRepository{
    private final JdbcTemplate template;
    private final RowMapper<Bier> bierMapper = (result, rowNum) ->
             new Bier(result.getInt("id"), result.getString("naam"),
                     result.getInt("brouwerid"), result.getBigDecimal("alcohol"),
                     result.getBigDecimal("prijs"),result.getLong("besteld"));
    public JdbcBierRepository(JdbcTemplate template) {
        this.template = template;
    }
    @Override
    public long findAantal() {
        return template.queryForObject("select count(*) from bieren", Long.class);
    }
    @Override
    public List<Bier> findBierenFromBrouwer(long brouwerId) {
        return template.query("select * from bieren where brouwerid = ? order by naam" , bierMapper, brouwerId);
    }

    @Override
    public Optional<Bier> findById(long id) {
        try {
            var sql = "select * from bieren where id = ?";
            return Optional.of(template.queryForObject(sql, bierMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
    @Override
    public BigDecimal findPrijsById(long id) {
        try {
            var sql = "select prijs from bieren where id = ?";
            return template.queryForObject(sql,BigDecimal.class,id);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
    @Override
    public String findNaamById(long id) {
        try {
            var sql = "select naam from bieren where id = ?";
            return template.queryForObject(sql,String.class,id);
        }
        catch (IncorrectResultSizeDataAccessException ex) {
        return null;
        }
    }
    @Override
    public Long getAantalBesteldByBierId(long id) {
        try { var sql = "select besteld from bieren where id =?";
        return template.queryForObject(sql, Long.class,id);
        }
        catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
    @Override
    public void telBijAantalBesteld(long aantal,long id) {
        try {
            var sql = "update bieren set besteld=? where id = ?";
            var aantalUpdate = aantal + getAantalBesteldByBierId(id);
            if (template.update(sql, aantalUpdate, id) == 0) {
                //Wordt niet gethrowed door IncorrectResultSizeDataAccessException in getAantalBesteldByBierId(id
                throw new BierNietGevondenException();
            }
        }
        catch (IncorrectResultSizeDataAccessException ex) {}
    }
}
