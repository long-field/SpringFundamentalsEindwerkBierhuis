package be.vdab.bierhuis.domain;
import org.springframework.format.annotation.NumberFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
//bestelbonlijn op zich niet nodig Dit is eerder een manditem want bestelbonlijn maakt deel uit van bestelbon
public class BestelbonLijn implements Comparable<BestelbonLijn>{
    private final long bierId;
    private final String naam;
    @NotBlank
    @Positive
    private final long aantal;
    @NumberFormat(pattern = "0.00")
    private final BigDecimal prijs;
    public BestelbonLijn(long bierId, String naam, long aantal, BigDecimal prijs) {
        this.bierId = bierId;
        this.naam = naam;
        this.aantal = aantal;
        this.prijs = prijs;
    }
    public long getBierId() {
        return bierId;
    }
    public long getAantal() {
        return aantal;
    }
    public BigDecimal getPrijs() {
        return prijs;
    }
    public String getNaam() {
        return naam;
    }
    public BigDecimal getBestelbonLijnPrijs(){
        return getPrijs().multiply(BigDecimal.valueOf(getAantal()));
    }
    @Override
    public int compareTo(BestelbonLijn o) {
        if(getBierId() > o.getBierId()){
            return 1;
        } else {
            return -1;
        }
    }
}
