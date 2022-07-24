package UI;

import Managers.GameManager;
import Managers.LevelManager;
import logic.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GameInit {
    protected static final ArrayList<Integer> players = new ArrayList<>(){{
        addAll(Arrays.asList(1,2,3,4,5,6,7));
    }};

    public static void main (String[] args){
        if(args.length != 1){
            throw new IllegalArgumentException("add one file directory of game levels");
        }
        TileFactory tileFactory = new TileFactory();
        CLI cli = new CLI();
        CMDInputProvider inputProvider = new CMDInputProvider();
        GameManager gameManager = new GameManager((s)->cli.print(ConsoleColors.GREEN_BOLD_BRIGHT,s));
        File folder = new File(args[0]);
        //List<File> files = Arrays.stream(Objects.requireNonNull(folder.listFiles())).toList(); // get files from folder
        File[] fileList = folder.listFiles();
        Arrays.sort(fileList); // sort files
        List<File> files = Arrays.stream(fileList).toList(); // convert to list
        cli.printInfo(tileFactory.listPlayers()); // prints info to cmd
        int playerIndex = inputProvider.getIntegerInput();//get player choice;
        while(!players.contains(playerIndex))
            playerIndex = inputProvider.getIntegerInput();

        playerIndex--;
        LinkedList<LevelManager> levelManagers = new LinkedList<>(); // create a new list of levelmanagers
        files = files.subList(1, files.size());//for tests
        for(File file:files){
            LevelManager levelManager = new LevelManager();
            List<Enemy> enemies = new ArrayList<>();
            List<List<Character>> chars = readLines(file);
          //  Character[][]  charsArr = chars.stream().map(u -> u.toArray(new Character[0])).toArray(Character[][]::new); // convert to 2d array
            Tile[][] tiles = new Tile[chars.size()][chars.get(0).size()];
            for(int i =0; i< tiles.length; i++){
                for(int j=0; j<tiles[0].length; j++){
                    char tile = chars.get(i).get(j);
                    switch (tile) {
                        case '@' -> tiles[i][j] = tileFactory.producePlayer(playerIndex, Position.at(j, i), levelManager, cli, gameManager,inputProvider);
                        case '#' -> tiles[i][j] = tileFactory.produceWall(Position.at(j, i));
                        case '.' -> tiles[i][j] = tileFactory.produceEmpty(Position.at(j, i));
                        default -> {
                            Enemy e = tileFactory.produceEnemy(tile, Position.at(j, i), levelManager, cli);
                            tiles[i][j] = e;
                            enemies.add(e);
                        }
                    }
                }
            }

            GameBoard gameBoard = new GameBoard(tiles, tiles.length, tiles[0].length);
            gameBoard.setPrintBoardCallback(cli::printBoard);
            levelManager.initLevelManager(gameBoard,enemies);
            levelManagers.addLast(levelManager);

        }

        gameManager.initializeGameManager(levelManagers);
        gameManager.initGame();



    }
    //private Tile[][] fileToBoard(File file){ // need to implement


    //}
    private static List<List<Character>> readLines(File file){
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file.getPath()));
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        List<List<Character>> chars = new ArrayList<>();
        for(String line: lines){
            chars.add(line.chars().mapToObj(e->(char)e).collect(Collectors.toList()));
        }


        return chars;

    }


    /*
    public Object[] fileTranslator (char[][] map){
        int length=map.length;
        int width=map[0].length;
        Tile[][] tiles=new Tile[length][width]; //to confirm order(?)

        for (int i=0; i<length; i++){
            for (int j=0; j<width; j++){
                Position pos=new Position(j,i);
                char currPos=map[j][i];
                switch (currPos){
                    case '#':
                        tiles[j][i]=new Wall();
                        break;
                    case '.':
                        tiles[j][i]=new Empty();
                        break;
                    case '@':
                        //tiles[j][i]=which player
                }

            }
        }


    }


     */
    private int lengthOfMap(String map){
        int length=0;
        char c=map.charAt(length);
        while (c!='\n'){
            length++;
            c=map.charAt(length);
        }
        return length;
    }


}