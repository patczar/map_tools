package net.patrykczarnik.map_tools.graphic;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public final class ImageTools {
	private ImageTools() {
	}

	public static BufferedImage read(String aPath) throws IOException {
		return ImageTools.read(new File(aPath));
	}

	public static BufferedImage read(File aFile) throws IOException {
		return ImageIO.read(aFile);
	}

	public static void write(BufferedImage aImg, String aPath) throws IOException {
		final String formatName = aPath.substring(aPath.length() - 3);
		ImageIO.write(aImg, formatName, new File(aPath));
	}

	public static void write(BufferedImage aImg, File aFile) throws IOException {
		final String fileName = aFile.getName();
		final String formatName = fileName.substring(fileName.length() - 3);
		ImageIO.write(aImg, formatName, aFile);
	}

	public static BufferedImage copy(BufferedImage aImg) {
		BufferedImage newImg = new BufferedImage(aImg.getWidth(), aImg.getHeight(), aImg.getType());
		newImg.setData(aImg.getData());
		return newImg;
	}

	public static BufferedImage cropCopy(BufferedImage aImg, int aX, int aY, int aWidth, int aHeight) {
		BufferedImage newImg = new BufferedImage(aWidth, aHeight, aImg.getType());
		Graphics2D g = newImg.createGraphics();
		g.drawImage(aImg.getSubimage(aX, aY, aWidth, aHeight), 0, 0, aWidth, aHeight, null);
		g.dispose();
		return newImg;
	}

	public static Path2D path2dOfPoints(List<Point> aPoints) {
		Path2D.Float path2d = new Path2D.Float();
		boolean first = true;
		for (Point point : aPoints) {
			if (first) {
				path2d.moveTo(point.getX(), point.getY());
				first = false;
				continue;
			}
			path2d.lineTo(point.getX(), point.getY());
		}
		return path2d;
	}
}
