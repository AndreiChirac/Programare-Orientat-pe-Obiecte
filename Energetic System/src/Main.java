import actions.Actions;
import complexentities.Consumers;
import complexentities.Distibutors;
import complexentities.Producers;
import entitiesoutput.EntitiesOutput;
import entitiesoutput.FactoryOutput;
import entitiesoutput.MonthlyStats;
import observer.ProducerObserver;
import parsing.Input;
import parsing.InputLoader;
import parsing.Output;
import parsing.OutputLoader;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() {
    }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files,
     *                   parsing JSON
     */
    public static void main(String[] args) throws Exception {
        Input input = InputLoader.getInstance().readData(args[0]);
        ArrayList<Consumers> consumers = new ArrayList<>();
        ArrayList<Distibutors> distibutors = new ArrayList<>();
        ArrayList<Producers> producers = new ArrayList<>();
        Actions actions = new Actions();
        actions.createConsumers(input, consumers);
        actions.createDistibutors(input, distibutors);
        actions.createProducers(input, producers);
        Collections.sort(producers, Producers.id());
        Collections.sort(distibutors, Distibutors.id());
        ProducerObserver producerObserver = new ProducerObserver();


        for (Producers item : producers) {
            producerObserver.addObserver(item);
        }

        for (int i = 0; i <= input.getNumberOfTurns(); i++) {
            if (i != 0) {
                actions.costChangesDistibutor(distibutors,
                        input.getMonthlyUpdates().get(i - 1)
                                .getDistributorChanges()); //se aplica cost
                // changes
                actions.addCustomers(consumers,
                        input.getMonthlyUpdates().get(i - 1)
                                .getNewConsumers()); //sa vina conusmatorii noi
            }

            if (i == 0) {
                for (Distibutors distibutor : distibutors) {
                    distibutor.getStrategyType().chooseEnergy(producers,
                            distibutor);
                }
            }

            actions.updateContractCostsDistibutors(
                    distibutors); //se recalculeaza costul unui contract


            for (Consumers value : consumers) {
                if (!value.isHasContract()) {
                    value.removeFromDistributor(distibutors); //in cazul in
                    // care nu mai exista contract utilizatorul trebuie sa
                    // elimine contractul expirat
                }
                value.setContract(distibutors); //aduaga contract
                value.recalulculateBudget(
                        distibutors); //plateste contract/intra in
                // datorie
            } //sa aleaga toata lumea contractele

            actions.updatePayDayDistibutors(distibutors,
                    consumers, producers); //se realizeaza platile pe care
            // trebuie sa le execute distibuitorul

            if (i != 0) {
                for (int k = 0; k < input.getMonthlyUpdates().get(i - 1)
                        .getProducerChanges().size(); k++) {
                    producerObserver.modifier(
                            input.getMonthlyUpdates().get(i - 1)
                                    .getProducerChanges().get(k));
                }
            }

            for (Distibutors distributor : distibutors) {
                if (!distributor.isBankrupt() && i != 0
                        && actions.hasSufferdModifications(distributor, input,
                        i)) {

                    distributor.removeFromProducer(producers);
                    distributor.getProducers().clear();

                    distributor.getStrategyType().chooseEnergy(
                            producers, distributor);
                } //toti distibuitorii isi actualizeaza producatorii
            }

            if (i != 0) {
                for (Producers producer : producers) {
                    ArrayList<Integer> distributorsid = new ArrayList<>();
                    for (int z = 0;
                         z < producer.getDistibutors().size(); z++) {
                        if (!distributorsid.contains(
                                producer.getDistibutors().get(z)
                                        .getId())) {
                            distributorsid.add(producer.getDistibutors()
                                    .get(z).getId());
                        }
                    }
                    Collections.sort(distributorsid);
                    MonthlyStats monthlyStats = new MonthlyStats(i,
                            distributorsid);

                    producer.getMonthlyStats().add(monthlyStats);

                }
            }

            for (Consumers consumer : consumers) {
                if (consumer.isBankrupt()) {
                    consumer.removeFromDistributor(distibutors);
                }
            }
        }

        ArrayList<EntitiesOutput> consumersOutput = new ArrayList<>();

        for (Consumers consumer : consumers) {
            consumersOutput.add(
                    FactoryOutput.createOutputEntity(consumer));
        }

        ArrayList<EntitiesOutput> distributorsOutput = new ArrayList<>();

        for (Distibutors distibutor : distibutors) {
            distributorsOutput.add(
                    FactoryOutput.createOutputEntity(distibutor));
        }
        actions.sortOutput(distributorsOutput);

        ArrayList<EntitiesOutput> energyProducersOutput = new ArrayList<>();
        for (Producers producer : producers) {
            energyProducersOutput
                    .add(FactoryOutput.createOutputEntity(producer));
        }
        actions.sortOutput(energyProducersOutput);

        Output output = new Output(consumersOutput, distributorsOutput,
                energyProducersOutput);

        //String outhelper = args[0];
        //String result = outhelper.replaceAll("in", "out");
        //OutputLoader.getInstance().putData(result, output);
        OutputLoader.getInstance().putData(args[1], output);
    }
}
