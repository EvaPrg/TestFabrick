package it.fabrick.appFabrick.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bonifico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String receiverName;
    private String description;
    private String currency;
    private String amount;
    private String executionDate;
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "conto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_conto_id"))
   private List<Bonifico> bonifici = new ArrayList<>();
    private Conto conto;



   public Bonifico(){

   }

    public Bonifico(String receiverName, String description, String currency, String amount, String executionDate, Conto conto) {
        this.receiverName = receiverName;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.executionDate = executionDate;
        this.conto = conto;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }

    @Override
    public String toString() {
        return "Bonifico{" +
                "receiverName='" + receiverName + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", executionDate='" + executionDate + '\'' +
                ", conto=" + conto +
                '}';
    }
}
