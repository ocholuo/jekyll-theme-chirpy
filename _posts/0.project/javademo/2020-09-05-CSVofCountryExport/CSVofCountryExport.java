
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVofCountryExport {
    

    // find exportitem in sepecific row in a csv paser
    public StorageResource listExporters(CSVParser parser, String exportItem){
        StorageResource sr = new StorageResource();
        for(CSVRecord record : parser){
            String item = record.get("Exports");
            if(item.contains(exportItem)){
            // if(item.indexOf(exportItem) != -1){
                String countryname = record.get("Country");
                // System.out.println(countryname);
                sr.add(countryname);
            }
        }
        return sr;
    }


    // find exportitem "coffee" in sepecific row "Exports" in a csv paser
    public void testwhoexportcoffee() {
        FileResource fr = new FileResource();
        CSVParser cr = fr.getCSVParser();
        StorageResource sr = listExporters(cr, "coffee");
        for(String exportsCountry : sr.data()){
            System.out.println(exportsCountry);
        }
    }


    // find country in sepecific row in a csv paser
    public void countryinfo(CSVParser parser, String keycountry){
        for(CSVRecord record : parser){
            String countryname = record.get("Country");
            // System.out.println(countryname);
            if(countryname.equals(keycountry)){
                System.out.print(keycountry + ": ");
                System.out.print(record.get("Exports") + ": ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }

    // find country that export item1&item2 in sepecific row in a csv paser
    public void listExportersTwoProducts(CSVParser parser, String item1, String item2){
        for(CSVRecord record : parser){
            String items = record.get("Exports");
            if(items.contains(item1) && items.contains(item2)){
                System.out.println(record.get("Country"));
            }
        }
    }

    // find country number that export item in sepecific row in a csv paser
    public int numberOfExporters(CSVParser parser, String exportItem) {
        StorageResource sr = listExporters(parser, exportItem);
        int count = 0;
        for(String country : sr.data()){
            count++;
        }
        return count;
    }


    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                System.out.print(record.get("Country") + " ");
                System.out.println(value);
            }
        }
    }



    public static void main(String[] args) {
        CSVofCountryExport pr = new CSVofCountryExport();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        // System.out.println("---------------test countryinfo()---------------");
        // pr.countryinfo(parser, "Nauru");

        // System.out.println("---------------test listExportersTwoProducts()---------------");
        // pr.listExportersTwoProducts(parser, "gold", "diamonds");

        // System.out.println("---------------test numberOfExporters()---------------");
        // int countryNum = pr.numberOfExporters(parser, "sugar");
        // System.out.println(countryNum);

        System.out.println("---------------test bigExporters()---------------");
        pr.bigExporters(parser, "$999,999,999,999");
    }
}
