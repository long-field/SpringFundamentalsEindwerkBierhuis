package be.vdab.bierhuis.services;
import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DefaultBierService implements BierService{
    private final BierRepository bierRepository;
    public DefaultBierService(BierRepository bierRepository) {
        this.bierRepository = bierRepository;
    }
    @Override
    public long findAantal() {
        return bierRepository.findAantal();
    }
    @Override
    public List<Bier> findBierenFromBrouwer(long id){
        return bierRepository.findBierenFromBrouwer(id);
    }
    @Override
    public Optional<Bier> findById(long id) {
        return bierRepository.findById(id);
    }
    @Override
    public BigDecimal findPrijsById(long id) {
        return bierRepository.findPrijsById(id);
    }
    @Override
    public String findNaamById(long id) {
        return bierRepository.findNaamById(id);
    }
}
