package complexentities;

import changes.ProducerChanges;
import entities.EnergyType;
import entitiesoutput.MonthlyStats;
import parsing.ProducerChangesDataInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

public class Producers extends Entities implements Observer {
    /**
     * statistici luanre.
     */
    private final ArrayList<MonthlyStats> monthlyStats = new ArrayList<>();
    /**
     * tipul de energie a unui producator.
     */
    private EnergyType energyType;
    /**
     * numărul maxim de distribuitori către care poate oferi energie.
     */
    private int maxDistributors;
    /**
     * pretul pe KWh a unui producator.
     */
    private double priceKW;
    /**
     * cantitatea lunară de energie oferită fiecărui distribuitor.
     */
    private int energyPerDistributor;
    /**
     * lista cu distribuitorii caruia producatorul ii funrizeaza energie.
     */
    private ArrayList<Distibutors> distibutors = new ArrayList<>();

    /**
     * @param idData                   ID-ul unui producator
     * @param energyTypeData           tipul de energie a unui producator
     * @param maxDistributorsData      numărul maxim de distribuitori către care
     *                                 poate oferi energie
     * @param priceKWData              pretul pe KWh a unui producator
     * @param energyPerDistributorData cantitatea lunară de energie oferită
     *                                 fiecărui distribuitor
     */
    public Producers(final int idData, final EnergyType energyTypeData,
                     final int maxDistributorsData,
                     final double priceKWData,
                     final int energyPerDistributorData) {
        super(idData, 0);
        this.energyType = energyTypeData;
        this.maxDistributors = maxDistributorsData;
        this.priceKW = priceKWData;
        this.energyPerDistributor = energyPerDistributorData;
    }

    /**
     * Sortarea producatorilor se realzieaza dupa faptul ca sursa folosita de
     * producator este regenerabila sau nu ,  dupa  pretul KWh si dupa
     * cantitatea de energie in cazul in care campurile coincid sortarea se
     * realizeaza dupa id.
     */
    public static Comparator<Producers> energyPriceQuantity() {
        return new Comparator<Producers>() {
            @Override
            public int compare(final Producers o1, final Producers o2) {

                if (Boolean.compare(o1.energyType.isRenewable(),
                        o2.energyType.isRenewable()) == 0) {
                    if (o1.priceKW == o2.priceKW) {
                        if (o1.energyPerDistributor
                                == o2.energyPerDistributor) {
                            return Integer.compare(o1.getId(), o2.getId());
                        } else {
                            return Integer.compare(o2.energyPerDistributor,
                                    o1.energyPerDistributor);
                        }
                    }
                    return Double.compare(o1.priceKW, o2.priceKW);
                }
                if (Boolean.compare(o1.getEnergyType().isRenewable(),
                        o2.getEnergyType().isRenewable()) == 1) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
    }

    /**
     * Sortarea producatorilor se realzieaza dupa pretul KWh si dupa cantitatea
     * de energie in cazul in care campurile coincid sortarea se realizeaza
     * dupa id.
     */
    public static Comparator<Producers> onlyPriceQuantity() {
        return new Comparator<Producers>() {
            @Override
            public int compare(final Producers o1, final Producers o2) {
                if (o1.priceKW == o2.priceKW) {
                    if (o1.energyPerDistributor == o2.energyPerDistributor) {
                        return Integer.compare(o1.getId(), o2.getId());
                    } else {
                        return Integer.compare(o2.energyPerDistributor,
                                o1.energyPerDistributor);
                    }
                }
                return Double.compare(o1.priceKW, o2.priceKW);
            }
        };
    }

    /**
     * Sortarea producatorilor se realzieaza dupa cantitatea de energie in
     * cazul in care campul coincide sortarea se realizeaza dupa id.
     */
    public static Comparator<Producers> onlyQuantity() {
        return new Comparator<Producers>() {
            @Override
            public int compare(final Producers o1, final Producers o2) {
                if (o1.energyPerDistributor == o2.energyPerDistributor) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
                return Integer.compare(o2.energyPerDistributor,
                        o1.energyPerDistributor);
            }
        };
    }

    /**
     * Sortarea producatorilor se realizeaza dupa id-urile acestora.
     */
    public static Comparator<Producers> id() {
        return new Comparator<Producers>() {
            @Override
            public int compare(final Producers o1, final Producers o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
    }

    /**
     * @return tipul de energie a unui producator
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * @param energyTypeData tipul de energie a unui producator
     */
    public void setEnergyType(final EnergyType energyTypeData) {
        this.energyType = energyTypeData;
    }

    /**
     * @return numarul maxim de Distibuitori admisi de catre un producator
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * @param maxDistributorsData numarul maxim de Distibuitori admisi de
     *                            catre un producator
     */
    public void setMaxDistributors(final int maxDistributorsData) {
        this.maxDistributors = maxDistributorsData;
    }

    /**
     * @return pretul cerut de catre producator
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     * @param priceKWData pretul cerut de catre producator
     */
    public void setPriceKW(final double priceKWData) {
        this.priceKW = priceKWData;
    }

    /**
     * @return energia maxima pe care o poate oferi un producator unui
     * distribuitor
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * @param energyPerDistributorData energia maxima pe care o poate oferi
     *                                 un producator unui distribuitor
     */
    public void setEnergyPerDistributor(final int energyPerDistributorData) {
        this.energyPerDistributor = energyPerDistributorData;
    }

    /**
     * @return lista cu distibutorii caruia producatorul oferta energie
     */
    public ArrayList<Distibutors> getDistibutors() {
        return distibutors;
    }

    /**
     * @param distibutorsData lista cu distribuitorii caruia producatorul
     *                        oferta energie
     */
    public void setDistibutors(
            final ArrayList<Distibutors> distibutorsData) {
        this.distibutors = distibutorsData;
    }

    /**
     * @return statusurile lunare ale unui producator.
     */
    public ArrayList<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * @param o
     * @param arg
     * Daca id-ul updateului corespunde cu id-ul producatorului atunci se
     * realizeaza updateul.
     */
    @Override
    public void update(final Observable o, final Object arg) {
        ProducerChanges producerChanges = new ProducerChanges();
        ProducerChangesDataInput helperProducer =
                (ProducerChangesDataInput) arg;
        producerChanges.setId(helperProducer.getId());
        producerChanges.setEnergyPerDistributor(
                helperProducer.getEnergyPerDistributor());
        if (producerChanges.getId() == this.getId()) {
            this.setEnergyPerDistributor(
                    producerChanges.getEnergyPerDistributor());
        }
    }

}
