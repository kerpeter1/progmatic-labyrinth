/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import com.progmatic.labyrinthproject.enums.Direction;
import static com.progmatic.labyrinthproject.enums.Direction.*;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.HashMap;

/**
 *
 * @author Peti 2
 */
public class WallFollowerPlayer implements Player {

    private Direction previousDirection = EAST;
    private static final HashMap<Direction, Direction> NEXT_DIRECTION_RIGHT = new HashMap<>();

    static {
        NEXT_DIRECTION_RIGHT.put(NORTH, EAST);
        NEXT_DIRECTION_RIGHT.put(EAST, SOUTH);
        NEXT_DIRECTION_RIGHT.put(SOUTH, WEST);
        NEXT_DIRECTION_RIGHT.put(WEST, NORTH);
    }

    @Override
    public Direction nextMove(Labyrinth l) {

        if (l.hasPlayerFinished()) {
            return null;
        }

        Direction nextDirection = NEXT_DIRECTION_RIGHT.get(previousDirection);
        nextDirection = NEXT_DIRECTION_RIGHT.get(nextDirection);

        while (1 == 1) {
            nextDirection = NEXT_DIRECTION_RIGHT.get(nextDirection);

            if (l.possibleMoves().contains(nextDirection)) {
                previousDirection = nextDirection;
                return nextDirection;
            }
        }
    }

}
