package parsing;

import java.util.ArrayList;

public class MonthlySingleDataInput {
    /**
     * lista cu consumatorii noi.
     */
    private ArrayList<ConsumersDataInput> newConsumers;
    /**
     * lista cu noile modificari care trebuie aplicate distribuitorului.
     */
    private ArrayList<DistributorChangesDataInput> distributorChanges;
    /**
     * lista cu noile modificari care trebuie aplicate producatorului.
     */
    private ArrayList<ProducerChangesDataInput> producerChanges;

    /**
     * @return lista cu consumatorii noi
     */
    public ArrayList<ConsumersDataInput> getNewConsumers() {
        return newConsumers;
    }

    /**
     * @param newConsumersData lista cu consumatorii noi
     */
    public void setNewConsumers(
            final ArrayList<ConsumersDataInput> newConsumersData) {
        this.newConsumers = newConsumersData;
    }

    /**
     * @return lista cu noile modificari care trebuie aplicate distribuitorului
     */
    public ArrayList<DistributorChangesDataInput> getDistributorChanges() {
        return distributorChanges;
    }

    /**
     * @param costsChangesData lista cu noile modificari care trebuie
     *                         aplicate distribuitorului
     */
    public void setDistributorChanges(
            final ArrayList<DistributorChangesDataInput> costsChangesData) {
        this.distributorChanges = costsChangesData;
    }

    /**
     * @return lista cu noile modificari care trebuie aplicate producatorului
     */
    public ArrayList<ProducerChangesDataInput> getProducerChanges() {
        return producerChanges;
    }

    /**
     * @param producerChangesData lista cu noile modificari care trebuie
     *                            aplicate producatorului
     */
    public void setProducerChanges(
            final ArrayList<ProducerChangesDataInput> producerChangesData) {
        this.producerChanges = producerChangesData;
    }
}
