package logic;

public class Empty extends Tile{
    public Empty() {
        super('.');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);

    }
}
