package parsing;

import java.util.ArrayList;

public class InitialDataInput {
    /**
     * lista de consumatori.
     */
    private ArrayList<ConsumersDataInput> consumers;
    /**
     * lista cu distribuitori.
     */
    private ArrayList<DistributorsDataInput> distributors;
    /**
     * lista cu producatori.
     */
    private ArrayList<ProducersDataInput> producers;

    /**
     * @return lista de consumatori.
     */
    public ArrayList<ConsumersDataInput> getConsumers() {
        return consumers;
    }

    /**
     * @param consumersData lista de consumatori.
     */
    public void setConsumers(
            final ArrayList<ConsumersDataInput> consumersData) {
        this.consumers = consumersData;
    }

    /**
     * @return lista cu distribuitori
     */
    public ArrayList<DistributorsDataInput> getDistributors() {
        return distributors;
    }

    /**
     * @param distributorsData lista cu distribuitorii
     */
    public void setDistributors(
            final ArrayList<DistributorsDataInput> distributorsData) {
        this.distributors = distributorsData;
    }

    /**
     * @return lista cu producatori
     */
    public ArrayList<ProducersDataInput> getProducers() {
        return producers;
    }

    /**
     * @param producersData lista cu producatori
     */
    public void setProducers(
            final ArrayList<ProducersDataInput> producersData) {
        this.producers = producersData;
    }

}
