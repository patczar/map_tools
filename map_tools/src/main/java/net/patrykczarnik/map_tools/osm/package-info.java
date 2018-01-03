/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * This package contains code related to web map engines such like Open Street Map (<a href="http://openstreetmap.org">OSM</a>) or other based on the same principals:
 * <ul>
 * <li>map is based on the <a href="http://en.wikipedia.org/wiki/Mercator_projection">Mercator projection</a>,
 * 	<ul>
 * 	<li>the proportions between vertical and horizontal distances (in other words, the angles) are preserved,
 * 	<li>but not distances themselves nor areas,
 * 	</ul>
 * <li>the whole map is inscribed within a regular square which implies that the map is restricted to latitude of about ±85°,
 * <li>the map image is divided into tiles; the lowest scale (0) consists of a single square tile,
 * 	higher scales are defined expotentially:
 * <ul>
 * 	<li>each next scale is double of its previous scale;
 * 	<li>in 2D it means that a tile in lower scale consists of 4 tiles in higher scale,
 * </ul>
 * <li>a tile in each scale consists of the same number of pixels; we also assume that tiles are regular squares,
 * <li>URLs or file paths for tiles can be established basing on 3 coordinates: the scale, horizontal and vertical position.
 * </ul>
 */
package net.patrykczarnik.map_tools.osm;