package complexentities;

import entities.EnergyType;
import strategies.EnergyChoiceStrategyType;

public class Factory {
    /**
     * @param id id-ul unei entitati
     * @param buget bugetul unei entitati
     * @param monthlyincome venitul lunar al unei entitati
     * @param contractLength lungimea unui contract al unei entitati
     * @param energyType tipul de energie a unei entitati
     * @param inftastructureCost costul infrastructurii a unei entitati
     * @param maxDistributors numarul maxim de distibuitori
     * @param energyNeededKW energia necesitata a unei entitati
     * @param priceKW pretul energiei
     * @param energyPerDistributor energia pe care o poate primi un distirbuitor
     * @param producerStrategy strategia unui producator
     * @param type tipul de entitate
     * @return
     */
    public static Entities createEntity(
            final int id, final int buget,
            final int monthlyincome,
            final int contractLength,
            final EnergyType energyType,
            final int inftastructureCost,
            final int maxDistributors,
            final int energyNeededKW,
            final double priceKW,
            final int energyPerDistributor,
            final EnergyChoiceStrategyType producerStrategy,
            final String type) {
        if (type.equals("Consumers")) {
            return new Consumers(id, buget, monthlyincome);
        } else if (type.equals("Distirbutors")) {
            return new Distibutors(id,
                    buget, contractLength, inftastructureCost,
                    energyNeededKW, producerStrategy);
        } else {
            return new Producers(id, energyType, maxDistributors, priceKW,
                    energyPerDistributor);
        }
    }
}
