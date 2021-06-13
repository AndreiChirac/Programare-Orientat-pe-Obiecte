package observer;

import parsing.ProducerChangesDataInput;

import java.util.Observable;

public class ProducerObserver extends Observable {
    /**
     * @param producerChanges schimbarile pe care le sufera un producator.
     */
    public void modifier(final ProducerChangesDataInput producerChanges) {

        setChanged();
        notifyObservers(producerChanges);
    }

}
