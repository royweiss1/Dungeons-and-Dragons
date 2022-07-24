package logic;

import UI.ConsoleColors;

public class Wall extends Tile{
    public Wall() {
        super('#');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
