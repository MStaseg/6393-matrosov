package ru.cft.focusstart.matrosov.model;

public enum GeometricShapeParameter {
    AREA("area"),
    PERIMETER("perimeter"),
    TYPE("shapeType"),
    WIDTH("width"),
    HEIGHT("height"),
    FIRST_SIDE("firstSide"),
    SECOND_SIDE("secondSide"),
    THIRD_SIDE("thirdSide"),
    FIRST_SIDE_FRONT_ANGLE("firstSideFrontAngle"),
    SECOND_SIDE_FRONT_ANGLE("secondSideFrontAngle"),
    THIRD_SIDE_FRONT_ANGLE("thirdSideFrontAngle"),
    DIAGONAL("diagonal"),
    SIZE("size"),
    RADIUS("radius"),
    DIAMETER("diameter");

    private final String name;

    GeometricShapeParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
