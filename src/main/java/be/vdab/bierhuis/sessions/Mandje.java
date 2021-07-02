package be.vdab.bierhuis.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@SessionScope
//Je kan eigenlijk een domain class maken voor mand
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final HashMap<Long, Long> idsCount = new HashMap<Long, Long>();
    //Programeer naar interface private final map = new HAshmap
    public boolean mandjeIsLeeg(){
        return idsCount.isEmpty();
    }
    public void maakLeeg() {
       idsCount.clear();
    }
    public void voegToe(long id,@NotBlank @Positive long aantal) {
        if (idsCount.containsKey(id)) {
            idsCount.put(id, aantal+idsCount.get(id));
        }
        else{
        idsCount.put(id,aantal);}
    }
    public HashMap<Long,Long> getIdsCount() {
        return idsCount;
    }
}
