package UI;

import logic.Player;
import logic.Position;
import logic.Tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CLI {
    private static final HashMap<String, Supplier> colors= new HashMap<>(){{
        put("@",()->ConsoleColors.GREEN_BOLD_BRIGHT);
        put("#",()-> ConsoleColors.WHITE_BACKGROUND);
        put("s",()->ConsoleColors.RED);
        put("k",()->ConsoleColors.RED);
        put("q",()->ConsoleColors.RED);
        put("z",()->ConsoleColors.RED);
        put("b",()->ConsoleColors.RED);
        put("g",()->ConsoleColors.RED);
        put("w",()->ConsoleColors.RED);
        put("B",()->ConsoleColors.BLUE);
        put("Q",()->ConsoleColors.BLUE);
        put("D",()->ConsoleColors.BLUE);
        put("M",()->ConsoleColors.RED);
        put("C",()->ConsoleColors.RED);
        put("K",()->ConsoleColors.RED);
        put(".",()->ConsoleColors.WHITE);
        put("X",()-> ConsoleColors.RED_BACKGROUND_BRIGHT);

    }};

    public CLI() {
    }
    public void print(String color,String s){
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+color+s+ConsoleColors.RESET);
    }

    public void printBoard(List<Tile> tiles,int length, int width){
        tiles = tiles.stream().sorted().collect(Collectors.toList()); // sorted by compareTo;
        List<String> tileStrings = tiles.stream().map(Tile::toString).collect(Collectors.toList()); // list of tile names
        int rowIndex= 0;
        StringBuilder board = new StringBuilder();
        for(int i =0; i<length; i++) { // append every row in the board
            List<String> row = tileStrings.subList(rowIndex, rowIndex + width);
            board.append(row.stream().reduce("", (acc, next) -> acc + colors.get(next).get() + next + ConsoleColors.RESET));
            board.append("\n");
            rowIndex += width;
        }
        System.out.println(board);

    }
    public void printInfo(List<Player> players){
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT+"CHOOSE UR HERO!"+ConsoleColors.RESET);
        players.forEach((p)-> System.out.println((ConsoleColors.YELLOW_BACKGROUND+ConsoleColors.WHITE_BOLD_BRIGHT+(players.indexOf(p)+1)+". "+p.describe()+ConsoleColors.RESET)));



    }
    /*
    tiles = tiles.stream().sorted().collect(Collectors.toList()); // sorted by compareTo;
        List<String> tileStrings = tiles.stream().map(Tile::toString).collect(Collectors.toList()); // list of tile names
        int rowIndex= 0;
        StringBuilder board = new StringBuilder();
        for(int i =0; i<length; i++){ // append every row in the board
            List<String> row = tileStrings.subList(rowIndex,rowIndex+width);
            board.append(row.stream().reduce("", (acc, next) -> acc + colors.get(next).get()+next+ConsoleColors.RESET));
            board.append("\n");
            rowIndex+=width;
        }
        System.out.println(board);
     */

}
