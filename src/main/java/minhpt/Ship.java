package minhpt;

public class Ship {
    private String name;
    private int size;
    private int livesLeft;
    private boolean isSunk;
    private Position position1;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.livesLeft = size;
        this.isSunk = false;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setPosition(Position position) {
        this.position1 = position;
    }

    public Position getPosition() {
        return position1;
    }

    public void shipWasHit() {
        if(livesLeft == 0) {
            isSunk = true;
            System.out.println("You sunk the " + name);
            return;
        }
        livesLeft--;
    }

}
