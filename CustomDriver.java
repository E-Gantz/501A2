/*==========================================================================
File: CustomDriver.java
Purpose: Driver program that loads the objects inspector and runs the
         tests. Verification of tests is done through the inspection
         of the out from the object inspector loaded at run time.

Location: University of Calgary, Alberta, Canada
Created By: Jordan Kidney
Created on:  Oct 23, 2005
Last Updated: Oct 23, 2005
========================================================================*/

import java.lang.reflect.*;

public class CustomDriver
{
    
    //-------------------------------------------------------------------
    public CustomDriver(String ObjInspectorName, boolean recursive)
	throws Exception
    {
	this.recursive=recursive;
	setObjectInspectorInfo(ObjInspectorName);
    }
    //--------------------------------------------------------------------
    public void setObjectInspectorInfo(String ObjectInspectorName)
	throws Exception
    {
	Class objInspectClass=null;
	try
	    {
		objInspectClass = Class.forName(ObjectInspectorName);
		ObjInspector = objInspectClass.newInstance();
	    }
	catch(Exception e) 
	    {
		throw new Exception("Unable create instance of your object inspector");
	    }

	// get reference to inspect method
	try
	    {
		Class[] param = { Object.class, boolean.class };
		inspectionMethod = objInspectClass.getDeclaredMethod("inspect",param);
	    }
	catch(Exception e) 
	    {
		throw new Exception("Unable to find required method: public void inspect(Object obj,boolean recursive)");
	    }
    }
    //--------------------------------------------------------------------
    public void runTest(Object testObj) throws Exception
    {
	try
	    {
		System.out.println("======================================================");
		System.out.println("Running Test: " + testObj);
		Object[] param = { testObj, new Boolean(recursive) };
		inspectionMethod.invoke(ObjInspector, param); 
		System.out.println("======================================================");
	    }
	catch(Exception e)
	    {
		
		e.printStackTrace();
		throw new Exception("unable to compleatly run test");
	    
	    }
    }
    //------- Fields -----------------------------------------------------
    private Object ObjInspector = null;
    private Method inspectionMethod =null;
    private boolean recursive=false;
    //====================== MAIN =======================================
    public static void main(String[] args)
    {
	boolean rec=false;

    try
        {
        System.out.println("Loading object inspector: " + "Inspector");
        CustomDriver driver = new CustomDriver("Inspector",rec);
        /*driver.runTest( new ClassA() );
        driver.runTest( new ClassA(12) );
        driver.runTest( new ClassB() );
        driver.runTest( new ClassD(32) );
        driver.runTest( new ClassD() );
        driver.runTest( new ClassB[12] );
        driver.runTest( new ClassB[12][12] );	
        //driver.runTest( "Test String" );*/
        driver.runTest(new TestClass());
        }
    catch(Exception e)
        {
        
        System.out.println("ERROR: " + e.getMessage());
        System.out.println("Exiting test driver");
        }
    }
}
