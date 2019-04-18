import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadCSVFile
{
    public static List<List<String>> readCSV(String filename) throws FileNotFoundException
    {
        Scanner scanny = new Scanner(new File(filename));
        scanny.useDelimiter(",");

        List<List<String>> cols = new ArrayList<List<String>>();

        while(scanny.hasNext())
        {
            String tempy = scanny.nextLine();
            List<String> row = Arrays.asList(tempy.split(","));
            cols.add(row);
        }
        scanny.close();

        return cols;
    }
}
