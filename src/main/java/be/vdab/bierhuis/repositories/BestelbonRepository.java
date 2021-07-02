package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;

public interface BestelbonRepository {
    public long create(Bestelbon entry);
}
