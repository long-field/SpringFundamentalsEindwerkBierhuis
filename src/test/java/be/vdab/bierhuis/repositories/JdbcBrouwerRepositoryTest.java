package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Brouwer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql("/inserts.sql")
@Import(JdbcBrouwerRepository.class)
class JdbcBrouwerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BROUWERS = "brouwers";
    private final JdbcBrouwerRepository repository;
    JdbcBrouwerRepositoryTest(JdbcBrouwerRepository repository) {
        this.repository = repository;
    }
    private long idVanTestBrouwer() {
        return jdbcTemplate.queryForObject(
                "select id from brouwers where naam = 'Testbrouwer1'", Long.class);
    }
    @Test
    void findAllGeeftAlleBrouwersGesorteerdOpId() {
        assertThat(repository.findAll()) .hasSize(countRowsInTable(BROUWERS))
                .extracting(Brouwer::getBrouwerId).isSorted();
    }
    @Test
    void findById() {
        assertThat(repository.findById(idVanTestBrouwer())) .hasValueSatisfying(bier->assertThat(bier.getNaam()).isEqualTo("Testbrouwer1"));
    }
    @Test
    void findByOnbestaandeIdGeeftNull() {
        assertThat(repository.findById(-1)) .isNull();
    }
}