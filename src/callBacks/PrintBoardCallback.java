package callBacks;

import logic.Tile;

import java.util.List;

public interface PrintBoardCallback {
    void print(List<Tile> t,int length,int width);
}
