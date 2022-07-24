package logic;


public class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {

    }

    public static Position at(int i, int j) {
        return new Position(i,j);
    }
    //TODO: implement this

    public boolean equals(Position pos){
        return x== pos.x && y== pos.y;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //TODO: add logic

    // top left is 0,0

    public int compareTo(Position position) {
        int deltaY = Integer.signum(y-position.y);
        int deltaX = Integer.signum(x- position.x);
        return deltaY == 0?deltaX:deltaY;
    }
    public double range(Position position){
        return Math.sqrt((x-position.x)*(x-position.x)+(y-position.y)*(y-position.y));
    }
    //TODO: this function is not done, need to think about it.
   public Position translate(Action a) {
        Position pos = new Position();
        switch (a) {
           case DOWN->
               pos = new Position(x, y + 1);
           case UP->
               pos = new Position(x, y - 1);
           case RIGHT ->
               pos = new Position(x+1,y);
           case LEFT->
               pos = new Position(x-1,y);
       }
       return pos;
   }
   public String toString(){
        return "("+x +","+y+")";
   }

}
