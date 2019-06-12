package MachineObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {
    private List<MachineComponent> components = new ArrayList<>();
    private int subcomponentsTrencats = 0;

    public void addComponent(MachineComponent mc) {
        components.add(mc);
        mc.addObserver(this);
        if (mc.isBroken()) {
            subcomponentsTrencats += 1;
            if (!broken && subcomponentsTrencats == 1) {

            }
        }
    }

    @Override
    public boolean isBroken() {
        //Modifiquem el isBroken per simplificar codi i reduir cost aixi evitar una cerca per bucle on el cost seria molt elevat
        return broken || subcomponentsTrencats > 0;
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
        subcomponentsTrencats -= 1;
        if (!isBroken()) {
            notificar();
        }
    }

    private void componentsTrencats(MachineComponent mc) {
        boolean trencat = isBroken();
        subcomponentsTrencats += 1;
        if (!trencat) {
            notificar();
        }
    }

  }

