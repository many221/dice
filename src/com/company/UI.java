package com.company;

import java.util.Scanner;

public class UI {

    public static Scanner in = new Scanner ( System.in );

    public static Integer num(){
        return in.nextInt ();
    }


    public static String strL (){
        return in.nextLine ();
    }
    public static String str (){
        return in.next ();
    }
}
