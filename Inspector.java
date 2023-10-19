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
            //Class superClass = classObject.getSuperclass();
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
