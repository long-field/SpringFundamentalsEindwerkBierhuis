package be.vdab.bierhuis.services;
import be.vdab.bierhuis.domain.Brouwer;
import java.util.List;
import java.util.Optional;

public interface BrouwerService {
    List<Brouwer> findAll();
    Optional<Brouwer> findById(long brouwerId);
}
