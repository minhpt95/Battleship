package minhpt;

public class Game {
        private Player[] players;

        public Game() {
            players = new Player[]{
                    new Player(1),
                    new Player(2)
            };
        }


        public void start() {
            int i = 0;
            int j = 1;
            int size = players.length;
            while(players[0].getLives() > 0 && players[1].getLives() > 0) {
                players[i++ % size].turnToPlay(players[j++ % size]);

                if (players[0].getLives() == 0){
                    System.out.println("Player 2 Win");
                }else if(players[1].getLives() == 0){
                    System.out.println("Player 1 Win");
                }
            }


        }
}
