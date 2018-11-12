public class Strategy {
    private String name = "";
    private int uses = 0;
    private int time = 0; // in milliseconds

    public Strategy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getUses() {
        return uses;
    }

    public void increaseUses(int count) {
        uses += count;
    }

    public String getTime() {
        return new Time(time).getTime();
    }

    public void addTime(int milliseconds) {
        time += milliseconds;
    }
}
