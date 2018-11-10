
//returns the freespaces available for the bot given an index
public static int freeSpacesAtIndex(int x, int y, int[][] state){
    int result = 1;
    for int(int i = -1; i < 2; i++){
        for int(int j = -1; j < 2; j++){
            if (state[x+i][y+j] == "."){
                result++;
            } else {
                //accounts for a barrier impeding a free space
                if (i==0 || j==0){
                    if (i == 0){
                        if (state[x+1][y+j] == "." || state[x-1][y+j] == "." ){
                            result--;
                        }
                    }
                    if (j == 0){
                        if (state[x+i][y+1] == "." || state[x+i][y-1] == "." ){
                            result--;
                        }
                    }
                    
                }
            }
        }
    }

    return result;
}