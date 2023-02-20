import java.util.*;

public class Main {
    static String[] character = {"Raja", "Mantri", "Sipai", "Chor"};
    static int[] value = {1000, 500, 300, 0};
    static String[] players = new String[4];
    static int match = 0;
    static int[][] score;
    static int[] totalScore = new int[4];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        var flag = 1;

        System.out.println("Welcome to ChorChitthi");
        System.out.println("Enter name of players:-");
        for (int i = 0; i < 4; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            players[i] = sc.next();
        }

        System.out.println("\n---***---\n");

        System.out.println("Enter number of rounds you wont play.");
        match = sc.nextInt();
        score = new int[match][4];

        int remainMatch = match;

        while (remainMatch > 0){
            System.out.println("\n-----------\n");
            System.out.println("You have " + remainMatch + " chance(s).");
            System.out.println("Draw(Enter any key): ");

//            flag = sc.nextInt();
            flag = sc.next().charAt(0);
            if (flag > 0)
                play(match - remainMatch);
            else
                continue;
            remainMatch--;
        }

        resultBoard();
    }

    private static void resultBoard() {
        System.out.println("\n---***---\n");
        System.out.println("SCORE BOARD\n");

        System.out.println("__________________________________________________________");
        System.out.printf("|%12s |%9s |%9s |%9s |%9s |\n", "|Game", players[0], players[1], players[2], players[3]);
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

        String[] chit;
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        int n;
        int chorIndex = 0;
        int mantriIndex = 0;
        int rajaIndex = 0;
        int chorPata;
//        int sipaiIndex = 0;
        String[] characterSequence = new String[4];

        for (int i = 0; i < 4; i++) {
            n = random.nextInt(4);
            while (set.contains(n)) {
                n = random.nextInt(4);
            }
            set.add(n);
            chit = getChith(n);
            score[game][i] += Integer.parseInt(chit[1]);

            for (int j = 0; j < 4; j++) {
                if(score[game][j] == 0)
                    chorIndex = j;
                else if (score[game][j] == 500)
                    mantriIndex = j;
                else if (score[game][j] == 1000)
                    rajaIndex = j;
//                if (score[game][j] == 300)
//                    sipaiIndex = j;
            }

            if (score[game][i] == 0 || score[game][i] == 300)
                System.out.println((i + 1) + ": " + players[i] + ": " + "***" + " = " + "***");
            else
                System.out.println((i + 1) + ": " + players[i] + ": " + chit[0] + " = " + score[game][i]);
            characterSequence[i] = chit[0];
        }

        System.out.println("\n " + players[rajaIndex] + ": Chor/Sipai ka patalagao");
        chorPata = sc.nextInt() - 1;
        System.out.println(players[mantriIndex] + ": " + players[chorPata] + " he.");

        if ((chorIndex == chorPata)){
            System.out.println("\nSahi jawab!");

            for (int i = 0; i < 4; i++)
                System.out.println((i + 1) + ": " + players[i] + ": " + characterSequence[i] + " = " + score[game][i]);
        }
//        else if (chorPata == sipaiIndex){
        else {
            System.out.println("\nGalat jawab!");
            swap (mantriIndex, chorIndex, game);

            for (int i = 0; i < 4; i++)
                System.out.println((i + 1) + ": " + players[i] + ": " + characterSequence[i] + " = " + score[game][i]);
        }
       /* else
            System.out.println("\nYe ky he...");*/
//        System.out.println("mantriIndex " + mantriIndex + "\nchorIndex " + chorIndex);
    }

    private static void swap(int mantriIndex, int chorIndex, int game) {
        int temp = score[game][mantriIndex];
        score[game][mantriIndex] = score[game][chorIndex];
        score[game][chorIndex] = temp;
    }

    private static String[] getChith(int index) {
        return new String[]{character[index], String.valueOf(value[index])};
    }
}