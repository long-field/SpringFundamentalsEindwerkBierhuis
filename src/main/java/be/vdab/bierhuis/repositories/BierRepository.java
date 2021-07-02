package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bier;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BierRepository {
    long findAantal();
    List<Bier> findBierenFromBrouwer(long brouwerId);
    //List<Bier> findByIds(List<Long> ids);
    Optional<Bier> findById(long id);
    BigDecimal findPrijsById(long id);
    String findNaamById(long id);
    Long getAantalBesteldByBierId(long id);
    void telBijAantalBesteld(long aantal,long id);
}
