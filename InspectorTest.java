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
	public void correctClassName() {
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
	public void correctMethodExceptions() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Exceptions Thrown: java.util.concurrent.BrokenBarrierException, javax.naming.directory.InvalidAttributeIdentifierException"));
	}

    @Test
	public void correctMethodParameters() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Parameter Types: int, double, boolean"));
	}

    @Test
	public void correctMethodReturn() throws Exception {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Return Type: java.lang.String"));
	}

    @Test
	public void correctMethodModifiers() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Modifiers: public") && 
        captcha.toString().contains("Modifiers: private") && 
        captcha.toString().contains("Modifiers: protected"));
	}

    @Test
	public void correctConstructorName() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Constructor name: TestClass"));
	}

    @Test
	public void correctConstructorParameters() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Parameter Types: int, java.lang.String, java.lang.Boolean"));
	}

    @Test
	public void correctConstructorModifiers() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Modifiers: public"));
	}

    @Test
	public void correctFieldNames() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Field name: numb") && 
        captcha.toString().contains("Field name: letter") && 
        captcha.toString().contains("Field name: maybe"));
	}

    @Test
	public void correctFieldTypes() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Type: int") && 
        captcha.toString().contains("Type: java.lang.String") && 
        captcha.toString().contains("Type: java.lang.Boolean"));
	}

    @Test
	public void correctFieldPrimitiveValue() {
		Inspector gadget = new Inspector();
        gadget.inspect(new TestClass(), false);
        assertTrue(captcha.toString().contains("Value: 1"));
	}
}
