package actions;

import complexentities.Consumers;
import complexentities.Distibutors;
import complexentities.Factory;
import complexentities.Producers;
import entitiesoutput.EntitiesOutput;
import parsing.ConsumersDataInput;
import parsing.DistributorChangesDataInput;
import parsing.Input;

import java.util.ArrayList;

public class Actions {

    /**
     * @param distibutors lista cu distribuitorii
     */
    public void updateContractCostsDistibutors(
            final ArrayList<Distibutors> distibutors) {
        for (Distibutors distibutor : distibutors) {
            distibutor.costContractCalculator();
        }
    }

    /**
     * @param distibutors lista cu distibuitorii
     * @param consumers   lista cu consumatorii
     * @param producers   lista cu producatorii
     */
    public void updatePayDayDistibutors(
            final ArrayList<Distibutors> distibutors,
            final ArrayList<Consumers> consumers,
            final ArrayList<Producers> producers) {
        for (Distibutors distibutor : distibutors) {
            distibutor.recalulculateBudget(consumers, producers);
        }
    }

    /**
     * hasSufferdModifications care pe baza updateurilor aflu daca un
     * producator a suferit o schimbare sau nu pentru a sti daca
     * distribuitorii care erau abonati la producatroii respectivi sa stie sa
     * si caute noii producatori.
     * @param distibutor distribuitorul care vrea sa stie daca producatorii
     *                   lui au sufeit modificari
     * @param input updateurile
     * @param month luna in care ne aflam pentru a verifica upadturile
     * @return daca s-a realizat o modificare v-a returna true in caz contrar
     * false
     */
    public boolean hasSufferdModifications(
            final Distibutors distibutor,
            final Input input,
            final int month) {
        for (int i = 0; i
                < input.getMonthlyUpdates().get(month - 1).getProducerChanges()
                .size(); i++) {
            for (int j = 0; j < distibutor.getProducers().size(); j++) {
                if (input.getMonthlyUpdates().get(month - 1)
                        .getProducerChanges()
                        .get(i).getId()
                        == distibutor.getProducers().get(j).getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param output ogera entitatile de output nesortate
     */
    public void sortOutput(final ArrayList<EntitiesOutput> output) {
        EntitiesOutput aux;
        for (int i = 0; i < output.size(); i++) {
            for (int j = 0; j < output.size(); j++) {
                if (output.get(i).getId() < output.get(j).getId()) {
                    aux = output.get(i);
                    output.set(i, output.get(j));
                    output.set(j, aux);
                }
            }
        }
    }

    /**
     * @param consumers    lista cu consumatorii
     * @param newconsumers consumatoii noi care trebuie adaugati
     */
    public void addCustomers(final ArrayList<Consumers> consumers,
                             final ArrayList<ConsumersDataInput> newconsumers) {
        for (ConsumersDataInput newconsumer : newconsumers) {
            Consumers conusmer =
                    (Consumers) Factory.createEntity(newconsumer.getId(),
                            newconsumer.getInitialBudget(),
                            newconsumer.getMonthlyIncome(), 0, null, 0, 0, 0, 0,
                            0,
                            null, "Consumers");
            consumers.add(conusmer);
        }

    }

    /**
     * @param distibutors        distibutorii
     * @param distibutorsChanges schimbarile care trebuie efectuate asupra
     *                           distibuitorilor
     */
    public void costChangesDistibutor(
            final ArrayList<Distibutors> distibutors,
            final ArrayList<DistributorChangesDataInput> distibutorsChanges) {
        for (DistributorChangesDataInput distibutorsChange
                : distibutorsChanges) {
            for (Distibutors distibutor : distibutors) {
                if (distibutor.getId()
                        == distibutorsChange.getId()) {
                    distibutor.setInitialInfrastructureCost(
                            distibutorsChange.getInfrastructureCost());
                }
            }
        }
    }

    /**
     * @param input     inputul
     * @param consumers list goala cu consumaotrii
     */
    public void createConsumers(final Input input,
                                final ArrayList<Consumers> consumers) {
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            Consumers conusmer =
                    (Consumers) Factory.createEntity(
                            input.getInitialData().getConsumers().get(i)
                                    .getId(),
                            input.getInitialData().getConsumers().get(i)
                                    .getInitialBudget(),
                            input.getInitialData().getConsumers().get(i)
                                    .getMonthlyIncome(), 0,
                            null, 0, 0, 0, 0,
                            0, null,
                            "Consumers");
            consumers.add(conusmer);
        }
    }

    /**
     * @param input       inputul
     * @param distibutors lista cu distribuitori goala
     */
    public void createDistibutors(final Input input,
                                  final ArrayList<Distibutors> distibutors) {

        for (int i = 0; i < input.getInitialData().getDistributors().size();
             i++) {
            Distibutors distibutor =
                    (Distibutors) Factory.createEntity(
                            input.getInitialData().getDistributors().get(i)
                                    .getId(),
                            input.getInitialData().getDistributors().get(i)
                                    .getInitialBudget(),
                            0,
                            input.getInitialData().getDistributors().get(i)
                                    .getContractLength(),
                            null,
                            input.getInitialData().getDistributors().get(i)
                                    .getInitialInfrastructureCost(),
                            0,
                            input.getInitialData().getDistributors().get(i)
                                    .getEnergyNeededKW(),
                            0, 0,
                            input.getInitialData().getDistributors().get(i)
                                    .getProducerStrategy(),
                            "Distirbutors");
            distibutor.profitcalculator();
            distibutor.costContractCalculator();
            distibutors.add(distibutor);
        }
    }

    /**
     * @param input     inputul
     * @param producers lista cu producatori goala
     */
    public void createProducers(final Input input,
                                final ArrayList<Producers> producers) {
        for (int i = 0; i < input.getInitialData().getProducers().size();
             i++) {
            Producers producer =
                    (Producers) Factory.createEntity(
                            input.getInitialData().getProducers().get(i)
                                    .getId(), 0, 0,
                            0,
                            input.getInitialData().getProducers().get(i)
                                    .getEnergyType(), 0,
                            input.getInitialData().getProducers().get(i)
                                    .getMaxDistributors(), 0,
                            input.getInitialData().getProducers().get(i)
                                    .getPriceKW(),
                            input.getInitialData().getProducers().get(i)
                                    .getEnergyPerDistributor(), null,
                            "Producers");

            producers.add(producer);
        }
    }


}
