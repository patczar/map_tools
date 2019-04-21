package net.patrykczarnik.map_tools.graphic;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

/**
 @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * Object of this class serves for drawing paths (tracks) on maps.
 *
 */
public class PathImageCreator {
	private PathDrawingSettings fSettings;
	
	private PathImageCreator(PathDrawingSettings aSettings) {
		fSettings = aSettings;
	}

	public static PathImageCreator withSettings(PathDrawingSettings aSettings) {
		return new PathImageCreator(aSettings);
	}

	public static PathImageCreator withDefaultSettings() {
		PathDrawingSettings settings = new PathDrawingSettings();
		return withSettings(settings);
	}
	
	public void drawOver(BufferedImage aImg, PixelPath aPath) {
		Graphics2D g = aImg.createGraphics();
		Path2D shape = ImageTools.path2dOfPoints(aPath.getPoints());
		Stroke stroke = new BasicStroke(fSettings.lineWidth, 1, 1);
		g.setColor(fSettings.lineColor);
		g.setStroke(stroke);
		g.draw(shape);
		g.dispose();
	}

}
