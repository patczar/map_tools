package net.patrykczarnik.map_tools.graphic;

import java.awt.Color;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.patrykczarnik.map_tools.exn.FileProcessingException;

@XmlRootElement(name = "path-drawing-settings")
public class PathDrawingSettings {
	@XmlElement(name = "frame-width")
	public final int frameWidth;
	@XmlElement(name = "frame-height")
	public final int frameHeight;
	@XmlElement(name = "line-width")
	public final float lineWidth;
	@XmlElement(name = "line-color")
	@XmlJavaTypeAdapter(JAXBColorAdapter.class)
	public final Color lineColor;
	@XmlElement(name = "point-radius")
	public final float pointRadius;
	@XmlElement(name = "point-color")
	@XmlJavaTypeAdapter(JAXBColorAdapter.class)
	public final Color pointColor;
	@XmlElement(name = "minimal-distance")
	public final double minimalDistance;
	@XmlElement(name = "maximal-distance")
	public final Double maximalDistance;
	@XmlElement(name = "points-behind")
	public final int pointsBehind;

	public PathDrawingSettings() {
		this.frameWidth = 1280;
		this.frameHeight = 720;
		this.lineWidth = 3.0F;
		this.lineColor = Color.RED;
		this.pointRadius = 4.0F;
		this.pointColor = Color.BLUE;
		this.minimalDistance = 1.0D;
		this.maximalDistance = null;
		this.pointsBehind = 1;
	}

	public PathDrawingSettings(int aFrameWidth, int aFrameHeight, float aLineWidth, Color aLineColor,
			float aPointRadius, Color aPointColor, double aMinimalDistance, Double aMaximalDistance,
			int aPointsBehind) {
		this.frameWidth = aFrameWidth;
		this.frameHeight = aFrameHeight;
		this.lineWidth = aLineWidth;
		this.lineColor = aLineColor;
		this.pointRadius = aPointRadius;
		this.pointColor = aPointColor;
		this.minimalDistance = aMinimalDistance;
		this.maximalDistance = aMaximalDistance;
		this.pointsBehind = aPointsBehind;
	}

	public static PathDrawingSettings readFromFile(File aFile) throws FileProcessingException {
		try {
			JAXBContext ctx = JAXBContext.newInstance(PathDrawingSettings.class);
			Unmarshaller u = ctx.createUnmarshaller();
			return (PathDrawingSettings) u.unmarshal(aFile);
		} catch (JAXBException var3) {
			throw new FileProcessingException("Exception during JAXB unmarshalling.", var3);
		}
	}	
	
	public static class MyColor {
		@XmlAttribute(required = true)
		public int red;
		@XmlAttribute(required = true)
		public int green;
		@XmlAttribute(required = true)
		public int blue;
		@XmlAttribute(required = false)
		public Integer alpha;

		public Color asAwtColor() {
			int a = this.alpha != null ? this.alpha : 255;
			return new Color(this.red, this.green, this.blue, a);
		}

		public static MyColor ofAwtColor(Color aColor) {
			MyColor result = new MyColor();
			result.red = aColor.getRed();
			result.green = aColor.getGreen();
			result.blue = aColor.getBlue();
			result.alpha = aColor.getAlpha();
			return result;
		}
	}

	static class JAXBColorAdapter extends XmlAdapter<MyColor, Color> {
		public Color unmarshal(MyColor aMyColor) {
			return aMyColor.asAwtColor();
		}

		public MyColor marshal(Color aColor) {
			return MyColor.ofAwtColor(aColor);
		}
	}
}
