package MachineObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {
    private List<MachineComponent> components = new ArrayList<>();
    private List<MachineComponent> componentstrencats = new ArrayList<>();

    public void addComponent(MachineComponent mc) {
        components.add(mc);
        mc.addObserver(this);
        if (mc.isBroken()) {
            componentstrencats.add(mc);
            if (!broken && componentstrencats.size()==1) {
                notificar();
            }
        }
    }

    @Override
    public boolean isBroken() {
        //Modifiquem el isBroken per simplificar codi i reduir cost aixi evitar una cerca per bucle on el cost seria molt elevat
       // return broken || subcomponentsTrencats > 0;
        if(broken || componentstrencats.size()>0)
        {
            return true;
        }
        return false;

    }

    @Override
    public void update(Observable o, Object arg) {
        MachineComponent mc = (MachineComponent) o;
        if (mc.isBroken()) {
            componentsTrencats(mc);
        } else {
            componentsReparats(mc);
        }
    }

    private void componentsReparats(MachineComponent mc) {
        if (!mc.isBroken()) {
            componentstrencats.remove(mc);
            notificar();
        }
    }

    private void componentsTrencats(MachineComponent mc) {
        if (!mc.isBroken()) {
            componentstrencats.add(mc);
            notificar();
        }
    }

  }

