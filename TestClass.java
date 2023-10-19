import java.util.concurrent.BrokenBarrierException;

import javax.naming.directory.InvalidAttributeIdentifierException;

public class TestClass {
    protected int numb;
    public String letter;
    protected Boolean maybe;
    public int[][][] numbs;
    protected Object[] classes;

    public TestClass(){
        numb = 1;
        letter = "deez";
        maybe = true;
        numbs = new int[2][2][1];
        classes = new ClassD[5];
        for(int i = 0; i<5; i++){
            classes[i] = new ClassD(i);
        }
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
