package net.patrykczarnik.map_tools.repository;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.patrykczarnik.map_tools.osm.OSMTile;

class TryFileTileProvider {

	public static void main(String[] args) {
		try {
			FileTileProvider fileTileProvider = FileTileProvider.withPathPattern("/home/patryk/osm/Polska/tile%02d_%04d_%04d.png");
			OSMTile tile = OSMTile.ofCoordinates(7, 70, 40);
			BufferedImage image = fileTileProvider.getImageForTile(tile);
			JLabel label = new JLabel(new ImageIcon(image));
			JOptionPane.showMessageDialog(null, label, ""+tile, JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
