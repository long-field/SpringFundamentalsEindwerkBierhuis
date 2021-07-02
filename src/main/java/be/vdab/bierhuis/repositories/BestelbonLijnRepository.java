package be.vdab.bierhuis.repositories;
import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;

public interface BestelbonLijnRepository {
    public long create(BestelbonLijn entry,Long key);
}
