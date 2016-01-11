package material;

import geometries.Hit;
import light.Light;
import ray.World;
import textures.Texture;
import Matrizen_Vektoren_Bibliothek.Point3;
import Matrizen_Vektoren_Bibliothek.Vector3;
import application.Tracer;
import color.Color;

/**
 * PhongMaterial
 *
 * @author Waschmaschine
 *         <p>
 *         Das PhongMaterial erbt von der abstrakten Klasse Material und
 *         überschreibt die Methode colorFor. Es reflektiert das Licht diffus
 *         und errechnet zugleich einen für den betrachter sichtbaren
 *         Reflektionspunkt auf der Oberfläche des Materials.
 */

public class PhongMaterial extends Material {
	private final Texture diffuse;
	private final Texture specular;
	private final int exponent;

	/**
	 * Konstruktor: PhongMaterial
	 *
	 * @param diffuse
	 *            Farbe der diffusen Reflektion
	 * @param specular
	 *            Farbe des Reflektionspunktes
	 * @param exponent
	 *            der Exponent bestimmt die größe des errechneten
	 *            Reflektionspunktes
	 * @throws IllegalArgumentException
	 */

	public PhongMaterial(Texture diffuse, Texture specular, int exponent) throws IllegalArgumentException {
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	/**
	 * Method: colorFor(Color)
	 *
	 * @param hit
	 *            : übergebenes hit - Objekt
	 * @param world
	 *            : übergebenes world - Objekt
	 * @param tracer
	 *            : übergebenes tracer - Objekt
	 * @return color - für jeden Pixel wird, falls er von der Lichtquelle
	 *         angeleuchtet wird, die Farbe errechnet und zurück gegeben.
	 * @throws IllegalArgumentException
	 */

	@Override
	public Color colorFor(Hit hit, World world, Tracer tracer) {

		final Color returnColor = diffuse.colorFor(hit.tex.u, hit.tex.v).mul(world.ambient);
		final Vector3 e = (hit.ray.direction.mul(-1.0)).normalized();
		final Point3 hitPoint = hit.ray.at(hit.t);
		Color lightColor = new Color(0, 0, 0);
		for (final Light light : world.lights) {

			if (light.illuminates(hit.ray.at(hit.t), world)) {

				// final Color lightColor = light.color;
				//
				// final Vector3 lightVector =
				// light.directionFrom(hit.ray.at(hit.t)).normalized();
				//
				// final Vector3 reflectedVector =
				// lightVector.reflectedOn(hit.n);
				//
				// final Vector3 e = (hit.ray.direction.mul(-1));
				//
				// final double max = Math.max(0.0, lightVector.dot(hit.n));
				//
				// returnColor =
				// returnColor.add(diffuse.mul(lightColor).mul(max));
				//
				// returnColor =
				// returnColor.add(specular.mul(lightColor.mul(Math.pow(Math.max(0.0,
				// reflectedVector.dot(e)), exponent))));
				// }
				final Vector3 lightVector = light.directionFrom(hitPoint).normalized();

				final Vector3 reflectedVector = lightVector.reflectedOn(hit.n);

				final double max = Math.max(0.0, lightVector.dot(hit.n));
				final double maxSP = Math.pow(Math.max(0.0, reflectedVector.dot(e)), this.exponent);

				lightColor = returnColor.add(light.color.mul(diffuse.colorFor(1, 1)).mul(max)).add(
						light.color.mul(specular.colorFor(1, 1)).mul(maxSP));
				// returnColor = returnColor.add(light.color.mul(this.diffuse));
				// returnColor =
				// returnColor.mul(max).add(light.color.mul(this.specular).mul(maxSP));
			}
		}
		return returnColor.add(lightColor);
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhongMaterial [diffuse=" + diffuse + ", specular=" + specular + ", exponent=" + exponent + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diffuse == null) ? 0 : diffuse.hashCode());
		result = prime * result + exponent;
		result = prime * result + ((specular == null) ? 0 : specular.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PhongMaterial other = (PhongMaterial) obj;
		if (diffuse == null) {
			if (other.diffuse != null)
				return false;
		} else if (!diffuse.equals(other.diffuse))
			return false;
		if (exponent != other.exponent)
			return false;
		if (specular == null) {
			if (other.specular != null)
				return false;
		} else if (!specular.equals(other.specular))
			return false;
		return true;
	}

}
