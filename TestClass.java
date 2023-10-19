import java.util.concurrent.BrokenBarrierException;

import javax.naming.directory.InvalidAttributeIdentifierException;

public class TestClass {
    int numb;
    String letter;
    Boolean maybe;

    public TestClass(){
        numb = 1;
        letter = "deez";
        maybe = true;
    }

    public TestClass(int a, String b, Boolean c){
        numb = a;
        letter = b;
        maybe = c;
    }

    private void doSumn(int b, double ds, boolean bs) throws BrokenBarrierException, InvalidAttributeIdentifierException {
        numb += b;
    }

    protected String konkat(String a){
        letter = letter + a;
        try {
            doSumn(2, 2, true);
        } catch (InvalidAttributeIdentifierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return letter;
    }
    
}
