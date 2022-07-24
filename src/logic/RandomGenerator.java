package logic;

import java.util.Random;

public class RandomGenerator extends AbstractGenerator{


    @Override
    int nextInt(int x) {
        return new Random().nextInt(x);
    }
}
