package ru.cft.focusstart.matrosov.model;

/**
 * Enum contains each existing type of the cell that can be seen by user
 */
public enum CellType {

    CLOSED("closed"),
    EMPTY("zero"),
    BOMB("bomb"),
    NO_BOMB("nobomb"),
    BOMBED("bombed"),
    FLAGGED("flagged"),
    ONE_MINE_AROUND("num1"),
    TWO_MINES_AROUND("num2"),
    THREE_MINES_AROUND("num3"),
    FOUR_MINES_AROUND("num4"),
    FIVE_MINES_AROUND("num5"),
    SIX_MINES_AROUND("num6"),
    SEVEN_MINES_AROUND("num7"),
    EIGHT_MINES_AROUND("num8");

    private String imagePath;

    CellType(String image) {
        imagePath = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    /**
     * Method returns next cell with mines near number type. If the cell type is the bomb or bombed
     * it returns the same type. For empty or numeric types it returns next type.
     *
     * @return next type of the cell
     */
    public CellType getNextNumberCell() {
        switch (this) {
            case EMPTY: return CellType.ONE_MINE_AROUND;
            case ONE_MINE_AROUND: return CellType.TWO_MINES_AROUND;
            case TWO_MINES_AROUND: return CellType.THREE_MINES_AROUND;
            case THREE_MINES_AROUND: return CellType.FOUR_MINES_AROUND;
            case FOUR_MINES_AROUND: return CellType.FIVE_MINES_AROUND;
            case FIVE_MINES_AROUND: return CellType.SIX_MINES_AROUND;
            case SIX_MINES_AROUND: return CellType.SEVEN_MINES_AROUND;
            case SEVEN_MINES_AROUND: return CellType.EIGHT_MINES_AROUND;
            default: return this;
        }
    }

    /**
     * Returns integer value that means how many mines should be near the cell of this type
     *
     * @return number of mines. If the cell type does not support this operation it returns -1.
     */
    public int getNumberOfMinesAround() {
        switch (this) {
            case EMPTY: return 0;
            case ONE_MINE_AROUND: return 1;
            case TWO_MINES_AROUND: return 2;
            case THREE_MINES_AROUND: return 3;
            case FOUR_MINES_AROUND: return 4;
            case FIVE_MINES_AROUND: return 5;
            case SIX_MINES_AROUND: return 6;
            case SEVEN_MINES_AROUND: return 7;
            default: return -1;
        }
    }
}
