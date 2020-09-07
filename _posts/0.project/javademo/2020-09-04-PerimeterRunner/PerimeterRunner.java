import edu.duke.*;
import java.io.File;

public class PerimeterRunner {

    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        int NumPoints = 0;
        for(Point pts : s.getPoints()){
            NumPoints++;
        }
        return NumPoints;
    }

    public double getAverageLength(Shape s){
        double length = getPerimeter(s);
        int NumPoints = getNumPoints(s);
        double avg = length / NumPoints;
        return avg;
    }

    public double getLargestSide(Shape s) {
        double LargestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            LargestSide = currDist > LargestSide?currDist:LargestSide;
            prevPt = currPt;
        }
        return LargestSide;
    }

    public double getLargestX(Shape s){
        double maxX = 0.0;
        for (Point pt : s.getPoints()) {
            double newx = pt.getX();
            maxX = newx > maxX?newx:maxX;
        }
        return maxX;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int NumPoints = getNumPoints(s);
        double avg = getAverageLength(s);
        double LargestSide = getLargestSide(s);
        double maxX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points = " + NumPoints);
        System.out.println("Average Length = " + avg);
        System.out.println("Largest Side = " + LargestSide);
        System.out.println("Largest X = " + maxX);
    }


    // Assignment 2: Processing multiple Shape files
    // find the largest perimeter over several shapes by examining several files representing shapes, calculating the size of the largest perimeter and also the name of the file with the largest perimeter. You will add new methods to the PerimeterAssignmentRunner class.

    // The PerimeterAssignmentRunner class already includes the following method you should understand the following. The printFileNames method has no parameters and no return value, hence return type void. This method first creates a DirectoryResource. When this happens you are prompted to select a file or files. You can select a bunch of files together by clicking on the name of one file, and then hold down the shift key and

    public void getLargestPerimeterMultipleFiles(){
        double LargestPerimeter = 0.0;
        File largestFile = null;
        DirectoryResource dr = new DirectoryResource();      
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(LargestPerimeter < getPerimeter(s)){
                LargestPerimeter = getPerimeter(s);
                largestFile = f;
                System.out.println("The perimeter in " + largestFile + "is :" + LargestPerimeter);
            }
        }
        System.out.println("The largest perimeter in the directory = " + LargestPerimeter);
        System.out.println("The largest perimeter File in the directory = " + largestFile);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        // pr.testPerimeter();
        pr.getLargestPerimeterMultipleFiles();

    }
}
