package be.vdab.bierhuis.services;
import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;
import java.util.Set;

public interface BestelbonService {
    long bestelbonCreeeren(Bestelbon entry, Set<BestelbonLijn> entrylijst);
}
