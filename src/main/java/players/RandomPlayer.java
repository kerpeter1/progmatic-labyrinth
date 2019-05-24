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

/**
 *
 * @author Peti 2
 */
public class RandomPlayer implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {

        if (l.hasPlayerFinished()) {
            return null;
        }

        Direction direction = null;

        do {
            switch ((int) (Math.random() * 4)) {
                case 0:
                    direction = NORTH;
                    break;
                case 1:
                    direction = WEST;
                    break;
                case 2:
                    direction = SOUTH;
                    break;
                case 3:
                    direction = EAST;
                    break;
            }

            if (l.possibleMoves().contains(direction)) {
                return direction;
            }
        } while (1 == 1);
    }
}
