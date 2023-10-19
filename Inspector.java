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

        System.out.println("Methods this class declares:");
        Method[] methods = classObject.getDeclaredMethods();
        for(Method m : methods){
            try {
                m.setAccessible(true);
                inspectMethod(m);
            } catch (InaccessibleObjectException e) {
                System.out.println("Method Inaccessible: " + m.getName());
            }
        }

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
            for (Class i : interfaces){
                System.out.print(i.getName() + ", ");
            }
            System.out.println("");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void inspectMethod(Method methodObject){
        System.out.println("Method name: " + methodObject.getName());
        System.out.print("    Exceptions Thrown: ");
        Class[] exceptions = methodObject.getExceptionTypes();
        for (Class e : exceptions){
            System.out.print(e.getName() + ", ");
        }
        System.out.println("");
    }

    public void inspectFields(Object obj,Class classObject, ArrayList fields){
        //uh
    }

    public void inspectRecursive(Object obj, Class classObject, ArrayList fields, boolean recursive){
        //uh
    }
    
}
