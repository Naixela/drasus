package view;

import java.util.Vector;

import controller.*;
import model.*;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

import view.Tile.FIELD;

public class GamePlayState extends BasicGameState {

	private enum STATES {
		START_GAME, NEW_UNIT, START_TURN, PLAY_TURN, END_TURN, SELECTING_UNIT, PAUSE_GAME, GAME_OVER
	}

	private TiledMap grassMap;
	private int stateID = -1;
	private STATES currentState = null;
	private MainController main;
	private Tile currentSelected = null;
	private Vector<Tile> tiles;
	private Image Archer = null;
	private Image ArcherMonte = null;
	private Image Berserker = null;
	private Image Bretteur = null;
	private Image Cavalier = null;
	private Image Chevalier = null;
	private Image Eclaireur = null;
	private Image Fantassin = null;
	private Image Lancier = null;
	private Image Rodeur = null;
	private Image Tank = null;
	private Image Target = null;
	private int unitNb = 0;
	private String[][] taba;
	private String[][] tabb;
	private Vector<Tile> highLight = new Vector<Tile>();

	public GamePlayState(int stateID) {
		this.stateID = stateID;
		currentState = STATES.START_GAME;
		tiles = new Vector<Tile>();
		main = MainController.getInstance();
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Archer = new Image("res/sprites/Archer.png");
		ArcherMonte = new Image("res/sprites/Archermonte.png");
		Berserker = new Image("res/sprites/Berserk.png");
		Bretteur = new Image("res/sprites/Bretteur.png");
		Cavalier = new Image("res/sprites/Cavalier.png");
		Chevalier = new Image("res/sprites/Chevalier.png");
		Eclaireur = new Image("res/sprites/Eclaireur.png");
		Fantassin = new Image("res/sprites/Fantassin.png");
		Lancier = new Image("res/sprites/Lancier.png");
		Rodeur = new Image("res/sprites/Rodeur.png");
		Tank = new Image("res/sprites/Tank.png");
		Target = new Image("res/sprites/path.png");
		grassMap = new TiledMap("res/drasus.tmx");

		/* Init Vector tiles */

		for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
				int tileID = grassMap.getTileId(xAxis, yAxis, 0);
				boolean block = false;

				String value = grassMap.getTileProperty(tileID, "blocked",
						"false");
				if ("true".equals(value)) {
					block = true;
				}
				value = grassMap.getTileProperty(tileID, "field", "default");
				switch (value) {
				case "default":
					tiles.addElement(new Tile(xAxis, yAxis, block));
					break;
				case "grass":
					tiles.addElement(new Tile(xAxis, yAxis, block, FIELD.GRASS));
					break;
				case "forest":
					tiles.addElement(new Tile(xAxis, yAxis, block, FIELD.FOREST));
					break;
				case "mountain":
					tiles.addElement(new Tile(xAxis, yAxis, block,
							FIELD.MOUNTAIN));
					break;
				case "fort":
					tiles.addElement(new Tile(xAxis, yAxis, block, FIELD.FORT));
					break;
				case "bridge":
					tiles.addElement(new Tile(xAxis, yAxis, block, FIELD.BRIDGE));
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2)
			throws SlickException {
		grassMap.render(0, 0);

		if (highLight.isEmpty() == false) {
			for (Tile t : highLight) {
				Target.draw(t.x * 32, t.y * 32);
			}
		}

		taba = main.aToTab();
		for (int i = 0; i < taba.length; i++) {
			switch (taba[i][0]) {
			case "Archer":
				Archer.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "ArcherMonte":
				ArcherMonte.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Berserker":
				Berserker.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Bretteur":
				Bretteur.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Cavalier":
				Cavalier.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Chevalier":
				Chevalier.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Eclaireur":
				Eclaireur.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Fantassin":
				Fantassin.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Lancier":
				Lancier.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Rodeur":
				Rodeur.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			case "Tank":
				Tank.draw(Integer.parseInt(taba[i][1]) * 32,
						Integer.parseInt(taba[i][2]) * 32);
				break;
			}

		}

		tabb = main.bToTab();
		for (int i = 0; i < tabb.length; i++) {
			switch (tabb[i][0]) {
			case "Archer":
				Archer.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "ArcherMonte":
				ArcherMonte.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Berserker":
				Berserker.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Bretteur":
				Bretteur.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Cavalier":
				Cavalier.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Chevalier":
				Chevalier.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Eclaireur":
				Eclaireur.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Fantassin":
				Fantassin.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Lancier":
				Lancier.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Rodeur":
				Rodeur.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			case "Tank":
				Tank.draw(Integer.parseInt(tabb[i][1]) * 32,
						Integer.parseInt(tabb[i][2]) * 32);
				break;
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		switch (currentState) {
		case START_GAME:
			currentState = STATES.NEW_UNIT;
			autoGenerateBUnits();
			break;
		case NEW_UNIT:
			newUnit(gc, sbg, delta);
			break;
		case START_TURN:
			initTurn();
			currentState = STATES.PLAY_TURN;
		case PLAY_TURN:
			playTurn(gc, sbg, delta);
		case SELECTING_UNIT:
			selectingUnit(gc, sbg, delta);
			break;
		case END_TURN:
			currentSelected = null;
			System.out.println("fin du tour");
			// currentState = STATES.PAUSE_GAME;
			break;
		case PAUSE_GAME:
			break;
		case GAME_OVER:
			sbg.enterState(ViewController.MAINMENUSTATE);
			break;
		}

	}

	private void initTurn()
	{
		main.initNewTurn();
	}
	private void newUnit(GameContainer gc, StateBasedGame sbg, int delta) {
		Tile tile = getTileClicked(gc);
		if (tile != null) // si clic
			if (tile.isBlocked() == false && main.isFreeTileset(tile)) {
				main.addUnit(main.getPlayerA().getNom()[unitNb], tile);
				System.out.print("Ajout :");
				System.out.println(main.getPlayerA().getNom()[unitNb]);
				unitNb++;
			} else
				System.out.println("Impossible de placer une unit� ici");

		// else System.out.println("null");
		// si il reste des unit�s � placer
		currentState = STATES.NEW_UNIT;
		// sinon
		if (unitNb == main.getPlayerA().getNom().length)
			currentState = STATES.START_TURN;

	}

	private void playTurn(GameContainer gc, StateBasedGame sbg, int delta) {
		Tile tile = getTileClicked(gc);
		if (tile != null) {
			if (main.isPlayerAUnit(tile)) {
				currentState = STATES.SELECTING_UNIT;
				currentSelected = tile;
				setHighLight(main.canCross(tiles, currentSelected, main.getPlayerA().getUnit(currentSelected.x,
						currentSelected.y).getMove()));
				//setHighLight(main.canCross(tiles, currentSelected, 6));
			}
		} else if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			currentState = STATES.END_TURN;
		}

		// autres...
	}

	private void selectingUnit(GameContainer gc, StateBasedGame sbg, int delta) {
		Tile tile = getTileClicked(gc);
		if (tile != null) {
			// on clique sur une unit� de B
			if (main.isPlayerBUnit(tile)) {
				System.out.println(main.attack(currentSelected, tile));
				// Si tout c'est bien pass� on r�initialise l'�tat
				currentState = STATES.PLAY_TURN;

			}
			// si on clic que une case vide d�placement
			if (main.distance(currentSelected, tile) <= main.getPlayerA()
					.getUnit(currentSelected.x, currentSelected.y).getMove()) {
				if (main.isFreeTileset(tile) && tile.isBlocked() == false) {

					main.getPlayerA()
							.getUnit(currentSelected.x, currentSelected.y)
							.setTile(tile);
				} else {
					System.out.println("La case n'est pas libre");
				}
			} else {
				System.out.println("C'est trop loin");
			}
			highLight.clear();
			currentState = STATES.PLAY_TURN;

		}
	}

	private void highLight(Unit u) {
		for (Tile t : tiles) {
			if (main.distance(t, currentSelected) <= u.getMove()) {
				System.out.println("try");
				highLight.add(t);
			}
		}
	}

	private void setHighLight(Vector<Tile> hl) {
		highLight = hl;
	}

	private Tile getTileClicked(GameContainer gc) {
		Input input = gc.getInput();
		if (input.isMousePressed(0)) {

			int mouseX = input.getMouseX();
			int mouseY = input.getMouseY();
			int x = 0;
			int y = 0;

			x = mouseX / grassMap.getTileWidth();
			y = mouseY / grassMap.getTileHeight();
			System.out.println(mouseX + " x : " + x);
			System.out.println(mouseY + " y : " + y);

			for (Tile t : tiles) {
				if (t.x == x && t.y == y)
					return t;
			}
		}
		return null;
	}
	
	private void autoGenerateBUnits()
	{
		main.addUnitToB("Eclaireur", tiles.get(800));
		main.addUnitToB("Fantassin", tiles.get(801));
		main.addUnitToB("Tank", tiles.get(802));
		main.addUnitToB("Archer", tiles.get(803));
	}

}
