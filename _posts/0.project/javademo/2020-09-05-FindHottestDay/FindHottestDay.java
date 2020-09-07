

import edu.duke.*;
import org.apache.commons.csv.*;

public class HottestDay {

    public CSVParser inputfile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }


    //  reduce redunce
    public CSVRecord getLargestofTwo(CSVRecord currRow, CSVRecord LargestRow){
        LargestRow = LargestRow == null?currRow:LargestRow;
        double hottesttemp = Double.parseDouble(LargestRow.get("TemperatureF"));
        double currenttemp = Double.parseDouble(currRow.get("TemperatureF"));
        if(hottesttemp < currenttemp){
            hottesttemp = currenttemp;
            LargestRow = currRow;
        }
        return LargestRow;
    }


    // find hottest temperature in a day
    public CSVRecord hottestInADay(CSVParser parser){
        CSVRecord LargestRow = null;
        for(CSVRecord currRow : parser){
            // String tempStr = currRow.get("TemperatureF");
            // double temp = Double.parseDouble(tempStr);
            // if(largestSoFar < temp){
            //     largestSoFar = temp;
            //     LargestRow = currRow;
            // }
            LargestRow = getLargestofTwo(currRow, LargestRow);
        }
        System.out.println("Hottest day:");
        System.out.println(LargestRow.get("DateUTC") + ": " + LargestRow.get("TemperatureF"));
        return LargestRow;
    }


    // find hottest temperature in many days
    // public CSVRecord hottestInManyDays(){
    //     CSVRecord LargestRow = null;

    //     DirectoryResource dr = new DirectoryResource();
    //     for(File f : dr.selectedFiles()) {
    //         FileResource fr = new FileResource(f);

    //         CSVParser parser = fr.getCSVParser();
    //         CSVRecord currRow = hottestInADay(parser);

    //         LargestRow = getLargestofTwo(currRow, LargestRow);
    //     }
    //     System.out.println("hottest temperature in many days:");
    //     System.out.println(LargestRow.get("DateUTC") + ": " + LargestRow.get("TemperatureF"));
    //     return LargestRow;
        
    // }

    // public void testhottestInManyDays() {
    //     CSVRecord LargestRow = hottestInManyDays();
    // }



    // fin cold day
    public CSVRecord getSmallestofTwo(CSVRecord currRow, CSVRecord smallestRow){
        if(smallestRow == null){
            smallestRow = currRow;
        }
        double smallestTemp = Double.parseDouble(smallestRow.get("TemperatureF"));
        double currTemp = Double.parseDouble(currRow.get("TemperatureF"));

        if(smallestTemp > currTemp){
            smallestTemp = currTemp;
            smallestRow = currRow;
        }
        return smallestRow;
    }


    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestRow = null;
        for(CSVRecord currSRow : parser){
            smallestRow = getSmallestofTwo(currSRow, smallestRow);
            // if(smallestRow == null){
            //     smallestRow = currSRow;
            // }
    
            // double smallestTemp = Double.parseDouble(smallestRow.get("TemperatureF"));
            // double currTemp = Double.parseDouble(currSRow.get("TemperatureF"));
    
            // if(smallestTemp < currTemp){
            //     smallestTemp = currTemp;
            //     smallestRow = currSRow;
            // }
        }
        System.out.println("Clodest day:");
        System.out.println(smallestRow.get("DateUTC") + ": " + smallestRow.get("TemperatureF"));
        return smallestRow;
    }


    // find lowest humidity
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHRow = null;
        for(CSVRecord currRow : parser){
            if(lowestHRow == null){
                lowestHRow = currRow;
            }

            double lowestH = Double.parseDouble(lowestHRow.get("Humidity"));

            String currHStr = currRow.get("Humidity");
            if(!currHStr.equals("N/A")){
                double currH = Double.parseDouble(currHStr);
                if(lowestH > currH){
                    lowestH = currH;
                    lowestHRow = currRow;
                }
            }
        }
        System.out.println("lowest Humidity day:");
        System.out.println(lowestHRow.get("DateUTC") + ": " + lowestHRow.get("Humidity"));
        return lowestHRow;
    }

    // find average Temperature 
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;
        for(CSVRecord currSRow : parser){
            double temp = Double.parseDouble(currSRow.get("TemperatureF"));
            sum += temp;
            count++;
        }
        double avg = sum/count;
        System.out.println("Average temperature in file is: " + avg);
        return avg;
    }


    // find average Temperature With HighHumidity
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        for(CSVRecord currRow : parser){
            double hum = Double.parseDouble(currRow.get("Humidity"));
            if(hum >= value){
                double temp = Double.parseDouble(currRow.get("TemperatureF"));
                sum += temp;
                count++;
            }
        }
        double avg = sum/count;
        System.out.println("Average temperature With HighHumidity in file is: " + avg);
        return avg;
    }




    public static void main(String[] args) {

        HottestDay pr = new HottestDay();
        CSVParser parser = pr.inputfile();

        // System.out.println("---------------test hottestInADay()---------------");
        // CSVRecord LargestRow = pr.hottestInADay(parser);
        
        // System.out.println("---------------test coldestHourInFile()---------------");
        // CSVRecord smallestRow = pr.coldestHourInFile(parser);
        
        // System.out.println("---------------test lowestHumidityInFile()---------------");
        // CSVRecord lowestHRow = pr.lowestHumidityInFile(parser);

        // System.out.println("---------------test averageTemperatureInFile()---------------");
        // double avg = pr.averageTemperatureInFile(parser);
    
        System.out.println("---------------test averageTemperatureWithHighHumidityInFile()---------------");
        double avg = pr.averageTemperatureWithHighHumidityInFile(parser, 80);
    }
}
