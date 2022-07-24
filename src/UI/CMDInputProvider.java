package UI;

import logic.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CMDInputProvider extends InputProvider {
    private final Scanner sc = new Scanner(System.in);
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

    @Override
    protected Action getAction() {
        System.out.println("Enter your move!");
        char move =getChar();
        return switch (Character.toUpperCase(move)) {
            case 'W' -> Action.UP;
            case 'S' -> Action.DOWN;
            case 'D' -> Action.RIGHT;
            case 'A' -> Action.LEFT;
            case 'E' -> Action.ABILITY;
            default -> Action.NONE;
        };
        // whatever
    }
    private char getChar(){
        String s = sc.next();
        while(s.length()!=1||!MOVE_LIST.contains(s.charAt(0)))
            s = sc.next();
        return s.charAt(0);
    }
    public int getIntegerInput(){
        return sc.nextInt();
    }
}
