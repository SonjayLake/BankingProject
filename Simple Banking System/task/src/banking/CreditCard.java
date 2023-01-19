package banking;
import java.util.concurrent.ThreadLocalRandom;


public class CreditCard {
    private String number;
    private String pin;

    public CreditCard(){
        this.number = "400000" + createNumber();
        this.pin = createPin();

    }

    private String createNumber(){
        //drop the last digit
        //multiply odd digits by 2
        // subtract 9 to numbers over 9
        //add all numbers
        // result must be evenly divisble by 10 -> checksum value

        long number = ThreadLocalRandom.current().nextLong(100_000_000L,999_999_999L); //gen 9 digits
        String digits = String.valueOf(number);

        int[] numericalDigits = new int[9];
        int digitOrder = 7;
        for(int i=0;i<9;i++){
            int slice = Integer.valueOf(digits.substring(i,i+1));
            if(digitOrder%2 ==1){
                numericalDigits[i] = slice*2;

            } else{
                numericalDigits[i] = slice;
            }
            if(numericalDigits[i] > 9){
                numericalDigits[i] = numericalDigits[i] - 9;
            }
            digitOrder++;

        }
        int sum = 8;

        for(int num: numericalDigits){

            sum += num;
        }

        int finalNum = 0;
        while((sum+finalNum) %10 != 0) {
            finalNum++;
        }

        String finalString = "";
        for(int i=0;i<9;i++){

            finalString += digits.charAt(i);
        }

        return finalString + finalNum;


    }

    private String createPin(){

        int number = ThreadLocalRandom.current().nextInt(1000,9999);
        return String.format("%04d",number);
    }

    public String getNumber(){
        return number;
    }

    public String getPin(){
        return pin;
    }

    public static boolean passesLuhn(String number){
        if(String.valueOf(number).length() != 16){

            return false;
        }
        String starting = String.valueOf(number).substring(0,6);
        String digits = String.valueOf(number).substring(6,16);


        int finalNum = Integer.valueOf(digits.substring(9,10));


        try{
            int[] numericalDigits = new int[9];
            int digitOrder = 7;
            for(int i=0;i<9;i++) {
                int slice = Integer.valueOf(digits.substring(i, i + 1));
                if (digitOrder % 2 == 1) {
                    numericalDigits[i] = slice * 2;

                } else {
                    numericalDigits[i] = slice;
                }
                if (numericalDigits[i] > 9) {
                    numericalDigits[i] = numericalDigits[i] - 9;
                }
                digitOrder++;

            }

            int sum = 0;
            for(int index=0;index<starting.length();index++){
                sum+= Integer.valueOf(starting.charAt(index));
            }
            for(int num: numericalDigits){
                sum += num;
            }
            sum+= finalNum;

            if(sum % 10 == 0) {

                return true;
            }

        } catch (Exception e){
            return false;
        }

        return false;
    }
}