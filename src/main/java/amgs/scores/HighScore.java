package amgs.scores;

public class HighScore {

    public String name;
    public int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return name+" "+score;
    }

}
