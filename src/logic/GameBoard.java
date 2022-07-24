package logic;

import callBacks.PrintBoardCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
public class GameBoard {
    private List<Tile> tiles;
    private int length;
    private int width;
    private PrintBoardCallback printBoardCallback;
    public GameBoard(Tile[][] board,int length, int width) {
        tiles = new ArrayList<>();
        for (Tile[] line : board) {
            tiles.addAll(Arrays.asList(line));
        }
        this.length = length;
        this.width = width;
    }
    public void setPrintBoardCallback(PrintBoardCallback printBoardCallback){
        this.printBoardCallback = printBoardCallback;
    }

    public Tile get(Position pos) {
        for (Tile t : tiles) {
                if (t.getPosition().equals(pos)) {
                return t;
            }
        }
        // Throw an exception if no such tile.
        throw new NoSuchElementException();

    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        Empty empty = new Empty();
        empty.setPosition(p);
        tiles.add(empty);


    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList()); // sorted by compareTo;
        List<String> tileStrings = tiles.stream().map(Tile::toString).collect(Collectors.toList()); // list of tile names
        int rowIndex= 0;
        StringBuilder board = new StringBuilder();
        for(int i =0; i<length; i++){ // append every row in the board
            List<String> row = tileStrings.subList(rowIndex,rowIndex+width);
            board.append(row.stream().reduce("", (acc, next) -> acc + next));
            board.append("\n");
            rowIndex+=width;
        }
        return board.toString();
    }
    public void printBoard(){
        printBoardCallback.print(tiles,length,width);
    }
    public boolean validatePos(int x, int y){
        return x< width && x>=0 && y<length && y>=0;
    }
}