package search.reader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

//--data "/home/nik/IdeaProjects/Simple Search Engine/Simple Search Engine/task/src/search/resources/input.txt"
//--data "./Simple Search Engine/task/src/search/resources/input.txt"
public class UserReader {
    public ArrayList<String> read(String filePatch) {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(filePatch))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
