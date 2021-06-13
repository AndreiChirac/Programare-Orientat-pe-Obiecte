package complexentities;

import strategies.EnergyChoiceStrategyType;
import strategy.GreenStrategy;
import strategy.PriceStrategy;
import strategy.QuantityStrategy;
import strategy.Strategy;

import java.util.ArrayList;
import java.util.Comparator;

public class Distibutors extends Entities {

    /**
     * costul unui contract.
     */
    private int contractCost;
    /**
     * durata unui contract.
     */
    private int contractLength;
    /**
     * costul initial pentru realizarea infrastructurii.
     */
    private int initialInfrastructureCost;
    /**
     * costul initial de producite.
     */
    private int initialProductionCost;
    /**
     * profitul unui consumaotr.
     */
    private long profit;
    /**
     * cantitatea de energie lunara de care distibuitorul are nevoie.
     */
    private int energyNeededKW;
    /**
     * strategia distibuitorului de a alege sursa de energie.
     */
    private EnergyChoiceStrategyType producerStrategy;
    /**
     * strategia unui distribuitor.
     */
    private Strategy strategyType;
    /**
     * lista cu producatorii care furnizeaza energie distribuitorului.
     */
    private ArrayList<Producers> producers = new ArrayList<>();

    /**
     * @param idData                        id-ul unui distribuitor
     * @param initialBudgetData             bugetul initial al unui distribuitor
     * @param contractLengthData            durata unui contract
     * @param initialInfrastructureCostData costul initial pentru realizarea
     *                                      infrastructurii
     * @param energyNeededKWData            cantitatea de energie lunara de care
     *                                      distibuitorul are nevoie
     * @param producerStrategyData          strategia distibuitorului de a
     *                                      alege sursa
     *                                      de energie
     */
    public Distibutors(final int idData, final int initialBudgetData,
                       final int contractLengthData,
                       final int initialInfrastructureCostData,
                       final int energyNeededKWData,
                       final EnergyChoiceStrategyType producerStrategyData) {
        super(idData, initialBudgetData);
        this.contractLength = contractLengthData;
        this.initialInfrastructureCost = initialInfrastructureCostData;
        this.energyNeededKW = energyNeededKWData;
        this.producerStrategy = producerStrategyData;
        if (this.producerStrategy.equals(EnergyChoiceStrategyType.GREEN)) {
            this.strategyType = new GreenStrategy();
        } else if (this.producerStrategy
                .equals(EnergyChoiceStrategyType.PRICE)) {
            this.strategyType = new PriceStrategy();
        } else {
            this.strategyType = new QuantityStrategy();
        }
    }

    /**
     * sortare a distibuitorilor dupa id.
     */
    public static Comparator<Distibutors> id() {
        return new Comparator<>() {
            @Override
            public int compare(final Distibutors o1, final Distibutors o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
    }

    /**
     * @return costul unui contract
     */
    public int getContractCost() {
        return contractCost;
    }

    /**
     * @param contractCostData costul unui contract
     */
    public void setContractCost(final int contractCostData) {
        this.contractCost = contractCostData;
    }

    /**
     * @return durata unui contract
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     * @param contractLengthData durata unui contract
     */
    public void setContractLength(final int contractLengthData) {
        this.contractLength = contractLengthData;
    }

    /**
     * @return costul initial pentru realizarea infrastructurii
     */
    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * @param initialInfrastructureCostData costul initial pentru
     *                                      realizarea infrastructurii
     */
    public void setInitialInfrastructureCost(
            final int initialInfrastructureCostData) {
        this.initialInfrastructureCost = initialInfrastructureCostData;
    }

    /**
     * @param initialProductionCostData costul initial de producite
     */
    public void setInitialProductionCost(final int initialProductionCostData) {
        this.initialProductionCost = initialProductionCostData;
    }

    /**
     * @param profitData PROFIT
     */
    public void setProfit(final long profitData) {
        this.profit = profitData;
    }

    /**
     * @return energia necesara distribuitorului.
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * @param energyNeededKWData energia necesara distribuitorului.
     */
    public void setEnergyNeededKW(final int energyNeededKWData) {
        this.energyNeededKW = energyNeededKWData;
    }

    /**
     * @return tipul de energie dorita , prioritizata de distibuitor
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    /**
     * @param producerStrategyData tipul de energie dorita , prioritizata de
     *                             distibuitor
     */
    public void setProducerStrategy(
            final EnergyChoiceStrategyType producerStrategyData) {
        this.producerStrategy = producerStrategyData;
    }

    /**
     * @return tipul de strategie dupa care se aleg producatorii
     */
    public Strategy getStrategyType() {
        return strategyType;
    }

    /**
     * @return lista cu producatorii care furnizeaza energie
     */
    public ArrayList<Producers> getProducers() {
        return producers;
    }

    /**
     * @param producersData lista cu producatorii care furnizeaza energie
     */
    public void setProducers(final ArrayList<Producers> producersData) {
        this.producers = producersData;
    }

    /**
     * calculeaza costul unui contract.
     */
    public void costContractCalculator() {
        profitcalculator();
        if (super.getContracts().isEmpty()) {
            this.setContractCost((int) (this.initialInfrastructureCost
                    + this.initialProductionCost + this.profit));
        } else {
            this.setContractCost((int) Math.round(Math
                    .floor(this.initialInfrastructureCost
                            / super.getContracts().size())
                    + this.initialProductionCost + this.profit));
        }
    }

    /**
     * calculeaza profitul unui distibuitor.
     */
    public void profitcalculator() {
        final double constantNumber = 0.2;
        this.setProfit(
                Math.round(Math.floor(
                        constantNumber * this.initialProductionCost)));
    }

    /**
     * Se calculeaza bugetul.
     * In cazul in care distibuitorul nu este in faliment , se fa calcula
     * pretul pe care acesta trebuie sa-l plateasca pentru a genera serviciile
     * daca acestea depasesc bugetul acesta va intra in faliment .
     * Falimentarea acestuia va consta in desfintarea tuturor contractelor
     * utilizatorilor , schimbarea statutului unui utilizator daca acesta
     * are contract si se afla in datorie si ilaturarea tuturor contractelor
     * din distibuitor.
     *
     * @param consumers lista de consumatori in cazul de faliment
     * @param producersData lista cu producatorii in cazul unui faliment se
     *                      va scoate distribuitroul din lista din producator
     */
    public void recalulculateBudget(final ArrayList<Consumers> consumers,
                                    final ArrayList<Producers> producersData) {
        if (!super.isBankrupt()) {
            int budget = super.getBudget();
            int cost =
                    this.initialInfrastructureCost
                            + (this.initialProductionCost
                            * super.getContracts().size());
            if (budget - cost < 0) {
                super.setBankrupt(true);
                super.setBudget(super.getBudget() - cost);

                removeFromConsumers(consumers); //se scot contractele si din
                // lista de consumaotri
                chageConsumerStatus(consumers); //daca distibuitorul era
                // dator sa il faca sa nu mai fie dator
                removeFromProducer(producersData); // se scot contractele din
                // lista producatorului daca distibuitorul a dat faliment
                super.getContracts().clear(); //sa se scoata toate contractele
                this.getProducers().clear(); //se scot toti producatorii

            } else {
                super.setBudget(super.getBudget() - cost);
            }
        }
    }

    /**
     * Scoate contractele din utilizatori.
     *
     * @param consumers lista cu consumaotri
     */
    public void removeFromConsumers(final ArrayList<Consumers> consumers) {
        for (int i = 0; i < super.getContracts().size(); i++) {
            for (Consumers consumer : consumers) {
                if (super.getContracts().get(i).getConsumerId()
                        == consumer.getId()) {
                    super.getContracts().remove(super.getContracts().get(i));
                }
            }
        }

    }

    /**
     * functie care elimina din lista producatorilor toti distibuitorii
     *
     * @param producersData lista cu toti producatorii
     */
    public void removeFromProducer(final ArrayList<Producers> producersData) {
        for (int i = 0; i < this.getProducers().size(); i++) {
            for (int j = 0; j < producersData.size(); j++) {
                if (this.getProducers().get(i).getId()
                        == producersData.get(j).getId()) {
                    if (producersData.get(j).getDistibutors().contains(this)) {
                        producersData.get(j).getDistibutors().remove(this);
                    }
                }
            }
        }
    }


    /**
     * in cazul in care utilizatorul este cu datorii , iar distibuitorul da
     * faliment utilizatorul scapa de datorie.
     *
     * @param consumers lista cu consumatori
     */
    public void chageConsumerStatus(final ArrayList<Consumers> consumers) {
        for (int i = 0; i < super.getContracts().size(); i++) {
            for (Consumers consumer : consumers) {
                if (super.getContracts().get(i).getConsumerId()
                        == consumer.getId()) {
                    consumer.setInDept(false);
                }
            }
        }
    }
}
