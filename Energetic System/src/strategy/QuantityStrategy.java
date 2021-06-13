package strategy;

import complexentities.Distibutors;
import complexentities.Producers;

import java.util.ArrayList;
import java.util.Collections;

public class QuantityStrategy implements Strategy {
    /**
     * @param producers  lista cu producatorii din care trebuie sa se aleaga
     * @param distibutor distibuitorul a supra caruia se realizeaza operatia
     *                   de alegerea a producatorului
     */
    @Override
    public void chooseEnergy(
            final ArrayList<Producers> producers,
            final Distibutors distibutor) {
        Collections.sort(producers, Producers.onlyQuantity());
        Strategy.producerChoosingAndCostCalculator(producers, distibutor);
    }
}
