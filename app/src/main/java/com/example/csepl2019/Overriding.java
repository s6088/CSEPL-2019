package com.example.csepl2019;


class A{
    public void show(){
        System.out.println("in A");
    }
}

class B extends A{
    //show1
    @Override
    public void show(){
        System.out.println("in B");
    }
}

public class Overriding {


    public static void main (String[] args){
        B b = new B();
        b.show();
    }

}
