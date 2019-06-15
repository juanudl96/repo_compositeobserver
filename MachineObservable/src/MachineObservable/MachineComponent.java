
package MachineObservable;

import java.util.Observable;

public abstract class MachineComponent extends Observable {

    protected boolean broken = false;

    public void setBroken() {

        boolean aux = isBroken();
        broken = true;

        if (!aux) {
            notificar();
        }
    }

    public void  repair() {

        boolean aux = isBroken();
        broken = false;

        if (aux) {
            notificar();
        }
    }

    public abstract boolean isBroken();

    protected void notificar(){

        setChanged();
        notifyObservers();
    }
}
