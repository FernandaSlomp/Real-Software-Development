package org.example.liskovExemple;

public class ClassChild extends ClassFather{

    @Override
    public int salary(int years) throws Exception {
        if (years < 30){
            return 101;
        }
         throw new Exception();
    }

}
