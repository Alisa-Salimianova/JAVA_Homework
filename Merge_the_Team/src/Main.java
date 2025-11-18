import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int[][] teams = {
                { 45, 31, 24, 22, 20, 17, 14, 13, 12, 10 },
                { 31, 18, 15, 12, 10, 8, 6, 4, 2, 1 },
                { 51, 30, 10, 9, 8, 7, 6, 5, 2, 1 }
        };

        int[] nationalTeam = mergeAll(teams);
        System.out.println(Arrays.toString(nationalTeam));
    }

    public static int[] mergeAll(int[][] teams) {
        if (teams.length == 0) {
            return new int[0];
        }

        int[] result = teams[0];

        for (int i = 1; i < teams.length; i++) {
            result = merge(result, teams[i]);
        }

        return result;
    }

    public static int[] merge(int[] teamA, int[] teamB) {
        int[] merged = new int[10];
        int i = 0, j = 0, k = 0;

        while (k < 10 && i < teamA.length && j < teamB.length) {
            if (teamA[i] >= teamB[j]) {
                merged[k] = teamA[i];
                i++;
            } else {
                merged[k] = teamB[j];
                j++;
            }
            k++;
        }

        while (k < 10 && i < teamA.length) {
            merged[k] = teamA[i];
            i++;
            k++;
        }

        while (k < 10 && j < teamB.length) {
            merged[k] = teamB[j];
            j++;
            k++;
        }

        return merged;
    }
}