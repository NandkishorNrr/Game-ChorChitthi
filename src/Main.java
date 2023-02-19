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

        String[] chit = {"", ""};
        Random random = new Random();
        Set set = new HashSet();
        int n;
        int chorIndex = 0;
        int mantriIndex = 0;
        int rajaIndex = 0;
        int chorPata = 0;
        int sipaiIndex = 0;
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
                if (score[game][j] == 300)
                    sipaiIndex = j;
            }

            if (score[game][i] == 0 || score[game][i] == 300)
                System.out.println((i + 1) + ": " + players[i] + ": " + "***" + " = " + "***");
            else
                System.out.println((i + 1) + ": " + players[i] + ": " + chit[0] + " = " + score[game][i]);
            characterSequence[i] = chit[0];
        }

        System.out.println("\nRaja(" + players[rajaIndex] +")" + " dono mese chor kon?");
        chorPata = sc.nextInt() - 1;
        System.out.println("Mantri(" + players[mantriIndex] +") " + players[chorPata] + " he janab.");

        if ((chorIndex == chorPata)){
            System.out.println("\nSahi javab!");

            for (int i = 0; i < 4; i++)
                System.out.println((i + 1) + ": " + players[i] + ": " + characterSequence[i] + " = " + score[game][i]);
        }
        else if (chorPata  == chorIndex || chorPata == sipaiIndex){
            System.out.println("\nGalat javab!");
            swap (mantriIndex, chorIndex, game);

            for (int i = 0; i < 4; i++)
                System.out.println((i + 1) + ": " + players[i] + ": " + characterSequence[i] + " = " + score[game][i]);
        }
        else
            System.out.println("\nYe ky he...");
//        System.out.println("mantriIndex " + mantriIndex + "\nchorIndex " + chorIndex);
    }

    private static void swap(int mantriIndex, int chorIndex, int game) {
        int temp = score[game][mantriIndex];
        score[game][mantriIndex] = score[game][chorIndex];
        score[game][chorIndex] = temp;
    }

    private static String[] getChith(int index) {
        String[] chith = {character[index], String.valueOf(value[index])};
        return chith;
    }
}