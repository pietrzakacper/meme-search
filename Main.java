import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static String[] findIntersection(String[] arr1, String[] arr2) {
        HashSet<String> set1 = new HashSet<String>(Arrays.asList(arr1));
        HashSet<String> s2 = new HashSet<String>(Arrays.asList(arr2));
        set1.retainAll(s2);

        return set1.toArray(new String[set1.size()]);
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        HashMap<File, String[]> filesToTags = new HashMap<File, String[]>();

        File memesDir = new File("memes");
        for(File memeFile: memesDir.listFiles()) {
            BufferedReader memeReader = new BufferedReader(new FileReader(memeFile));
            String tagsLine = memeReader.readLine();

            filesToTags.put(memeFile, tagsLine.split(" "));
        }

        System.out.print("Please type tags (space separated) to search: ");

        Scanner scan = new Scanner(System.in);
        String inputTags = scan.nextLine();
        String[] inputTagsArr = inputTags.split(" ");

        HashMap<File, Integer> filesToMatchedTagsNumber = new HashMap<File, Integer>();
        for(File memeFile: filesToTags.keySet()) {
            String[] memeFileTags = filesToTags.get(memeFile);
            String[] tagsIntersection = findIntersection(inputTagsArr, memeFileTags);
            if(tagsIntersection.length > 0) {
                filesToMatchedTagsNumber.put(memeFile, tagsIntersection.length);
            }
        }

        for(File matchedFile: filesToMatchedTagsNumber.keySet()) {
            printMemeInFile(matchedFile);
        }
    }

    private static void printMemeInFile(File memeFile) throws FileNotFoundException {
        BufferedReader memeReader = new BufferedReader(new FileReader(memeFile));
        memeReader.lines().forEachOrdered((String memeLine) -> System.out.println(memeLine));
    }
}