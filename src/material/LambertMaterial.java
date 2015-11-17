package material;

import geometries.Hit;
import ray.World;
import color.Color;

public class LambertMaterial extends Material {
	private final Color diffuse;
	private final Color specular;
	private final int exponent;
	public LambertMaterial(Color diffuse, Color specular, int exponent) {
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	@Override
	public Color colorFor(Hit hit, World world) {
		return null;

	}

}
