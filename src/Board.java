import java.util.ArrayList;

public class Board {
    private String row1;
    private String row2;
    private String row3;
    private ArrayList<String> rows;
    private String row4;
    public Board(){
        row1 = "123";
        row2 = "456";
        row3 = "789";
        rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
    }

    public String printBoard(){
        return (row1+"\n"+row2+"\n"+row3+"\n");
    }
    public ArrayList<String> getBoard(){
        return rows;
    }



    public void makeMove(int in, String player){
        int i = in-1;
        if (i/3==0) {
            row1 = row1.substring(0, i) + player + row1.substring(i+1, row1.length());
            rows.set(0,row1);
        }
        else if (i/3==1) {
            row2 = row2.substring(0, i % 3) + player + row2.substring(i%3+1, row2.length());
            rows.set(1,row2);
        }
        else {
            row3 = row3.substring(0, i % 3) + player + row3.substring(i % 3+1, row3.length());
            rows.set(2,row3);
        }
    }

    public void undoMove (int in, String player){
        int i = in-1;
        if (i/3==0) {
            row1 = row1.substring(0, i) + in + row1.substring(i+1, row1.length());
            rows.set(0,row1);
        }
        else if (i/3==1) {
            row2 = row2.substring(0, i % 3) + in + row2.substring(i%3+1, row2.length());
            rows.set(1,row2);
        }
        else {
            row3 = row3.substring(0, i % 3) + in + row3.substring(i % 3+1, row3.length());
            rows.set(2,row3);
        }
    }

    public String checkWin(String turn){

        boolean cats = true;

        if (row1.substring(0,1).equals(row2.substring(1,2)) && row2.substring(1,2).equals(row3.substring(2,3)) && row2.substring(1,2).equals(turn)){
            return "w";
        }
        if (row3.substring(0,1).equals(row2.substring(1,2)) && row2.substring(1,2).equals(row1.substring(2,3)) && row2.substring(1,2).equals(turn)){
            return "w";
        }

        for (String r:rows){
            for (int i = 0; i < 3; i++) {
                if (!(r.substring(i,i+1).equals("X") || r.substring(i,i+1).equals("O")))
                    cats = false;
            }
        }

        boolean rowBool = false;
        boolean colBool = false;
        //Checking Rows
        for (String r: rows){
            if ((r.substring(0,1).equals(r.substring(1,2)) && r.substring(2,3).equals(r.substring(1,2)) && r.substring(1,2).equals(turn)))
                rowBool = true;

        }

        //Checking Column
        for (int i=0;i<=2;i++){
            if ((row1.substring(i,i+1).equals(row2.substring(i,i+1))&& row3.substring(i,i+1).equals(row2.substring(i,i+1)) && row2.substring(i,i+1).equals(turn)))
                colBool=true;
        }
        if (rowBool || colBool)
            return "w";
        else if (cats)
            return "c";
        return "n";
    }

    public ArrayList<Integer> getEmpty() {
        ArrayList<Integer> temp = new ArrayList<>();
        for(int j = 0; j<3; j++){
            String r = rows.get(j);
            for (int i = 0; i < 3; i++) {
                if ((!r.substring(i,i+1).equals("X") && !r.substring(i,i+1).equals("O")))
                    temp.add(3*j + i + 1);
            }
        }
        return temp;

    }
}
