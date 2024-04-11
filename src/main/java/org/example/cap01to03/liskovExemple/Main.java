package org.example.cap01to03.liskovExemple;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception {


        System.out.println("Liskov errors exemple");
        ClassChild classChild = new ClassChild();
        ClassFather classFather = new ClassFather();
        test(classFather);
        test(classChild);


    }

    public static void test(ClassFather father) throws Exception {
        int salary = father.salary(30);
        if (salary == 100){
            System.out.println("salary");
        }

    }
}
