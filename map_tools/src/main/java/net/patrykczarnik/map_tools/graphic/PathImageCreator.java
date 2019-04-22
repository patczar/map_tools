package net.patrykczarnik.map_tools.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
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
	private final PathDrawingSettings fSettings;
	
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
	
	public void drawWith(Graphics2D g, PixelPath aPath) {
		final Path2D shape = ImageTools.path2dOfPoints(aPath.getPoints());
		final Stroke stroke = new BasicStroke(fSettings.lineWidth, 1, 1);
		g.setColor(fSettings.lineColor);
		g.setStroke(stroke);
		g.draw(shape);
	}

	public void drawOver(BufferedImage aImg, PixelPath aPath) {
		Graphics2D g = aImg.createGraphics();
		try {
			drawWith(g, aPath);
		} finally {
			g.dispose();
		}
	}

	public BufferedImage drawEmpty(PixelPath aPath) {
		PixelBounds bounds = aPath.getBounds();
		System.out.println("bounds: " + bounds);
		BufferedImage img = new BufferedImage(bounds.getWidth(), bounds.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		try {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, img.getWidth(), img.getHeight());
			g.translate(-bounds.minX, -bounds.minY);
			drawWith(g, aPath);
			return img;
		} finally {
			g.dispose();
		}
	}

}
