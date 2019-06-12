package MachineObservable;

public abstract class MachineComponent {

    protected boolean broken = false;
    public void setBroken() {broken=true;}
    public void repair() {broken=false;}
    public abstract boolean isBroken();

}
