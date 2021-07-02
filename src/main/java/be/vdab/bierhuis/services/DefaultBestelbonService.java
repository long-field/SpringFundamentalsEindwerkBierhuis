package be.vdab.bierhuis.services;
import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;
import be.vdab.bierhuis.repositories.BestelbonLijnRepository;
import be.vdab.bierhuis.repositories.BestelbonRepository;
import be.vdab.bierhuis.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
@Transactional
public class DefaultBestelbonService implements BestelbonService {
    private final BestelbonRepository bestelbonRepository;
    private final BestelbonLijnRepository bestelbonLijnRepository;
    private final BierRepository bierRepository;
    public DefaultBestelbonService(BestelbonRepository bestelbonRepository, BestelbonLijnRepository bestelbonLijnRepository, BierRepository bierRepository) {
        this.bestelbonRepository = bestelbonRepository;
        this.bestelbonLijnRepository = bestelbonLijnRepository;
        this.bierRepository = bierRepository;
    }
    @Override
    @Transactional
    public long bestelbonCreeeren(Bestelbon entry, Set<BestelbonLijn> bestelbonLijnen) {
        long getNieuweId = bestelbonRepository.create(entry);
        bestelbonLijnen.forEach(lijn -> {
            bestelbonLijnRepository.create(lijn, getNieuweId);
            bierRepository.telBijAantalBesteld(lijn.getAantal(), lijn.getBierId());
        });
        return getNieuweId;
    }
}
