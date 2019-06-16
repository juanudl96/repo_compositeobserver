
package Test;
import MachineObservable.*;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Observer;
import java.util.Observable;

public class TestMachineObservable {

    private Machine m = new Machine();
    private MachineComposite mc = new MachineComposite();
    private Comprovation c = new Comprovation();

    // Machine

    @Test
    public void checkMachineIsBroken() {

        assertFalse(m.isBroken());
        m.setBroken();
        assertTrue(m.isBroken());
    }

    @Test
    public void checkMachineRepaired() {

        m.setBroken();
        m.repair();
        assertFalse(m.isBroken());
    }

    @Test
    public void checkMachineBrokenUpdate () {

        m.addObserver(c);
        m.setBroken();
        assertTrue(c.first);

        m.setBroken();
        assertFalse(c.second);
    }

    @Test
    public void checkMachineRepairedUpdate () {

        m.addObserver(c);
        m.setBroken();

        m.repair();
        assertTrue(c.first);

        m.repair();
        assertTrue(c.second);
    }

    // Machine Composite

    @Test
    public void checkMachineCompositeIsBroken() {

        assertFalse(mc.isBroken());

        m.setBroken();
        mc.addComponent(m);

        assertTrue(mc.isBroken());
    }

    @Test
    public void checkMachineCompositeRepaired() { //revisar a partir de este test

        assertFalse(mc.isBroken());

        m.setBroken();
        mc.addComponent(m);

        assertTrue(mc.isBroken());

        Machine m2 = new Machine();
        m2.setBroken();
        mc.addComponent(m2);

        m.repair();
        assertFalse(mc.isBroken());

        m2.repair();
        assertTrue(mc.isBroken());
    }

    @Test
    public void checkMachineCompositeReparation() {

        Machine mach2=new Machine();
        assertFalse(mc.isBroken());

        m.setBroken();
        mach2.setBroken();
        mc.addComponent(m);
        mc.addComponent(mach2);
        assertTrue(mc.isBroken());

        m.repair();
        mach2.repair();
        assertFalse(mc.isBroken());

    }

    @Test
    public void machineCompositeNotRepaired() {

        Machine mach2=new Machine();
        assertFalse(mc.isBroken());

        m.setBroken();
        mc.addComponent(m);
        assertTrue(mc.isBroken());

        mc.repair(); //Comprovem que MachineComposite no es pot reparar per ella mateixa
        assertTrue(mc.isBroken());

    }

    // update test

    @Test
    public void machineCompositeNotRepairedUpdate() {

        Machine m2 = new Machine();
        mc.addObserver(c);

        mc.addComponent(m2);
        mc.addComponent(m);

        m.setBroken();
        m2.setBroken();
        m2.repair();

        assertFalse(c.second);
    }

    public static class Comprovation implements Observer {

        private boolean first = false;
        private boolean second = false;

        @Override
        public void update(Observable o, Object arg) {

            if (first == true) {
                second = true;
            }
            first = true;
        }
    }


}