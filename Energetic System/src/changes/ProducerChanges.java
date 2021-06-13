package changes;

public class ProducerChanges {
    /**
     * ID-ul unui producator.
     */
    private int id;
    /**
     * capacitatea maxima de distibutie a unui producator per distribuitor.
     */
    private int energyPerDistributor;

    /**
     * @return ID-ul unui producator
     */
    public int getId() {
        return id;
    }

    /**
     * @param idData ID-ul unui producator
     */
    public void setId(final int idData) {
        this.id = idData;
    }

    /**
     * @return capacitatea maxima de distibutie a unui producator per
     * distribuitor
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * @param energyPerDistributorData capacitatea maxima de distibutie a
     *                                 unui producator per distribuitor
     */
    public void setEnergyPerDistributor(final int energyPerDistributorData) {
        this.energyPerDistributor = energyPerDistributorData;
    }
}
