package changes;

public class DistributorChanges {
    /**
     * ID-ul unui distribuitor.
     */
    private int id;
    /**
     * costul actualizat pentru realizarea infrastructurii.
     */
    private int infrastructureCost;

    /**
     * @return ID-ul unui distribuitor
     */
    public int getId() {
        return id;
    }

    /**
     * @param idData ID-ul unui distribuitor
     */
    public void setId(final int idData) {
        this.id = idData;
    }

    /**
     * @return costul actualizat pentru realizarea infrastructurii
     */
    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * @param infrastructureCostData costul actualizat pentru realizarea
     *                               infrastructurii
     */
    public void setInfrastructureCost(final int infrastructureCostData) {
        this.infrastructureCost = infrastructureCostData;
    }


}
