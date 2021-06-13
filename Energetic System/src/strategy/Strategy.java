package strategy;

import complexentities.Distibutors;
import complexentities.Producers;

import java.util.ArrayList;

public interface Strategy {
    /**
     * ProducerChoosingAndCostCalculator are roul de a selecta producatorii ,
     * de a-i adauga in listele distribuitorulor , de a aduga in lista
     * producatorului distribuitorul respectiv si de a seta costul initial de
     * productie a distribuitorului .
     * @param producers  lista cu producatorii de energie
     * @param distibutor distribuitorul care trebuie sa aleaga de la care
     *                   producatori va lua energie
     */
    static void producerChoosingAndCostCalculator(
            ArrayList<Producers> producers, Distibutors distibutor) {
        int auxEnergyNeeded = distibutor.getEnergyNeededKW();
        double cost = 0;
        int i = 0;

        while (auxEnergyNeeded > 0 && i < producers.size()) {
            if (producers.get(i).getDistibutors().size()
                    < producers.get(i).getMaxDistributors()) {

                cost = cost + producers.get(i).getEnergyPerDistributor()
                        * producers.get(i).getPriceKW();

                auxEnergyNeeded = auxEnergyNeeded
                        - producers.get(i).getEnergyPerDistributor();

                distibutor.getProducers().add(producers.get(i));
                producers.get(i).getDistibutors().add(distibutor);
            }
            i++;
        }
        distibutor.setInitialProductionCost(
                (int) Math.round(Math.floor(cost / 10)));
    }

    /**
     * @param producers  lista cu producatorii din care trebuie sa se aleaga
     * @param distibutor distibuitorul a supra caruia se realizeaza operatia
     *                   de alegerea a producatorului
     * @return lista sortata dupa timpul de strategie dorita de distibuitor
     */
    void chooseEnergy(ArrayList<Producers> producers,
                      Distibutors distibutor);
}