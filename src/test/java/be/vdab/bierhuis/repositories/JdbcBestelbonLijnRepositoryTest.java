package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;
import be.vdab.bierhuis.domain.Bier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql("/inserts.sql")
@Import({JdbcBestelbonLijnRepository.class, JdbcBestelbonRepository.class})
class JdbcBestelbonLijnRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BESTELBONLIJNEN = "bestelbonLijnen";
    private final JdbcBestelbonLijnRepository repository;
    private final JdbcTemplate template;
    private final RowMapper<Bier> bierMapper = (result, rowNum) ->
            new Bier(result.getInt("id"), result.getString("naam"),
                    result.getInt("brouwerid"), result.getBigDecimal("alcohol"),
                    result.getBigDecimal("prijs"), result.getLong("besteld"));
    JdbcBestelbonLijnRepositoryTest(JdbcBestelbonLijnRepository repository, JdbcTemplate template) {
        this.repository = repository;
        this.template = template;
    }
    private long idVanTestBier() {
        return jdbcTemplate.queryForObject("select id from bieren where naam = 'test1'", Long.class);
    }
    private Bier TestBier() {
        var sql = "select * from bieren where id = ?";
        return template.queryForObject(sql, bierMapper, idVanTestBier());
    }
    @Test
    void create() {
        var bier = TestBier();
        var lijn = new BestelbonLijn(bier.getId(), bier.getNaam(), 11111, bier.getPrijs());
        repository.create(lijn, 9999L);
        assertThat(countRowsInTableWhere(BESTELBONLIJNEN, "bestelbonid = " + 9999L)).isOne();

    }
}

