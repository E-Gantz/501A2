//Structure inspired by Jordan Kidney
import java.util.*;
import java.lang.reflect.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive){
        ArrayList<Object> recurseObjects = new ArrayList<Object>();
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

        System.out.println("Constructors this class declares:");
        Constructor[] makers = classObject.getDeclaredConstructors();
        for(Constructor maker : makers){
            inspectConstructor(maker);
        }

        System.out.println("Fields this class declares:");
        Field[] fields = classObject.getDeclaredFields();
        for(Field field : fields){
            try {
                field.setAccessible(true);
                inspectField(field, obj, recurseObjects);
            } catch (InaccessibleObjectException e) {
                System.out.println("Field Inaccessible: " + field.getName());
            }
        }

        if (recursive){
            inspectRecursive(obj, classObject, recurseObjects, recursive);
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
        System.out.println("    Method name: " + methodObject.getName());

        System.out.print("        Exceptions Thrown: ");
        Class[] exceptions = methodObject.getExceptionTypes();
        for (Class e : exceptions){
            System.out.print(e.getName() + ", ");
        }
        System.out.println("");

        System.out.print("        Parameter Types: ");
        Class[] params = methodObject.getParameterTypes();
        for (Class param : params){
            System.out.print(param.getName() + ", ");
        }
        System.out.println("");

        System.out.println("        Return Type: " + methodObject.getReturnType().getName());

        System.out.println("        Modifiers: " + Modifier.toString(methodObject.getModifiers()));
    }


    public void inspectConstructor(Constructor maker){
        System.out.println("    Constructor name: " + maker.getName());
        System.out.print("        Parameter Types: ");
        Class[] params = maker.getParameterTypes();
        for (Class param : params){
            System.out.print(param.getName() + ", ");
        }
        System.out.println("");

        System.out.println("        Modifiers: " + Modifier.toString(maker.getModifiers()));
    }


    public void inspectField(Field field, Object obj, ArrayList<Object> recurseObjects){
        System.out.println("    Field name: " + field.getName());
    }


    public void inspectRecursive(Object obj, Class classObject, ArrayList fields, boolean recursive){
        //uh
    }

    
}
