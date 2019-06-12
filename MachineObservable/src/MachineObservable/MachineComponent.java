package MachineObservable;

import java.util.Observable;

public abstract class MachineComponent extends Observable {

    protected boolean broken = false;
    public void setBroken() {
        boolean trencat = isBroken();
        broken = true;
        if (!trencat) {
            notificar();
        }}
    public void repair() {
        boolean trencat=broken;
        broken=false;
        if(trencat)
        {
            notificar();
        }
    }
    public abstract boolean isBroken();

    protected void notificar(){

        setChanged();
        notifyObservers();
    }
}
