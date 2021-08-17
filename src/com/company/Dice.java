package com.company;

public class Dice {

    //TODO Create a single 6 prob dice --[x]
    // TODO create a single modifiable dice(current is perfect but could be better such as a 12 or 8 sided dice) --[]
    //Can do this by making it a non static object
    private static byte value;

    public static  void setValue(){

        byte min = 1;

        byte max = 6;

        value = (byte)Math.floor(Math.random()*(max-min+1)+min);
    }

    public static byte roll() {
        setValue ();
        return value;
    }

    public static byte getValue() {

        return value;
    }

    /*
Testing if rolls are random
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println ("--------------\nbefore roll: "+getValue ());
          //  System.out.println (getRoll ());
            System.out.println (roll ());
            System.out.println ("after roll: "+getValue () + "\n--------------");
        }
*/


}
