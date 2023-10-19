import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class InspectorTest {
    final PrintStream sOut = System.out;
    final ByteArrayOutputStream captcha = new ByteArrayOutputStream();

    @Before
    public void setup(){
        System.setOut(new PrintStream(captcha));
    }

    @After
    public void teardown(){
        System.setOut(sOut);
    }

    @Test
	public void correctName() {
		Inspector gadget = new Inspector();
        gadget.inspect(new ClassA(1), false);
        assertTrue(captcha.toString().contains("Name of declaring class: ClassA"));
	}

    @Test
	public void correctSuperName() {
		Inspector gadget = new Inspector();
        try {
            gadget.inspect(new ClassB(), false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue(captcha.toString().contains("Name of immediate superclass: ClassC"));
	}

}
