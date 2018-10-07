package minhpt;

import java.awt.Point;
import java.util.Scanner;


public class Board {
    private char[][] board;

    //Create ship
    private Ship ship = new Ship("Cruiser", Constants.CRUISER_SIZE);

    //Create Map Game
    public Board(int id) {
        board = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            for(int j = 0; j < Constants.BOARD_SIZE; j++) {
                board[i][j] = Constants.BOARD_ICON;
            }
        }
        placeShipsOnBoard(id);
    }

    //Target ship opponent
    public Ship targetShip(Point point) {
        boolean isHit = false;
        Ship hitShip = null;

        if (ship.getPosition() != null) {
            if (Utils.isPointBetween(point, ship.getPosition())) {
                isHit = true;
                hitShip = ship;


            }
        }

        final char result = isHit ? Constants.SHIP_IS_HIT_ICON : Constants.SHOT_MISSED_ICON;
        updateShipOnBoard(point, result);
        printBoard();

        return (isHit) ? hitShip : null;
    }

    //Place ship on the sea (Map game)
    private void placeShipsOnBoard(int id) {
        System.out.printf("%nAlright - Time to place out your ships%n%n");
        Scanner s = new Scanner(System.in);
        Ship ship = this.ship;
        boolean isShipPlacementLegal = false;

        System.out.printf("%nEnter position of %s (length  %d): ", ship.getName(), ship.getSize());
        System.out.println();

        while(!isShipPlacementLegal) {
            try {
                System.out.print("Enter Start Point Of Ship (X) : ");
                int xStartPoint = s.nextInt();
                System.out.print("Enter Start Point Of Ship (Y) : ");
                int yStartPoint = s.nextInt();
                System.out.print("Enter End Point Of Ship (X) : ");
                int xEndPoint = s.nextInt();
                System.out.print("Enter End Point Of Ship (Y) : ");
                int yEndPoint = s.nextInt();

                Point from = new Point(xStartPoint, yStartPoint);
                Point to = new Point(xEndPoint, yEndPoint);


                while(ship.getSize() != Utils.distanceBetweenPoints(from, to)) {
                    System.out.printf("The ship currently being placed on the board is of length: %d. Change your coordinates and try again",
                            ship.getSize());
                    System.out.println();

                    System.out.print("Enter Start Point Of Ship (X) : ");
                    xStartPoint = s.nextInt();
                    System.out.print("Enter Start Point Of Ship (Y) : ");
                    yStartPoint = s.nextInt();
                    System.out.print("Enter End Point Of Ship (X) : ");
                    xEndPoint = s.nextInt();
                    System.out.print("Enter End Point Of Ship (Y) : ");
                    yEndPoint = s.nextInt();

                    from = new Point(xStartPoint, yStartPoint);
                    to = new Point(xEndPoint, yEndPoint);
                }

                Position position = new Position(from, to);

                    drawShipOnBoard(position);
                    ship.setPosition(position);
                    isShipPlacementLegal = true;

            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid coordinates - Outside board");
            }
        }
    }

    //Update status ship on board
    private void updateShipOnBoard(Point point, final char result) {
        int x = (int) point.getX() - 1;
        int y = (int) point.getY() - 1;
        board[y][x] = result;
    }

    //Draw ship on board
    private void drawShipOnBoard(Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();
        for(int i = (int) from.getY() - 1; i < to.getY(); i++) {
            for(int j = (int) from.getX() - 1; j < to.getX(); j++) {
                board[i][j] = Constants.SHIP_ICON;
            }
        }
        printBoard();
    }

    //Show board
    private void printBoard() {
        System.out.print("\t");

        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            System.out.print(Constants.BOARD_LETTERS[i] + "\t");
        }

        System.out.println();

        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            System.out.print((i+1) + "\t");
            for(int j = 0; j < Constants.BOARD_SIZE; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

}