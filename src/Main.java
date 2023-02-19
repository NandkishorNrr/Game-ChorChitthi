import java.util.*;

public class Main {
    static String[] character = {"Raja", "Mantri", "Sipai", "Chor"};
    static int[] value = {1000, 500, 300, 0};
    static String[] players = new String[4];
    static int match = 0;
    static int[][] score;
    static int[] totalScore = new int[4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int flag = 1;

        System.out.println("Welcome to ChorChiththi");
        System.out.println("Enter players Name:-");
        for (int i = 0; i < 4; i++) {
            System.out.println("Player " + (i + 1) + ": ");
            players[i] = sc.next();
        }

        System.out.println("\n---***---\n");

        System.out.println("Enter number of match you wont play.");
        match = sc.nextInt();
        score = new int[match][4];

        int remainMatch = match;

        while (remainMatch > 0){
            System.out.println("\n-----------\n");
            System.out.println("You have " + remainMatch + " chance(s).");
            System.out.println("Through(Enter 1): ");

            flag = sc.nextInt();
            if (flag == 1)
                play(match - remainMatch);
            else
                continue;
            remainMatch--;
        }

        resultBoard();
    }

    private static void resultBoard() {
        System.out.println("\n---***---\n");
        System.out.println("RESULT BOARD\n");

        System.out.println("__________________________________________________________");
        System.out.printf("|%11s  | %7s  | %7s | %7s  | %7s |\n","|Game ", players[0],  players[1], players[2],  players[3]);
        System.out.println("|_________________________________________________________|");
        for (int i = 0; i < match; i++) {
            System.out.printf("|%s |%5d |%9d |%9d |%9d |%9d |\n","Score", (i + 1), score[i][0], score[i][1], score[i][2], score[i][3]);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < match; j++) {
                totalScore[i] += score[j][i];
            }
        }
        System.out.println("|_________________________________________________________|");
        System.out.printf("|%s |%5s |%9d |%9d |%9d |%9d |\n", "Total", "=", totalScore[0], totalScore[1] ,totalScore[2] ,totalScore[3]);
        System.out.println("|_________________________________________________________|");
    }

    private static void play(int game) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String[] chit;
        Random random = new Random();
        Set set = new HashSet();
        int n;

        for (int i = 0; i < 4; i++) {
            n = random.nextInt(4);
            while (set.contains(n)) {
                n = random.nextInt(4);
            }
            set.add(n);
            chit = getChith(n);
            score[game][i] += Integer.parseInt(chit[1]);

            System.out.println((i + 1) + ": " + players[i] + ": " + chit[0] + " = " + score[game][i]);
        }
    }

    private static String[] getChith(int index) {
        String[] chith = {character[index], String.valueOf(value[index])};
        return chith;
    }
}