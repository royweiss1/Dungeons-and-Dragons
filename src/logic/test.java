package logic;

import UI.CMDInputProvider;
import UI.ConsoleColors;

import java.util.*;
import java.util.function.Supplier;

public class test {
    private static HashMap<Character, Supplier> colors= new HashMap<>(){{
        put('@',()->ConsoleColors.GREEN);
        put('#',()-> ConsoleColors.WHITE_BACKGROUND);
        put('s',()->ConsoleColors.RED);
        put('k',()->ConsoleColors.RED);
        put('q',()->ConsoleColors.RED);
        put('z',()->ConsoleColors.RED);
        put('b',()->ConsoleColors.RED);
        put('g',()->ConsoleColors.RED);
        put('w',()->ConsoleColors.RED);
        put('B',()->ConsoleColors.RED);
        put('Q',()->ConsoleColors.RED);
        put('D',()->ConsoleColors.RED);

    }};
    protected static List<Character> MOVE_LIST = new ArrayList<Character>(){{
        add('w');
        add('W');
        add('a');
        add('A');
        add('s');
        add('S');
        add('d');
        add('D');
        add('q');
        add('Q');
        add('e');
        add('E');
    }};
    public static void main(String[] args){
        CMDInputProvider cmd = new CMDInputProvider();
        cmd.getInputQuery().getInput();
        String s = ConsoleColors.RED+"fasfsdfsdfsaf"+'\n'+"fasfasefseag"+ConsoleColors.GREEN+"safsfesef";
        System.out.println(s);
        Random r = new Random();
        System.out.println(r.nextInt(0));



    }
}
