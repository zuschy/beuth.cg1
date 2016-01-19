package raytracergui.container;

import raytracergui.enums.Geometry;
import raytracergui.enums.Material;
import raytracergui.enums.Texture;

import java.util.HashMap;

/**
 * Created by jroehl on 14.01.16.
 */
public class GeometryContainer {

    private Material material;
    private Geometry geometry;
    private Texture texture_1;
    private Texture texture_2;
    private Texture texture_3;
    private Object texture_1extra;
    private Object texture_2extra;
    private Object texture_3extra;
    private int exponent = 0;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public void addTextures(HashMap<String, Object[]> textures) {

        Object[] texture_1s = textures.get("texture_0");
        Object[] texture_2s = textures.get("texture_1");
        Object[] texture_3s = textures.get("texture_2");

        if (texture_1s != null) {
            this.texture_1 = (Texture) texture_1s[0];
            this.texture_1extra = texture_1s[1];
        }
        if (texture_2s != null) {
            this.texture_2 = (Texture) texture_2s[0];
            this.texture_2extra = texture_2s[1];
        }
        if (texture_3s != null) {
            this.texture_3 = (Texture) texture_3s[0];
            this.texture_3extra = texture_3s[1];
        }
    }

    public void addMaterial(Material material, int exponent) {
        this.material = material;
        this.exponent = exponent;
    }

    public geometries.Geometry getGeometry() {
//        printValues();
        switch (material) {
            case SINGLECOLOR:
            case LAMBERT:
                return (geometry.getGeometry(material.getMaterial(this.exponent, this.texture_1.getTexture(this.texture_1extra))));
            case PHONG:
                return (geometry.getGeometry(material.getMaterial(this.exponent, this.texture_1.getTexture(this.texture_1extra), this.texture_2.getTexture(this.texture_2extra))));
            case REFLECTIVE:
                return (geometry.getGeometry(material.getMaterial(this.exponent, this.texture_1.getTexture(this.texture_1extra), this.texture_2.getTexture(this.texture_2extra), this.texture_3.getTexture(this.texture_3extra))));
            default:
                return null;
        }
    }

    public void printValues() {
        System.out.println("###########GEOMETRY###########");
        System.out.println(this);
        System.out.println(this.name);
        System.out.println("###########GEOMETRY###########");

    }

    private color.Color generateColor(javafx.scene.paint.Color value) {
        return new color.Color(value.getRed(), value.getGreen(), value.getBlue());
    }

}
