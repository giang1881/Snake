package server;


import java.util.ArrayList;
// import java.util.Collections;
import java.util.Random;

public class Game {
    public static final int WIDTH = 64;
	public static final int HEIGHT = 36;
	public static final int FRUITS = 3;
	public static final int TICK = 100; //speed
	
	public int field[][] = new int[WIDTH][HEIGHT];
	// public ArrayList<Player> players = new ArrayList<>();
    public Player p;

	private Random rnd = new Random();
	public ArrayList<Integer> fruitsX = new ArrayList<>();
	public ArrayList<Integer> fruitsY = new ArrayList<>();
	public ArrayList<Integer> solidsX = new ArrayList<>();
	public ArrayList<Integer> solidsY = new ArrayList<>();
	public int highscore = 0;
	public boolean deadPlayersBecomeSolids = false;

    public Game() {
		
		for (int i = 0; i < FRUITS; i++) {
			fruitsX.add(rnd.nextInt(WIDTH));
			fruitsY.add(rnd.nextInt(HEIGHT));
		}
		
	}
	
    public void update() {

		move();
		// checkCollisions();
		// checkSolids();
		checkFruits();
		
		updateField();
		
	}

    private void move() {
		
		// for (Player p : players) {
		// 	if (p == null) continue;
		// 	p.move();
		// }

        p.move();
		
	}

    private void checkFruits() {        
        for (int i = 0; i < FRUITS; i++) {
            
            if (p.segmentsX.get(0) == fruitsX.get(i) && p.segmentsY.get(0) == fruitsY.get(i)) {
                
                fruitsX.set(i, rnd.nextInt(WIDTH));
                fruitsY.set(i, rnd.nextInt(HEIGHT));
                
                p.score++;                
            }
            
        }
			
	}

    private void updateField() {
		
		field = new int[WIDTH][HEIGHT];

		// solids
		for (int i = 0; i < solidsX.size(); i++) {
			
			// safezone
			if (solidsX.get(i) < 3 && solidsY.get(i) < 3) {
				solidsX.remove(i);
				solidsY.remove(i);
				continue;
			}
			
			field[solidsX.get(i)][solidsY.get(i)] = -1;
		}
		
		// fruits
		for (int i = 0; i < FRUITS; i++) {
			field[fruitsX.get(i)][fruitsY.get(i)] = 1;
		}
		
		// players
			
			// segments
        for (int i = 0; i < p.segmentsX.size(); i++) {
            field[p.segmentsX.get(i)][p.segmentsY.get(i)] = p.id;
        }
        
        // head
        field[p.segmentsX.get(0)][p.segmentsY.get(0)] = -p.id;
			
	}

}