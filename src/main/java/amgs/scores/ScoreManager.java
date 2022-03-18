package amgs.scores;

import amgs.utils.Utils;

import java.util.*;

public class ScoreManager {

    private ArrayList<HighScore> allHighScores;
    private Comparator<HighScore> sorter = new Comparator<HighScore>(){
        // compare highScores based on their score value
        @Override
        public int compare(HighScore a, HighScore b) {
            if((a.score) >= (b.score)) {
                return -1;
            }
            return 1;
        }
    };

    public ScoreManager() {
        allHighScores = new ArrayList<HighScore>();
        loadHighScores();
        sortHighScore();
        System.out.println(toString());
    }

    public void loadHighScores() {
        String content = Utils.loadFileAsString("/res/saves/score.txt");
        String[] lines = content.split("[\\r\\n]+");
        for(int i = 0; i < lines.length; i++) {
            // cut lines with blank space
            String[] lineElements = lines[i].split("\\s+"); // name score
            allHighScores.add(new HighScore(lineElements[0], Utils.parseInt(lineElements[1])));
        }
    }

    public void saveHighScores() {
        // keep 10 first elements
        sortHighScore();
        keep20FirstElements();

        String content = toString();
        // write the file
        Utils.saveStringAsFile(content);
    }

    public String toString() {
        // build a string representation of the score file
        String content = "";
        Iterator<HighScore> iterator = allHighScores.iterator();
        while(iterator.hasNext()) {
            HighScore highScore = iterator.next(); // retreive element
            if(content != "") {
                content += System.lineSeparator() + highScore.toString();
            } else {
                // init, do no put first System.lineSeparator()
                content += highScore.toString();
            }
        }
        return content;
    }

    public void sortHighScore() {
        allHighScores.sort(sorter);
    }

    public int getAllHighScoresLength() {
        int length = 0;
        boolean notEmpty = true;
        Iterator<HighScore> iterator = allHighScores.iterator();
        while(iterator.hasNext() && notEmpty) {
            System.out.print("W");
            HighScore highScore = iterator.next(); // retreive element
            if(highScore.score != 0 || highScore.name != null) {
                length++;
            } else {
                notEmpty = false;
            }
        }
        return length;
    }

    private void keep20FirstElements() {
        if(getAllHighScoresLength() > 20) {
            allHighScores = (ArrayList<HighScore>) allHighScores.subList(0, 20);
        }
    }

    // GETTERS SETTERS
    public void addHighScore(HighScore highScore) {
        allHighScores.add(highScore);
        sortHighScore();
    }

    public ArrayList<HighScore> getAllHighScores() {
        return allHighScores;
    }

    public String[] get10HighestScoresToStrings() {
        String[] highScoreStrings = new String[10];
        Iterator<HighScore> iterator = allHighScores.iterator();
        int i = 0;
        while(iterator.hasNext() && (i < highScoreStrings.length)) {
            HighScore highScore = iterator.next(); // retreive element
            highScoreStrings[i] = highScore.toString();
            i++;
        }
        return highScoreStrings;
    }

}
