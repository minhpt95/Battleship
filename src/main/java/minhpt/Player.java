package minhpt;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {

    private int id;
    private int lives;
    private Board board;
    private Map<Point,Boolean> targetHistory;
    private Scanner scanner;

    //set up variable of ship for player
    public Player(int id) {
        System.out.printf("%n=== Setting up everything for Player %s ====", id);
        this.id = id;
        this.board = new Board(id);
        this.lives = Constants.PLAYER_LIVES;
        this.targetHistory = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLive(){
        lives--;
    }

    //input position want to fire
    public void turnToPlay(Player opponent) {
        System.out.printf("%n%nPlayer %d, Choose coordinates you want to shoot : ", id);
        System.out.println();

        System.out.print("X : ");
        int x = scanner.nextInt();
        System.out.print("Y : ");
        int y = scanner.nextInt();

        Point point = new Point(x, y);

        while(targetHistory.get(point) != null) {
            System.out.println("This position has already been fried");
            System.out.print("X : ");
            x = scanner.nextInt();
            System.out.print("Y : ");
            y = scanner.nextInt();

            point = new Point(x, y);
        }
        attack(point, opponent);
    }

    //attack !!! X when hit , M when miss
    private void attack(Point point, Player opponent) {
        Ship ship = opponent.board.targetShip(point);

        boolean isShipHit = (ship != null) ? true : false;

        if(isShipHit) {
            ship.shipWasHit();
            opponent.decreaseLive();
        }

        targetHistory.put(point, isShipHit);

        System.out.printf("Player %d, targets (%d, %d)",
                id,
                (int)point.getX(),
                (int)point.getY());

        System.out.println("...and " + ((isShipHit) ? "HITS!" : "misses..."));
    }



}



