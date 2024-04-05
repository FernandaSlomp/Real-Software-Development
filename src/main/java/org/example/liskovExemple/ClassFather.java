package org.example.liskovExemple;

public class ClassFather {

    public int salary(int years) throws Exception {
        if (years >= 30){
            return 100;
        }
        return 0;
    }

}
