
package MachineObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {

    private List<MachineComponent> components = new ArrayList<>();
    private List<MachineComponent> brokens = new ArrayList<>();

    public void addComponent(MachineComponent mc) {

        components.add(mc);
        mc.addObserver(this);

        if (mc.isBroken()) {
            brokens.add(mc);

            if (!broken && brokens.size() == 1) {
                notificar();
            }
        }
    }

    @Override
    public boolean isBroken() {

        if(broken || brokens.size() > 0) {
            return true;
        }
        return false;

    }

    @Override
    public void update(Observable o, Object arg) {

        MachineComponent mc = (MachineComponent) o;

        if (mc.isBroken()) {
            brokenComponents(mc);
        } else {
            repairedComponents(mc);
        }
    }

    private void repairedComponents(MachineComponent mc) {

        if (!mc.isBroken()) {
            brokens.remove(mc);
            notificar();
        }
    }

    private void brokenComponents(MachineComponent mc) {

        if (!mc.isBroken()) {
            brokens.add(mc);
            notificar();
        }
    }

  }

