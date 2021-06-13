package entitiesoutput;

import complexentities.Consumers;
import complexentities.Distibutors;
import complexentities.Entities;
import complexentities.Producers;

public class FactoryOutput {
    /**
     * @param entity tipul de entitate
     * @return entitatea care va fi pusa in output
     */
    public static EntitiesOutput createOutputEntity(final Entities entity) {
        if (entity instanceof Consumers) {
            return new ConsumersOutput(entity.getId(), entity.isBankrupt(),
                    entity.getBudget());
        } else if (entity instanceof Distibutors) {
            return new DistibutorsOutput(entity.getId(),
                    ((Distibutors) entity).getEnergyNeededKW(),
                    ((Distibutors) entity).getContractCost(),
                    ((Distibutors) entity).getProducerStrategy(),
                    entity.isBankrupt(),
                    entity.getBudget(), entity.getContracts());
        } else if (entity instanceof Producers) {
            return new ProducersOutput(entity.getId(),
                    ((Producers) entity).getMaxDistributors(),
                    ((Producers) entity).getPriceKW(),
                    ((Producers) entity).getEnergyType(),
                    ((Producers) entity).getEnergyPerDistributor(),
                    ((Producers) entity).getMonthlyStats());
        }
        return null;
    }
}
