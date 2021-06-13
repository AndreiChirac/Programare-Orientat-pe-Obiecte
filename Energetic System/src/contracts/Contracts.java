package contracts;

public class Contracts {
    /**
     * id-ul unui consumator.
     */
    private int consumerId;
    /**
     * costul pe care trebuie sa-l plateasca un consumator.
     */
    private int price;
    /**
     * numarul de luni valabile de contract.
     */
    private int remainedContractMonths;

    /**
     * @param consumerIdData             id consumaotr
     * @param priceData                  costul distribuitorului
     * @param remainedContractMonthsData numarul de luni ramase
     */
    public Contracts(
            final int consumerIdData,
            final int priceData,
            final int remainedContractMonthsData) {
        this.consumerId = consumerIdData;
        this.price = priceData;
        this.remainedContractMonths = remainedContractMonthsData;
    }

    /**
     * @return id-ul consumatorului
     */
    public int getConsumerId() {
        return consumerId;
    }

    /**
     * @param consumerIdData id-ul consumatorului
     */
    public void setConsumerId(final int consumerIdData) {
        this.consumerId = consumerIdData;
    }

    /**
     * @return costul contractului
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param priceData costul contractului
     */
    public void setPrice(final int priceData) {
        this.price = priceData;
    }

    /**
     * @return numarul de luni ramase din contract
     */
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * @param remainedContractMonthsData numarul de luni ramase din contract
     */
    public void setRemainedContractMonths(
            final int remainedContractMonthsData) {
        this.remainedContractMonths = remainedContractMonthsData;
    }
}
