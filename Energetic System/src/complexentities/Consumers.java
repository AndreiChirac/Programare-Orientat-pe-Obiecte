package complexentities;

import contracts.Contracts;

import java.util.ArrayList;

public class Consumers extends Entities {
    /**
     * coeficientul pentru o factura intarziata.
     */
    public static final double COEFFICIENT = 1.2;
    /**
     * venitul lunar obtinut de un consumator.
     */
    private int monthlyIncome;
    /**
     * statutul initial al unui consumators.
     */
    private boolean hasContract = false;
    /**
     * contractul vechi al utilizatorului.
     */
    private Contracts oldContract;
    /**
     * daca utilziatorul este sau nu intarziat cu plata.
     */
    private boolean inDept = false;
    /**
     * distibutorul la care consumatorul a avut contract.
     */
    private Integer oldDistibutorId = -1;

    /**
     * @param idData            id-ul unui utilizator
     * @param initialBudgetData bugetul initial al unui utilizator
     * @param monthlyIncomeData bugetul lunar al unui utilizator
     */
    public Consumers(final int idData, final int initialBudgetData,
                     final int monthlyIncomeData) {
        super(idData, initialBudgetData);
        this.monthlyIncome = monthlyIncomeData;
    }

    /**
     * @return venitul lunar obtinut de un consumator.
     */
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @param monthlyIncomeData venitul lunar obtinut de un consumator.
     */
    public void setMonthlyIncome(final int monthlyIncomeData) {
        this.monthlyIncome = monthlyIncomeData;
    }

    /**
     * @param inDeptData seteaza valoarea de adevar la afirmatia ca un
     *                   utilizator areo datorie sau nu
     */
    public void setInDept(final boolean inDeptData) {
        this.inDept = inDeptData;
    }

    /**
     * @return statutul de adevar al afirmatiei ca un consumaotr are un
     * contract activ.
     */
    public boolean isHasContract() {
        return hasContract;
    }

    /**
     * @param distibutors selecteaza un contract pentru consumator
     *                    Seteaza pentru un consumator contactul astfel
     *                    acesta initial verifica
     *                    daca exista sau nu un contract activ , daca exista
     *                    contractul activ nu
     *                    se va intampla nimic , dar daca este nevoie de un
     *                    contract se va cauta
     *                    cu ajutorul smallestDistibutorContract cel mai mic
     *                    contract , daca
     *                    rezultatul functiei este null inseamna ca nici un
     *                    distibuitori nu poate
     *                    oferi un contract , daca rezultatul este diferit
     *                    atunci se creaza un
     *                    contract care va fi adaugat in lista de contracte
     *                    atat a utilizator
     *                    cat si in cea a distribuitorului, iar acum ca
     *                    consumaotorul are un
     *                    contract vom seta valoarea adevarata in campul
     *                    hasContract
     */
    public void setContract(final ArrayList<Distibutors> distibutors) {
        if (!this.hasContract) {
            Distibutors distibutor = smallestDistibutorContract(distibutors);
            if (distibutor != null) {
                Contracts contract = new Contracts(this.getId(),
                        distibutor.getContractCost(),
                        distibutor.getContractLength());
                int index = findIndex(distibutors, distibutor);
                distibutors.get(index).getContracts().add(contract);
                super.getContracts().add(0, contract);
                this.hasContract = true;
            }
        }
    }

    /**
     * @param distibutors lista cu distibuitorii
     * @return returneaza distibuitroul cu contractul cel mai mic
     * se intereaza prin toti distibuitorii se verifica daca csotu contactului
     * este mai mic decat cel initial , in ac timp se verifica si ca
     * distribuiroul sa nu fie in faliment
     */
    public Distibutors smallestDistibutorContract(
            final ArrayList<Distibutors> distibutors) {
        Distibutors aux = null;
        int min = Integer.MAX_VALUE;
        for (Distibutors distibutor : distibutors) {
            if (distibutor.getContractCost() < min
                    && !distibutor.isBankrupt()) {
                aux = distibutor;
                min = aux.getContractCost();
            }
        }
        return aux;
    }


    /**
     * @param distibutors lista unde se cauta distibuitorul
     * @param distibutor  distribuitorul care este cautat
     * @return indexul unde s-a gasit distibuitrul in lista de distribuitori
     */
    private int findIndex(final ArrayList<Distibutors> distibutors,
                          final Distibutors distibutor) {
        for (int i = 0; i < distibutors.size(); i++) {
            if (distibutors.get(i).equals(distibutor)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * calculeaza noul buget al consumatorului.
     * Vom testa initial daca consumatorul se afla sau nu in falimetn pentru
     * a sti daca trebuie sa efectuam sau nu operatiile pe acesta.In cazul in
     * care acesta este un utilizator valid testam daca are sau nu o datorie .
     * In caazul in care nu are datorie se vor calcula costurile pe care
     * trebuie sa le plateasca daca acestea depasesc bugetul , utilizatorul
     * nu va plati nimic , iar acesta va fi declarat dator ,iar campul old
     * contract va fi asociat cu cel curent in cazul in care se schimba
     * contractul in urmatoarea luna si trebuie sa se plateasca factura, daca
     * nu este acest caz se va scadea din buget ce trebuie platit. In cazul
     * in care consumatorul este deja indatorat vom verifica daca acesta
     * poate sa-si plateasca factura curenta si pe cea anterioara , daca nu
     * acesta va intra in faliment . In acelasi timp se verifca de fiecare
     * data in orice caz am fi daca consumatorul mai are contract sau acesta
     * a expirat. Daca a expirat va trebui sa resetam utilizatorul .
     *
     * @param distibutors lista cu distibutorii
     */
    public void recalulculateBudget(final ArrayList<Distibutors> distibutors) {
        if (!super.isBankrupt()) {
            if (!this.inDept) {
                int budget = super.getBudget();
                budget = budget + this.monthlyIncome;
                int cost = super.getContracts().get(0).getPrice();
                if (budget - cost < 0) {
                    if (super.getContracts().get(0)
                            .getRemainedContractMonths() == 1) {
                        this.oldDistibutorId = findDistibutorid(distibutors);
                    }
                    this.inDept = true;
                    super.getContracts().get(0).setRemainedContractMonths(
                            super.getContracts().get(0)
                                    .getRemainedContractMonths() - 1);
                    this.oldContract = super.getContracts().get(0);

                } else {
                    budget = budget - cost;
                    payToDistibutor(distibutors, super.getContracts().get(0),
                            cost);
                    super.getContracts().get(0).setRemainedContractMonths(
                            super.getContracts().get(0)
                                    .getRemainedContractMonths() - 1);
                }
                if (super.getContracts().get(0)
                        .getRemainedContractMonths() == 0) {
                    this.hasContract = false;
                    this.getContracts().remove(0);
                }
                super.setBudget(budget);
            } else { //utilizatorul a amanat deja o factura
                int budget = super.getBudget();
                if (oldDistibutorId == findDistibutorid(distibutors)
                        || oldDistibutorId == -1) {
                    budget = budget + this.monthlyIncome;
                    int cost =
                            (int) ((Math.round(
                                    Math.floor(
                                            COEFFICIENT
                                                    * oldContract.getPrice()))
                                    + super.getContracts().get(0).getPrice()));
                    if (budget - cost < 0) {
                        super.setBankrupt(true);
                        this.getContracts().remove(0);
                    } else {
                        this.oldContract = null;
                        this.inDept = false;
                        budget = budget - cost;
                        payToDistibutor(distibutors,
                                super.getContracts().get(0),
                                cost);
                        super.getContracts().get(0).setRemainedContractMonths(
                                super.getContracts().get(0)
                                        .getRemainedContractMonths() - 1);
                        if (super.getContracts().get(0)
                                .getRemainedContractMonths() == 0) {
                            this.hasContract = false;
                            this.getContracts().remove(0);
                        }
                    }
                } else {
                    budget = budget + this.monthlyIncome;
                    int cost;
                    if (budget - (int) ((Math.round(
                            Math.floor(
                                    COEFFICIENT
                                            * oldContract.getPrice()))
                            + super.getContracts().get(0).getPrice())) >= 0) {
                        cost = (int) ((Math.round(
                                Math.floor(
                                        COEFFICIENT
                                                * oldContract.getPrice()))
                                + super.getContracts().get(0).getPrice()));
                    } else {
                        cost =
                                (int) ((Math.round(
                                        Math.floor(
                                                COEFFICIENT
                                                        *
                                                        oldContract
                                                                .getPrice()))));
                    }
                    if (budget - cost < 0) {
                        super.setBankrupt(true);
                        this.getContracts().remove(0);
                    } else {
                        if (cost == (int) ((Math.round(
                                Math.floor(
                                        COEFFICIENT
                                                * oldContract.getPrice()))
                                + super.getContracts().get(0).getPrice()))) {
                            this.inDept = false;
                            this.oldContract = null;
                            oldDistibutorId = -1;
                        } else {
                            this.inDept = true;
                            this.oldContract = super.getContracts().get(0);
                            oldDistibutorId = findDistibutorid(distibutors);
                        }

                        budget = budget - cost;
                        payToDistibutor(distibutors,
                                super.getContracts().get(0),
                                cost);
                        super.getContracts().get(0).setRemainedContractMonths(
                                super.getContracts().get(0)
                                        .getRemainedContractMonths() - 1);
                        if (super.getContracts().get(0)
                                .getRemainedContractMonths() == 0) {
                            this.hasContract = false;
                            this.getContracts().remove(0);
                        }
                    }
                }
                super.setBudget(budget);
            }
        }
    }

    /**
     * se intereaza prin contractele distibuitorului pentru a scoate
     * contractul utilizatorului.
     *
     * @param distibutors lista in care se cauta sa fie eliminat
     */
    public void removeFromDistributor(
            final ArrayList<Distibutors> distibutors) {
        for (Distibutors distibutor : distibutors) {
            for (int j = 0; j < distibutor.getContracts().size(); j++) {
                if (distibutor.getContracts().get(j).getConsumerId()
                        == this.getId()) {
                    distibutor.getContracts()
                            .remove(distibutor.getContracts().get(j));
                }
            }
        }
    }

    /**
     * se realizeaza plata catre distibuitor.
     *
     * @param distibutors lista cu distivuitor
     * @param contract    contractul
     * @param cost        cost care trebuie platit
     */
    public void payToDistibutor(final ArrayList<Distibutors> distibutors,
                                final Contracts contract,
                                final int cost) {
        for (Distibutors distibutor : distibutors) {
            if (distibutor.getContracts().contains(contract)) {
                distibutor
                        .setBudget(distibutor.getBudget() + cost);
            }
        }
    }

    /**
     * @param distibutors lista cu distibuitorii
     * @return id-ul distibuitorului la care are consumatorul contract
     */
    public int findDistibutorid(final ArrayList<Distibutors> distibutors) {
        for (int i = 0; i < distibutors.size(); i++) {
            for (int j = 0; j < distibutors.get(i).getContracts().size(); j++) {
                if (distibutors.get(i).getContracts().get(j).getConsumerId()
                        == this.getId()) {
                    return distibutors.get(i).getId();
                }
            }
        }
        return -1;
    }


}
