package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

@JdbcTest
@Import(JdbcBestelbonRepository.class)
class JdbcBestelbonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BESTELBONNEN = "bestelbonnen";
    private final JdbcBestelbonRepository repository;
    JdbcBestelbonRepositoryTest(JdbcBestelbonRepository repository) {
        this.repository = repository;
    }
    @Test
    void create() {
       var bestelbonLijnen = new TreeSet<BestelbonLijn>();
        bestelbonLijnen.add(new BestelbonLijn(0, "", 0, BigDecimal.ZERO));
        var bon =new Bestelbon(0,"Test", "Teststraat1","65", (short) 1000, "Testgemeente1",bestelbonLijnen);
        var id =repository.create(bon);
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(BESTELBONNEN,"id = " + id)).isOne();
    }
}