
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split(" ");
        int row = Integer.valueOf(dimensions[0]);
        int col = Integer.valueOf(dimensions[1]);
        int[][] matrix = new int[col][row];
        for(int rowIndex=0; rowIndex<row;rowIndex++){
            String[] rowString = scanner.nextLine().split(" ");
            for(int colIndex=col-1; colIndex >= 0; colIndex--){
                matrix[colIndex][rowIndex] = Integer.valueOf(rowString[colIndex]);
            }
        }
        for(int rowIndex = 0; rowIndex < col; rowIndex++){
            String rowString = "";
            for(int colIndex = row-1; colIndex >= 0;colIndex--){
                rowString += matrix[rowIndex][colIndex] + " ";
            }
            System.out.println(rowString);
        }


    }
}