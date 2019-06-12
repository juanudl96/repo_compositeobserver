package MachineObservable;

import java.util.ArrayList;
import java.util.List;

public class MachineComposite extends MachineComponent {
    private List<MachineComponent> components = new ArrayList<>();

    public void addComponent(MachineComponent mc)
    {
        components.add(mc);
    }
    @Override
    public boolean isBroken() {
        if(broken) {return true;}
        for(MachineComponent mc:components)
        {
            if(mc.isBroken()){return true;}

        }
        return false;
    }
}
