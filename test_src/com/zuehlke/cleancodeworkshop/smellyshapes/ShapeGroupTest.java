package com.zuehlke.cleancodeworkshop.smellyshapes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShapeGroupTest {

    @Test
    public void tooXml() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.add(new Rectangle(0, 0, 2, 1));

        String xml = shapeGroup.toXml();

        assertEquals(
                "<shapegroup>\n<rectangle x=\"0\" y=\"0\" width=\"2\" height=\"1\" />\n</shapegroup>\n",
                xml);
    }

    @Test
    public void constructor_withShapeArray() {
        ShapeGroup shapeGroup = new ShapeGroup(new Shape[]{new Circle(new Point(0, 0), 0)}, true);

        assertEquals(1, shapeGroup.getSize());
    }

    @Test
    public void add_withReadOnly() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.setReadOnly(true);

        shapeGroup.add(new Circle(new Point(0, 0), 0));

        assertEquals(0, shapeGroup.getSize());
    }

    @Test
    public void add_withoutReadOnly() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.setReadOnly(false);

        shapeGroup.add(new Circle(new Point(0, 0), 0));

        assertEquals(1, shapeGroup.getSize());
    }

    @Test
    public void add_sameElementTwice() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.setReadOnly(false);

        Circle circle = new Circle(new Point(0, 0), 0);
        shapeGroup.add(circle);
        shapeGroup.add(circle);

        assertEquals(1, shapeGroup.getSize());
    }

    @Test
    public void add_internalArraySizeExceeded() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.setReadOnly(false);

        for (int i = 0; i < 11; i++) {
            shapeGroup.add(new Circle(new Point(0, 0), 0));
        }

        assertEquals(11, shapeGroup.getSize());
    }

    @Test
    public void contains_pointNotInGroup() {
        ShapeGroup shapeGroup = new ShapeGroup();

        assertFalse(shapeGroup.contains(new Point(0, 0)));
    }

    @Test
    public void contains_pointInGroup() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.add(new Circle(new Point(0, 0), 0));

        assertTrue(shapeGroup.contains(new Point(0, 0)));
    }

    @Test
    public void contains_pointOutsideGroup() {
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.add(new Circle(new Point(0, 0), 0));

        assertFalse(shapeGroup.contains(new Point(1, 1)));
    }

    @Test
    public void contains_null() {
        ShapeGroup shapeGroup = new ShapeGroup();

        assertFalse(shapeGroup.contains((Shape)null));
    }

    @Test
    public void contains_shapeInGroup() {
        ShapeGroup shapeGroup = new ShapeGroup();
        Circle c = new Circle(new Point(0, 0), 0);
        shapeGroup.add(c);

        assertTrue(shapeGroup.contains(c));
    }
}
