package be.vdab.bierhuis.domain;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.TreeSet;

public class Bestelbon {
    private final long bestelBonId;
    @NotBlank
    private final String naam;
    @NotBlank
    private final String straat;
    @NotBlank
    private final String huisNr;
    @Min(1000)
    @Max(9999)
    private final short postcode;
    @NotBlank
    private final String gemeente;
    private final TreeSet<@Valid BestelbonLijn> bestelbonLijnen;
    public Bestelbon(long bestelBonId, String naam, String straat, String huisNr, short postcode, String gemeente, TreeSet<BestelbonLijn> bestelbonLijnen) {
        this.bestelBonId = bestelBonId;
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.bestelbonLijnen = bestelbonLijnen;
    }
    public long getBestelBonId() {
        return bestelBonId;
    }
    public String getNaam() {
        return naam;
    }
    public String getStraat() {
        return straat;
    }
    public String getHuisNr() {
        return huisNr;
    }
    public int getPostcode() {
        return postcode;
    }
    public String getGemeente() {
        return gemeente;
    }
    public TreeSet<BestelbonLijn> getBestelbonLijnen() {
        return bestelbonLijnen;
    }
}
