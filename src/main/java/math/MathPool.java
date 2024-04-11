package math;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class MathPool {
    private ArrayList<MathQuestion> poolOfQuestions;
    private final Random random;


    public MathPool() {
        poolOfQuestions = new ArrayList<MathQuestion>();
        random = new Random();
    }

    public void addMathQuestion(String wordProblem, int solution, int difficulty) {
        MathQuestion problem = new MathQuestion(wordProblem, solution, difficulty);
        poolOfQuestions.add(problem);
    }

    /*public MathQuestion getQuestionByDifficulty(int targetDifficulty) {
        ArrayList<MathQuestion> filteredQuestions = new ArrayList<>();
        for (MathQuestion question : poolOfQuestions) {
            if (question.getDifficulty() == targetDifficulty) {
                filteredQuestions.add(question);
            }
        }
        if (!filteredQuestions.isEmpty()) {
            int index = random.nextInt(filteredQuestions.size());
            return filteredQuestions.get(index);
        } else {
            return null;
        }
    }*/

    public MathQuestion getQuestionByDifficulty(int targetDifficulty) {
        ArrayList<MathQuestion> filteredQuestions = new ArrayList<>();
        for (MathQuestion question : poolOfQuestions) {
            if (question.getDifficulty() == targetDifficulty) {
                filteredQuestions.add(question);
            }
        }
        if (!filteredQuestions.isEmpty()) {
            // Shuffle the list of questions
            Collections.shuffle(filteredQuestions);
            // Iterate through shuffled questions
            for (MathQuestion question : filteredQuestions) {
                // Return the first question found (after shuffling)
                return question;
            }
        }
        return null; // Return null if no questions of the specified difficulty are found
    }


    public void init() {


        // Difficulty 2
        addMathQuestion("6 * 4 = ", 24, 0);
        addMathQuestion("12 / 3 = ", 4, 0);
        addMathQuestion("9 * 5 = ", 45, 0);
        addMathQuestion("20 / 4 = ", 5, 0);
        addMathQuestion("8 * 7 = ", 56, 0);

        // Difficulty 3
        addMathQuestion("5^2 = ", 25, 1);
        addMathQuestion("square root of 144 = ", 12, 1);
        addMathQuestion("3^3 = ", 27, 1);
        addMathQuestion("square root of 81 = ", 9, 1);
        addMathQuestion("7^2 = ", 49, 1);

        // Difficulty 4
        addMathQuestion("What is the sum of all angles in a triangle?", 180, 2);
        addMathQuestion("How many sides does a hexagon have?", 6, 2);
        addMathQuestion("What is the area of a square with side length 5?", 25, 2);
        addMathQuestion("What is the perimeter of a rectangle with sides 4 and 6?", 20, 2);
        // Difficulty 3
        addMathQuestion("What is 2 times the square root of 64?", 16, 3);
        addMathQuestion("How many degrees are in a right angle?", 90, 3);
        addMathQuestion("If a square has an area of 25 square units, what is the length of one side?", 5, 3);
        addMathQuestion("What is the sum of the first 10 positive integers?", 55, 3);
        addMathQuestion("How many edges does a cube have?", 12, 3);

        // Difficulty 4
        addMathQuestion("What is the value of 5 factorial (5!)?", 120, 4);
        addMathQuestion("What is the next prime number after 31?", 37, 4);
        addMathQuestion("How many vertices does a tetrahedron have?", 4, 4);
        addMathQuestion("How many diagonals does a hexagon have?", 9, 4);
        addMathQuestion("How many millimeters are in a meter?", 1000, 4);

        // Difficulty 5
        addMathQuestion("What is the value of 7 choose 3 (7C3)?", 35, 5);
        addMathQuestion("How many faces does a dodecahedron have?", 12, 5);
        addMathQuestion("How many sides does a regular polygon have if each exterior angle measures 30 " +
                "degrees?", 12, 5);
        addMathQuestion("What is the 20th Fibonacci number?", 6765, 5);
        addMathQuestion("What is the value of 2^10?", 1024, 5);


    }

}
