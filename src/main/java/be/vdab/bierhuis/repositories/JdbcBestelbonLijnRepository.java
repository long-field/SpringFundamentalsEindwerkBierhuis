package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.BestelbonLijn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class JdbcBestelbonLijnRepository implements BestelbonLijnRepository{
    private final SimpleJdbcInsert insert;
    private final JdbcTemplate template;
    public JdbcBestelbonLijnRepository(JdbcTemplate template) {
        insert=new SimpleJdbcInsert(template).withTableName("bestelbonlijnen");
        this.template = template;
    }
    @Override
    public long create(BestelbonLijn entry, Long key) {
        return insert.execute(Map.of("bestelbonid", key, "bierid", entry.getBierId(), "aantal", entry.getAantal(), "prijs", entry.getPrijs()));
    }
}
