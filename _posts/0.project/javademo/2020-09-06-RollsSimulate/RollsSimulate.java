import java.util.Random;

public class SimpleSimulate {
    
    public void simpleSimulate(int rolls){
        Random rand = new Random();
        int twos = 0, twelves = 0;
        for(int i = 0; i < rolls; i++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            if(d1 + d2 == 2){
                twos++;
            }
            else if(d1 + d2 == 12){
                twelves++;
            }
        }
        System.out.println("2: " + twos + "\t" + 100*twos/rolls);
        System.out.println("12: " + twelves + "\t" + 100*twelves/rolls);
    }


    public void rollsSimulate(int rolls){
        Random rand = new Random();
        int[] count = new int[13];
        for(int i = 0; i < rolls; i++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            int sum = d1 + d2 ;
            count[sum]++;
        }
        for(int i = 0; i < count.length; i++){
            System.out.println(i + ": " + count[i] + "\t" + 100.0*count[i]/rolls);
        }
    }





    public static void main(String[] args) {
        SimpleSimulate pr = new SimpleSimulate();

        // pr.simpleSimulate(1000);
        
        pr.rollsSimulate(1000000);
    }
}
