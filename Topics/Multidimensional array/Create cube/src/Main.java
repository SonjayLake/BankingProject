class ArrayOperations {
    public static int[][][] createCube() {
        // write your code here
        int[][][] cube = new int[3][3][3];
        //System.out.println(cube[0][0][0]);

        for (int width = 0; width < 3; width++) {
            int widthVal = 0;
            int heightVal = 1;
            int lengthVal = 2;
            for (int height = 0; height < 3; height++) {
                int[] row = new int[3];
                row[0] = widthVal;
                row[1] = heightVal;
                row[2] = lengthVal;
                cube[width][height] = row;
                widthVal += 3;
                heightVal += 3;
                lengthVal += 3;
            }
        }

        return cube;
    }
}

//    public static void main(String[] args){
//        int[][][] testing = createCube();
//        for(int i=0; i<3;i++){
//            String row = "";
//            for(int j=0; j<3;j++){
//                for(int k=0;k<3;k++){
//                    System.out.println(testing[i][j][k]);
//
//                }
//            }
//            System.out.println(row);
//        }
//    }
//}
