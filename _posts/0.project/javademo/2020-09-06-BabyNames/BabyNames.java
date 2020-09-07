

import edu.duke.*;
import jdk.nashorn.internal.ir.ReturnNode;
import jdk.nashorn.internal.runtime.StoredScript;
import org.apache.commons.csv.*;

public class BabyNames {


    public void printNames(CSVParser parser){
        for(CSVRecord rec : parser){
            String name = rec.get(0);
            System.out.println("Name " + rec.get(0) +
                                " Gender "+ rec.get(1) +
                                " Num Born" + rec.get(2));
        }
    }

    public CSVParser readOneFile(int year) {
        String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);    // false: no head row
        return parser;
    }


    public int totalBirths(CSVParser parser){
        int totalBorn = 0;
        int Gname = 0;
        int Bname = 0;
        for(CSVRecord rec : parser){
            int newBorn = Integer.parseInt(rec.get(2));
            totalBorn += newBorn;
            if(rec.get(1).equals("F")){
                Gname++;
            }
            if(rec.get(1).equals("M")){
                Bname++;
            }
        }
        System.out.println("The total Born " + totalBorn);
        System.out.println("The number of girls names " + Gname);
        System.out.println("The number of boys names  " + Bname);
        return totalBorn;
    }


    // This method returns the rank of the name in the file for the given gender, where rank 1 is the name with the largest number of births. If the name is not in the file, then -1 is returned.
    public int getRank(int year, String name, String gender){
        CSVParser parser = readOneFile(year);
        int rank = 0;
        for(CSVRecord rec : parser){
            rank++;
            if(name.equals(rec.get(0)) && gender.equals(rec.get(1)) ){
                System.out.println("the rank of the girl’s name " + name + " in " + year + " is " + rank);
                return rank;
            }
        }
        return -1;
    }


    // This method returns the name of the person in the file at this rank, for the given gender, where rank 1 is the name with the largest number of births. If the rank does not exist in the file, then “NO NAME” is returned.
    public String getName(int year, int rank, String gender){
        CSVParser parser = readOneFile(year);
        int count = 0;
        for(CSVRecord rec : parser){
            if(rec.get(1).equals(gender)){
                count++;
                if(count == rank){
                    String name = rec.get(0);
                    System.out.println("the name of the " + gender + "’s rank " + rank + " in " + year + " is " + name);
                    return name;
                }
            }
        }
        return "NO NAME";
    }


    // This method determines what name would have been named if they were born in a different year, based on the same popularity. That is, you should determine the rank of name in the year they were born, and then print the name born in newYear that is at the same rank and same gender.
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        if(rank == -1){
            return "No such name in file.";
        }
        String newname = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newname + " if she was born in " + newYear);
        return newname;
    }


    // Write the method yearOfHighestRank that has two parameters: a string name, and a string named gender (F for female and M for male). This method selects a range of files to process and returns an integer, the year with the highest rank for the name and gender. If the name and gender are not in any of the selected files, it should return -1. For example, calling yearOfHighestRank with name Mason and gender ‘M’ and selecting the three test files above results in returning the year 2012. That is because Mason was ranked the 2nd most popular name in 2012, ranked 4th in 2013 and ranked 3rd in 2014. His highest ranking was in 2012.
    
    // Write the method getAverageRank that has two parameters: a string name, and a string named gender (F for female and M for male). This method selects a range of files to process and returns a double representing the average rank of the name and gender over the selected files. It should return -1.0 if the name is not ranked in any of the selected files. For example calling getAverageRank with name Mason and gender ‘M’ and selecting the three test files above results in returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014. As another example, calling getAverageRank with name Jacob and gender ‘M’ and selecting the three test files above results in returning 2.66.



    // This method returns an integer, the total number of births of those names with the same gender and same year who are ranked higher than name.
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        CSVParser parser = readOneFile(year);
        int sum = 0;
        for(CSVRecord rec : parser){
            int born = Integer.parseInt(rec.get(2));
            if(rec.get(1).equals(gender)){
                if(rec.get(0).equals(name)){
                    System.out.println("total number of births of those names with the same gender and same year:");
                    System.out.println(name + ": " + sum);
                    return sum;
                }
                // System.out.println(rec.get(0));
                // System.out.println(rec.get(1));
                // System.out.println(rec.get(2));
                sum += born;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        BabyNames pr = new BabyNames();
        FileResource fr = new FileResource("example-small.csv");
        CSVParser parser = fr.getCSVParser(false);

        // System.out.println("---------------test printNames()---------------");
        // pr.printNames(parser);

        // System.out.println("---------------test totalBirths()---------------");
        // fr = new FileResource("yob1900.csv");
        // parser = fr.getCSVParser(false);
        // pr.totalBirths(parser);
        // fr = new FileResource("yob1905.csv");
        // parser = fr.getCSVParser(false);
        // pr.totalBirths(parser);


        // System.out.println("---------------test getRank()---------------");
        // int rank1 = pr.getRank(1960, "Emily", "F");
        // System.out.println("---------------test getRank()---------------");
        // int rank2 = pr.getRank(1971, "Frank", "M");


        // System.out.println("---------------test getRank()---------------");
        // String name1 = pr.getName(1980, 350, "F");
        // System.out.println("---------------test getRank()---------------");
        // String name2 = pr.getName(1982, 450, "M");


        // System.out.println("---------------test totalBirths()---------------");
        // pr.totalBirths(parser);

        // System.out.println("---------------test whatIsNameInYear()---------------");
        // String newname = pr.whatIsNameInYear("Owen", 1974, 2014, "M");

        System.out.println("---------------test whatIsNameInYear()---------------");
        int sum = pr.getTotalBirthsRankedHigher(1990, "Emily", "F");
        sum = pr.getTotalBirthsRankedHigher(1990, "Drew", "M");
    }


}