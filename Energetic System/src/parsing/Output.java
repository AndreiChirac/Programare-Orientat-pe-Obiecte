package parsing;

import entitiesoutput.EntitiesOutput;

import java.util.ArrayList;

public class Output {
    /**
     * Lista cu structura finala a unui consumator.
     */
    private ArrayList<EntitiesOutput> consumers;
    /**
     * Lista de distibuitori in starea lor finala dupa rundele de simulare.
     */
    private ArrayList<EntitiesOutput> distributors;
    /**
     * Lista de producatori in starea lor finala dupa rundele de simualre.
     */
    private ArrayList<EntitiesOutput> energyProducers;

    /**
     * @param consumersData       Lista de consumaotri in starea lor finala dupa
     *                            rundele de simulare
     * @param distributorsData    Lista de distibuitori in starea lor finala
     *                            dupa rundele de simulare
     * @param energyProducersData Lista de producatori in starea lor finala
     *                            dupa rundele de simulare
     */
    public Output(final ArrayList<EntitiesOutput> consumersData,
                  final ArrayList<EntitiesOutput> distributorsData,
                  final ArrayList<EntitiesOutput> energyProducersData) {
        this.consumers = consumersData;
        this.distributors = distributorsData;
        this.energyProducers = energyProducersData;
    }

    /**
     * @return lista cu consumatorii
     */
    public ArrayList<EntitiesOutput> getConsumers() {
        return consumers;
    }

    /**
     * @return lista cu distribuitorii
     */
    public ArrayList<EntitiesOutput> getDistributors() {
        return distributors;
    }

    /**
     * @return lista cu producatorii
     */
    public ArrayList<EntitiesOutput> getEnergyProducers() {
        return energyProducers;
    }
}
