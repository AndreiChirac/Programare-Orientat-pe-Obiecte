package entitiesoutput;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.EnergyType;

import java.util.ArrayList;

public class ProducersOutput extends EntitiesOutput {
    /**
     * id-ul unui producator.
     */
    private final int id;
    /**
     * numarul maxim de distribuitori carora producatorul poate oferi energie.
     */
    private final int maxDistributors;
    /**
     * pretul pe KW al unui producator.
     */
    private final double priceKW;
    /**
     * tipul de energie pe care il furnizeaza un producator.
     */
    private final EnergyType energyType;
    /**
     * energia maxima pe care o poate furniza un producator fiecarui
     * distibuitor.
     */
    private final int energyPerDistributor;
    /**
     * statistici luanre.
     */
    private final ArrayList<MonthlyStats> monthlyStats;

    /**
     * @param idData                   id-ul unui producator
     * @param maxDistributorsData      numarul maxim de distribuitori carora
     *                                 producatorul poate oferi energie
     * @param priceKWData              pretul pe KW al unui producator
     * @param energyTypeData           tipul de energie pe care il furnizeaza un
     *                                 producator
     * @param energyPerDistributorData energia maxima pe care o poate furniza
     *                                 un producator fiecarui distibuitor
     * @param monthlyStatsData         statistici luanre
     */
    public ProducersOutput(final int idData, final int maxDistributorsData,
                           final double priceKWData,
                           final EnergyType energyTypeData,
                           final int energyPerDistributorData,
                           final ArrayList<MonthlyStats> monthlyStatsData) {
        this.id = idData;
        this.maxDistributors = maxDistributorsData;
        this.priceKW = priceKWData;
        this.energyType = energyTypeData;
        this.energyPerDistributor = energyPerDistributorData;
        this.monthlyStats = monthlyStatsData;
    }

    /**
     * @return id-ul unui producator
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @return in cazul Producatorilor acest camp nu este necesar
     */
    @JsonIgnore
    @Override
    public boolean getIsBankrupt() {
        return false;
    }

    /**
     * @return in cazul Producatorilor acest camp nu este necesar
     */
    @JsonIgnore
    @Override
    public int getBudget() {
        return 0;
    }

    /**
     * @return numarul maxim de distribuitori carora producatorul poate oferi
     * energie
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * @return pretul pe KW al unui producator
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     * @return tipul de energie pe care il furnizeaza un producator
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * @return energia maxima pe care o poate furniza un producator fiecarui
     * distibuitor.
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * @return statistici luanre
     */
    public ArrayList<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }
}
