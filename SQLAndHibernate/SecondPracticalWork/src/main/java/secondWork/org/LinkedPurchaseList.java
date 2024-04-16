package secondWork.org;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "linkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseKey id;

    public void setId(LinkedPurchaseKey id) {
        this.id = id;
    }

    public LinkedPurchaseKey getId() {
        return id;
    }
}

