package logic;

public class NumberGenerator extends AbstractGenerator{

    @Override
    int nextInt(int x) {
        return x-1;
    }
}
