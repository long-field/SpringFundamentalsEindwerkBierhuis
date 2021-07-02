package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql("/inserts.sql")
@Import(JdbcBierRepository.class)
class JdbcBierRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BIEREN = "bieren";
    private final JdbcBierRepository repository;
    private final JdbcTemplate template;
    JdbcBierRepositoryTest(JdbcBierRepository repository, JdbcTemplate template) {
        this.repository = repository;
        this.template = template;
    }
    private long brouwerIdVanTestBier() {
        return jdbcTemplate.queryForObject(
                "select brouwerid from bieren where naam = 'Test1'", Long.class);
    }
    private long aantalVanTestBier() {
        return template.queryForObject("select count(*) from bieren where brouwerid =?", Long.class, brouwerIdVanTestBier());
    }
    private long idVanTestBier() {
        return jdbcTemplate.queryForObject(
                "select id from bieren where naam = 'test1'", Long.class);
    }
    private BigDecimal prijsvantestbier() {
        return template.queryForObject("select prijs from bieren where id = ?", BigDecimal.class, idVanTestBier());
    }
    private long aantalVanTestbier() {
        return template.queryForObject("select besteld from bieren where id = ?", long.class, idVanTestBier());
    }
    @Test
    void findAantal() {
        assertThat(repository.findAantal()).isEqualTo(countRowsInTable(BIEREN));
    }
    @Test
    void findBierenFromBrouwerGeeftAlleBierenVanBrouwerGesorteerd() {
        assertThat(repository.findBierenFromBrouwer(brouwerIdVanTestBier())).hasSize((int) aantalVanTestBier()).extracting(Bier::getNaam).isSorted();
    }
    @Test
    void findBierenFromBrouwerGeeftFoutBijFoutBrouwerId() {
        assertThat(repository.findBierenFromBrouwer(-1)).isEmpty();
    }
    @Test
    void findById() {
        assertThat(repository.findById(idVanTestBier())).hasValueSatisfying(bier -> assertThat(bier.getNaam()).isEqualTo("test1"));
    }
    @Test
    void findPrijsByIdVanTestBierKomtOvereen() {
        assertThat(repository.findById(idVanTestBier())).hasValueSatisfying(bier -> assertThat(bier.getPrijs()).isEqualTo(prijsvantestbier()));
    }
    @Test
    void findPrijsByFoutIdIsEmpty() {
        assertThat(repository.findById(-1)).isEmpty();
    }
    @Test
    void findNaamById() {
        assertThat(repository.findNaamById(idVanTestBier())).isEqualTo("test1");
    }
    @Test
    void getAantalBesteldVanBierIdIsGelijkAanAantalBesteldVanTestBier() {
        assertThat(repository.getAantalBesteldByBierId(idVanTestBier())).isEqualTo(aantalVanTestbier());
    }
    @Test
    void telBijAantalBesteld() {
        var result = aantalVanTestbier() + 10;
        repository.telBijAantalBesteld(10, idVanTestBier());
        assertThat(repository.getAantalBesteldByBierId(idVanTestBier())).isEqualTo(result);
    }
}