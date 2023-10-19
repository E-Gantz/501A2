//Structure inspired by Jordan Kidney
import java.util.*;
import java.lang.reflect.*;

public class Inspector {
    public void inspect(Object obj, boolean recursive){
        ArrayList<Object>  fields = new ArrayList<Object>();
        Class classObject = obj.getClass();

        if (recursive){
            System.out.println("Inspecting " + obj + " recursively");
        }
        else {
            System.out.println("Inspecting " + obj + " non-recursively");
        }

        inspectClass(classObject);
        inspectFields(obj, classObject, fields);

        if (recursive){
            inspectRecursive(obj, classObject, fields, recursive);
        }
    }

    public void inspectClass(Class classObject){
        try {
            System.out.println("Name of declaring class: " + classObject.getName());

            Class superClass = classObject.getSuperclass();
            if(superClass != null){
                System.out.println("Name of immediate superclass: " + superClass.getName());
            }

            Class[] interfaces = classObject.getInterfaces();
            System.out.print("Name of the interfaces the class implements: ");
            for (int i=0; i< interfaces.length; i++){
                if(i!=interfaces.length-1){
                    System.out.print(interfaces[i].getName() + ", ");
                }
                else {
                    System.out.print(interfaces[i].getName());
                }
            }
            System.out.println("");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void inspectFields(Object obj,Class classObject, ArrayList fields){
        //uh
    }

    public void inspectRecursive(Object obj, Class classObject, ArrayList fields, boolean recursive){
        //uh
    }
    
}
