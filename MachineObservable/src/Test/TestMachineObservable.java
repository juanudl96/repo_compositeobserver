package Test;
import MachineObservable.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Observer;
import java.util.Observable;

public class TestMachineObservable {

    private Machine mach=new Machine();
    private MachineComposite mc=new MachineComposite();
    private Comprovation c = new Comprovation();

    @Test
    public void checkIsAndSetBroken()
    {
        assertFalse(mc.isBroken() && mach.isBroken());
        mach.setBroken();
        assertTrue(mach.isBroken());
        mc.addComponent(mach);
        assertTrue(mc.isBroken());

    }

    @Test
    public void checkMachineReparation()
    {
        mach.setBroken();
        assertTrue(mach.isBroken());
        mach.repair();
        assertFalse(mach.isBroken());
    }
    @Test
    public void checkMachineCompositeReparation()
    {
        Machine mach2=new Machine();
        assertFalse(mc.isBroken());
        mach.setBroken();
        mach2.setBroken();
        mc.addComponent(mach);
        mc.addComponent(mach2);
        assertTrue(mc.isBroken());
        mach.repair();
        mach2.repair();
        assertFalse(mc.isBroken());

    }
    @Test
    public void machineCompositeNotRepared()
    {
        Machine mach2=new Machine();
        // System.out.println(mc.isBroken());
        assertFalse(mc.isBroken());
        mach.setBroken();
        mc.addComponent(mach);
        assertTrue(mc.isBroken());
        mc.repair(); //Comprovem que MachineComposite no es pot reparar per ella mateixa
        assertTrue(mc.isBroken());

    }

    @Test
    public void machineUpdate () {
        mach.addObserver(c);
        mach.setBroken();
        mach.setBroken();
        assertFalse(c.doubleUpdate);
        mach.repair();
        assertTrue(c.doubleUpdate);
    }

    @Test
    public void machineCompositeNotReparedUpdate()
    {
        Machine m2 = new Machine();
        mc.addObserver(c);

        mc.addComponent(m2);
        mc.addComponent(mach);

        mach.setBroken();
        m2.setBroken();
        m2.repair();

        assertFalse(c.doubleUpdate);
    }




    public class Comprovation implements Observer {
        public boolean hasUpdated = false;
        public boolean doubleUpdate = false;

        @Override
        public void update(Observable o, Object arg) {
            if (hasUpdated == true) {
                doubleUpdate = true;
            }
            hasUpdated = true;
        }
    }


}