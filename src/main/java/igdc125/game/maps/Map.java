package igdc125.game.maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import igdc125.core.Container;
import igdc125.game.Mob;
import igdc125.game.Player;
import igdc125.game.tiles.Tile;

public class Map extends Container {
	public Tile[][] map;
	public Mob player;

	public ArrayList<Mob> mobs = new ArrayList<>();

	public Map() {
		super();
		player = new Player();
	}

	@Override
	public void addChild(Container child) {
		if (child instanceof Mob) {
			mobs.add((Mob) child);
		}
		super.addChild(child);
	}

	@Override
	public void removeChild(Container child) {
		if (child instanceof Mob) {
			mobs.remove((Mob) child);
		}
		super.removeChild(child);
	}

	public void initFromBitmap(BufferedImage bitmap) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		map = new Tile[w][h];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Tile tile = Tile.factory(bitmap.getRGB(i, j), i, j, this);
				map[i][j] = tile;

				if (tile != null) {
					tile.setCell(i, j);
					addChild(tile);
				}
			}
		}

		addChild(player);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		x = 32 - player.x;
		y = 32 - player.y;
	}

	public void removeTile(int atx, int aty) {
		if (map[atx][aty] != null) {
			removeChild(map[atx][aty]);
			map[atx][aty] = null;
		}
	}

	public Tile getTile(int x, int y) {
		if ((x >= 0) && (x < map.length) && (y >= 0) && (y < map[0].length)) {
			return map[x][y];
		}

		return null;

	}
}
