package Model;

public class Cards {

    private int Cards_ID;
    private int Cards_Status;

    public Cards(int Cards_ID, int Cards_Status) {
        this.Cards_ID = Cards_ID;
        this.Cards_Status = Cards_Status;
    }

    public int getCards_ID() {
        return Cards_ID;
    }

    public void setCards_ID(int Cards_ID) {
        this.Cards_ID = Cards_ID;
    }

    public int getCards_Status() {
        return Cards_Status;
    }

    public void setCards_Status(int Cards_Status) {
        this.Cards_Status = Cards_Status;
    }

    @Override
    public String toString() {
        return "Cards{" + "Cards_ID=" + Cards_ID + ", Cards_Status=" + Cards_Status + '}';
    }

}
