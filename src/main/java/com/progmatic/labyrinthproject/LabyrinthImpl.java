package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    //1st dim: row, 2nd dim:column
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

            labyrinth = new CellType[height][width];
            System.out.println("ok");

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            setCellType(new Coordinate(hh, ww), CellType.WALL);
                            break;
                        case 'E':
                            setCellType(new Coordinate(hh, ww), CellType.END);
                            break;
                        case 'S':
                            setCellType(new Coordinate(hh, ww), CellType.START);
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
        return labyrinth == null ? -1 : labyrinth[0].length;
    }

    @Override
    public int getHeight() {
        return labyrinth == null ? -1 : labyrinth.length;
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getRow() < 0 || c.getRow() >= getHeight()
                || c.getCol() < 0 || c.getCol() >= getWidth()) {
            throw new CellException(c, "Out of labyrinth bounds exception");
        }
        return labyrinth[c.getRow()][c.getCol()];
    }

    @Override
    public void setSize(int width, int height) {
        labyrinth = new CellType[height][width];

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                labyrinth[i][j] = CellType.EMPTY;
            }
        }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {

        getCellType(c);
        labyrinth[c.getRow()][c.getCol()] = type;

        if (type == CellType.START) {
            playerPosition = new Coordinate(c.getRow(), c.getCol());
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasPlayerFinished() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Direction> possibleMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
