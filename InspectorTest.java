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

    @Test
	public void correctInterfaces() {
		Inspector gadget = new Inspector();
        gadget.inspect(new ClassA(1), false);
        assertTrue(captcha.toString().contains("Name of the interfaces the class implements: java.io.Serializable, java.lang.Runnable"));
	}

    @Test
	public void correctMethodNames() {
		Inspector gadget = new Inspector();
        gadget.inspect(new ClassA(1), false);
        assertTrue(captcha.toString().contains("Method name: run") && 
        captcha.toString().contains("Method name: toString") && 
        captcha.toString().contains("Method name: printSomething") && 
        captcha.toString().contains("Method name: setVal") && 
        captcha.toString().contains("Method name: getVal"));
	}

    @Test
	public void correctExceptionName() {
		Inspector gadget = new Inspector();
        gadget.inspect(new ClassA(1), false);
        assertTrue(captcha.toString().contains("Exceptions Thrown: java.lang.Exception"));
	}

}
