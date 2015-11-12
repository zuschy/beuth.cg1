package color;

public class Color {

	/**
	 * Rot-Wert der Color-Klasse
	 */
	public final double r;

	/**
	 * Gruen-Wert der Color-Klasse
	 */
	public final double g;

	/**
	 * Blau-Wert der Color-Klasse
	 */
	public final double b;

	/**
	 * 
	 * @param r
	 *            Rot-Wert
	 * @param g
	 *            Gruen-Wert
	 * @param b
	 *            Blau-Wert
	 */
	public Color(final double r, final double g, final double b) {

		// TODO: Keiner der Werte soll größer 1 sein, daher die Fallunterscheidung.
		// Später soll das jedoch auch Möglich sein
		if (r > 1) {
			this.r = 1;
		} else {
			this.r = r;
		}
		if (g > 1) {
			this.g = 1;
		} else {
			this.g = g;
		}
		if (b > 1) {
			this.b = 1;
		} else {
			this.b = b;
		}
	}

	/**
	 * Addiert eine Farbe zu der bestehenden dazu.
	 * 
	 * @param c
	 * @return
	 */
	public Color add(final Color c) {
		return new Color(this.r + c.r, this.g + c.g, this.b + c.b);
	}

	/**
	 * Subtraiert eine Farbe von der bestehenden.
	 * 
	 * @param c
	 * @return
	 */
	public Color sub(final Color c) {
		return new Color(this.r - c.r, this.g - c.g, this.b - c.b);
	}

	/**
	 * Multipliziert eine Farbe zu der bestehenden.
	 * 
	 * @param c
	 * @return
	 */
	public Color mul(final Color c) {
		// TODO: Wie Multipliziert man denn Farben ? :O
		return new Color(this.r * c.r, this.g * c.g, this.b * c.b);
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public Color mul(final double v) {
		return new Color(this.r * v, this.g * v, this.b * v);
	}

	/**
	 * Ueberschreiben der toString()-Methode
	 */
	@Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

	/**
	 * Ueberschreiben der hashCode()-Methode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Ueberschreiben der equals()-Methode
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}
}