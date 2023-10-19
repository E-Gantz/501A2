//Structure inspired by Jordan Kidney
import java.util.*;
import java.lang.reflect.*;

public class Inspector {
    ArrayList<Integer> seenObjects = new ArrayList<Integer>();

    public void inspect(Object obj, boolean recursive){
        if(!seenObjects.contains(obj.hashCode())){
            seenObjects.add(obj.hashCode());
            ArrayList<Object> recurseObjects = new ArrayList<Object>();
            ArrayList<Object> superObjects = new ArrayList<Object>();
            Class classObject = obj.getClass();

            if (recursive){
                System.out.println("Inspecting " + obj + " recursively");
            }
            else {
                System.out.println("Inspecting " + obj + " non-recursively");
            }

            inspectClass(classObject, superObjects);

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
                    inspectField(field, obj, recurseObjects, recursive);
                } catch (InaccessibleObjectException e) {
                    System.out.println("Field Inaccessible: " + field.getName());
                }
            }
            System.out.println("");
            System.out.println("Traversing Inheritance Hierarchy for " + obj + "\n");

            for(Object superObj : superObjects){
                inspect(superObj, recursive);
            }

            if (recursive){
                inspectRecursive(obj, classObject, recurseObjects, recursive);
            }
        }
    }


    public void inspectClass(Class classObject, ArrayList<Object> superObjects){
        try {
            System.out.println("Name of declaring class: " + classObject.getName());

            Class superClass = classObject.getSuperclass();
            if(superClass != null){
                System.out.println("Name of immediate superclass: " + superClass.getName());
                superObjects.add(superClass);
            }

            Class[] interfaces = classObject.getInterfaces();
            System.out.print("Name of the interfaces the class implements: ");
            for (Class i : interfaces){
                System.out.print(i.getName() + ", ");
                superObjects.add(i);
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
            System.out.print(param.getTypeName() + ", ");
        }
        System.out.println("");

        System.out.println("        Return Type: " + methodObject.getReturnType().getTypeName());

        System.out.println("        Modifiers: " + Modifier.toString(methodObject.getModifiers()));
    }


    public void inspectConstructor(Constructor maker){
        System.out.println("    Constructor name: " + maker.getName());
        System.out.print("        Parameter Types: ");
        Class[] params = maker.getParameterTypes();
        for (Class param : params){
            System.out.print(param.getTypeName() + ", ");
        }
        System.out.println("");

        System.out.println("        Modifiers: " + Modifier.toString(maker.getModifiers()));
    }


    public void inspectField(Field field, Object obj, ArrayList<Object> recurseObjects, boolean recursive){
        System.out.println("    Field name: " + field.getName());

        Class fType = field.getType();
        System.out.println("        Type: " + fType.getTypeName());
        if(fType.isArray()){
            try {
                int len = Array.getLength(field.get(obj));
                System.out.println("        Length: " + len);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("        Value: Unreachable");
            }
        }

        System.out.println("        Modifiers: " + Modifier.toString(field.getModifiers()));

        if(fType.isPrimitive()){
            try {
                System.out.println("        Value: " + field.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("        Value: Unreachable");
            }
        }
        else if(fType.isArray()){
            System.out.print("        Contents: ");
            try {
                if(fType.getComponentType().isArray()){
                System.out.println(Arrays.deepToString((Object[]) field.get(obj)));
                }
                else{
                    System.out.println(Arrays.toString((Object[]) field.get(obj)));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("unable to access");
            }
        }
        else{
            recurseObjects.add(field);
            System.out.println("        Identity Hash Code: " + field.hashCode());
            if(recursive){System.out.println("            See below for introspection");}
        }
    }


    public void inspectRecursive(Object obj, Class classObject, ArrayList fields, boolean recursive){
        //uh
    }

    
}
