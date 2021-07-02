package be.vdab.bierhuis.services;
import be.vdab.bierhuis.domain.Bier;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BierService {
    long findAantal();
    List<Bier> findBierenFromBrouwer(long id);
    Optional<Bier> findById(long id);
    BigDecimal findPrijsById(long id);
    String findNaamById(long id);


}
