package be.vdab.bierhuis.domain;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Brouwer {
    private long brouwerId;
    @NotBlank
    private String naam;
    @Valid
    private Adres adres;
    private BigDecimal omzet;
    public Brouwer(long id, String naam, Adres adres, BigDecimal omzet) {
        this.brouwerId = id;
        this.naam = naam;
        this.adres = adres;
        this.omzet = omzet;
    }
    public long getBrouwerId() {
        return brouwerId;
    }
    public String getNaam() {
        return naam;
    }
    public Adres getAdres() {
        return adres;
    }
    public BigDecimal getOmzet() {
        return omzet;
    }
}
