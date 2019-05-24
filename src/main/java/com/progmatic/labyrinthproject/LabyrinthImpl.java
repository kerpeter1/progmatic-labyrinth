package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    //1st dim: column, 2nd dim:row
    private CellType[][] labyrinth;
    Coordinate playerPosition;

    public LabyrinthImpl() {

    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());

            setSize(width, height);

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            setCellType(new Coordinate(ww, hh), CellType.WALL);
                            break;
                        case 'E':
                            setCellType(new Coordinate(ww, hh), CellType.END);
                            break;
                        case 'S':
                            setCellType(new Coordinate(ww, hh), CellType.START);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        } catch (CellException ex) {
            Logger.getLogger(LabyrinthImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getWidth() {
        return labyrinth == null ? -1 : labyrinth.length;
    }

    @Override
    public int getHeight() {
        return labyrinth == null ? -1 : labyrinth[0].length;
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getRow() < 0 || c.getRow() >= getHeight()
                || c.getCol() < 0 || c.getCol() >= getWidth()) {
            throw new CellException(c, "Out of labyrinth bounds exception");
        }
        return labyrinth[c.getCol()][c.getRow()];
    }

    @Override
    public void setSize(int width, int height) {
        labyrinth = new CellType[width][height];

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                labyrinth[i][j] = CellType.EMPTY;
            }
        }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {

        getCellType(c);
        labyrinth[c.getCol()][c.getRow()] = type;

        if (type == CellType.START) {
            playerPosition = new Coordinate(c.getCol(), c.getRow());
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {
        try {
            return getCellType(playerPosition) == CellType.END;
        } catch (CellException ex) {
            Logger.getLogger(LabyrinthImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> dir = new ArrayList<>();
        int col = playerPosition.getCol();
        int row = playerPosition.getRow();

        try {
            if (row > 0 && getCellType(new Coordinate(col, row - 1)) == CellType.EMPTY) {
                dir.add(Direction.NORTH);
            }
            if (col > 0 && getCellType(new Coordinate(col - 1, row)) == CellType.EMPTY) {
                dir.add(Direction.WEST);
            }
            if (row < getHeight() - 1 && getCellType(new Coordinate(col, row + 1)) == CellType.EMPTY) {
                dir.add(Direction.SOUTH);
            }
            if (col < getWidth() - 1 && getCellType(new Coordinate(col + 1, row)) == CellType.EMPTY) {
                dir.add(Direction.EAST);
            }
        } catch (CellException ex) {
            Logger.getLogger(LabyrinthImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dir;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        int col = playerPosition.getCol();
        int row = playerPosition.getRow();
        List<Direction> p = possibleMoves();

        if (p.contains(direction)) {
            switch (direction) {
                case NORTH:
                    playerPosition = new Coordinate(col, row - 1);
                    break;
                case WEST:
                    playerPosition = new Coordinate(col - 1, row);
                    break;
                case SOUTH:
                    playerPosition = new Coordinate(col, row + 1);
                    break;
                case EAST:
                    playerPosition = new Coordinate(col + 1, row);
                    break;
            }

        } else {
            throw new InvalidMoveException();
        }
    }

}
