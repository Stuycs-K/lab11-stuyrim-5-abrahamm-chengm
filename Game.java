import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  public static final int WHITE = 37;
  public static final int BACKGROUND = 10;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    for (int col = 1; col <= 80; col++) {
      drawText(Text.colorize("_",WHITE,WHITE+BACKGROUND),1,col);
      drawText(Text.colorize("_",WHITE,WHITE+BACKGROUND),6,col);
      drawText(Text.colorize("_",WHITE,WHITE+BACKGROUND),25,col);
      drawText(Text.colorize("_",WHITE,WHITE+BACKGROUND),30,col);
    }

    for (int row = 2; row <= 6; row++) {
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,26);
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,51);
    }
    for (int row = 7; row <= 24; row++) {
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,38);
    }
    for (int row = 25; row <= 29; row++) {
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,26);
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,51);
    }
    for (int row = 2; row <= 30; row++) {
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,1);
      drawText(Text.colorize("|",WHITE,WHITE+BACKGROUND),row,80);
    }
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    String[] words = text.split(" ");
    int row2 = row;
    String res = "";

    for (String word: words){
      if (res.length()+word.length()<width){
        if (res.length()==0)res+=word;
        else res += " "+word;
      }
      else{
        drawText(res + " ".repeat(width - res.length()), row2++, col);
        res = word;
        if (row2 >= row + height) break;
      }
    }
    if (row2<row+height){
      drawText(res + " ".repeat(width - res.length()), row2++, col);
    }
    while (row2 < row + height){
      drawText(" ".repeat(width), row2++, col);
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int find = (int)(Math.random()*3);
      int find2 = (int)(Math.random()*3);
      if(find == 0){
        if (find2 == 0){
          return new QB();
        }
        else if (find2 == 1){
          return new QB("Burrow");
        }
        else{
          return new QB("Lock");
        }
      }
      else if(find == 1){
        if (find2 == 0){
          return new RB();
        }
        else if (find2 == 1){
          return new RB("Barkley");
        }
        else{
          return new RB("Henry");
        }
      }
      else{
        if (find2 == 0){
          return new DL();
        }
        else if (find2 == 1){
          return new DL("Heyward");
        }
        else{
          return new DL("Hendrickson");
        }
    }
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      for (int is = 0;is<party.size();is++){
        Text.go(startRow,3+is*25);
        System.out.print(party.get(is).getName());
        Text.go(startRow+1,3+is*25);
        System.out.print("HP: "+colorByPercent(party.get(is).getHP(), party.get(is).getmaxHP()));
        Text.go(startRow+2,3+is*25);
        System.out.print(party.get(is).getSpecialName()+": "+party.get(is).getSpecial() + "/" + party.get(is).getSpecialMax());
      }
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    if ( maxHP / 4.0 >hp){
      output = "\u001b[" + Text.RED + "m" + output + "\u001b[0m";
    }
    else if((maxHP * 3 / 4.0 ) > hp){
      output = "\u001b[" + Text.YELLOW + "m" + output + "\u001b[0m";
    }
    return output;
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer>enemies,ArrayList<Adventurer>friends){

    drawBackground();

    drawParty(enemies,2);

    drawParty(friends,26);

    Text.go(8,3);
  }

  public static String userInput(Scanner in){
      Text.go(22,3);

      Text.showCursor();

      String input = in.nextLine();

      Text.clear();

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    int enmnum = (int)(Math.random()*3 + 1);
    if (enmnum == 1){
      enemies.add(new MahomesBoss());
    }
    else{
    for (int i22=1;i22<enmnum+ 1;i22++){
      enemies.add(createRandomAdventurer());
    }
  }
    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    int frinum = (int)(Math.random()*2);
    for(int i = 0; i < frinum+2; i++){
      party.add(createRandomAdventurer());
    }
    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(enemies,party);//initial state.
    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    TextBox(8,3,34,2,preprompt);
    boolean end = false;
    boolean endParty = false;
    while((! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))) && !end && !endParty){
      input = userInput(in);

      //example debug statment
      //TextBox(11,2,20,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      
      for ( int i = 0; i < enemies.size(); i ++){
        if (enemies.get(i).getHP() <= 0){
          end = true;
        }
      }
      if(partyTurn && !end){
        
        
        boolean works = false;
        //Process user input for the last Adventurer:
        if(input.startsWith("attack") || input.startsWith("a")){
          works = true;
          if(input.contains("0")){
            TextBox(14,3,34,10,party.get(whichPlayer).attack(enemies.get(0)));
          }
          if(input.contains("1") && enemies.size() >1 ){
            TextBox(14,3,34,10,party.get(whichPlayer).attack(enemies.get(1)));
          }
          if(input.contains("2") && enemies.size()  > 2){
            TextBox(14,3,34,10,party.get(whichPlayer).attack(enemies.get(2)));
          }
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          works = true;
          if(input.contains("0")){
            TextBox(14,3,34,10,party.get(whichPlayer).specialAttack(enemies.get(0)));
          }
          if(input.contains("1") && enemies.size() >1 ){
            TextBox(14,3,34,10,party.get(whichPlayer).specialAttack(enemies.get(1)));
          }
          if(input.contains("2") && enemies.size()  > 2){
            TextBox(14,3,34,10,party.get(whichPlayer).specialAttack(enemies.get(2)));
          }
        }
        else if(input.startsWith("su") || input.startsWith("support")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          
          works = true;
          if(input.contains("0")){
            if (whichPlayer == 0){
              TextBox(14,3,34,10,party.get(whichPlayer).support());
            }
            else{
              TextBox(14,3,34,10,party.get(whichPlayer).support(party.get(0)));
            }
          }
          if(input.contains("1") && party.size() >1 ){
            if (whichPlayer == 1){
              TextBox(14,3,34,10,party.get(whichPlayer).support());
            }
            else{
              TextBox(14,3,34,10,party.get(whichPlayer).support(party.get(1)));
            }
          }
          if(input.contains("2") && party.size()  > 2){
            if (whichPlayer == 2){
              TextBox(14,3,34,10,party.get(whichPlayer).support());
            }
            else{
              TextBox(14,3,34,10,party.get(whichPlayer).support(party.get(2)));
            }
          }
          if(input.contains("3") && party.size()  > 3){
            if (whichPlayer == 3){
              TextBox(14,3,34,10,party.get(whichPlayer).support());
            }
            else{
              TextBox(14,3,34,10,party.get(whichPlayer).support(party.get(3)));
            }
          }
        }
        String reprompt = "Re-enter command for "+party.get(whichPlayer)+": attack/special/quit";
        if ((!(input.startsWith("a ")) && !(input.startsWith("attack ")) && !(input.startsWith("sp ")) && !(input.startsWith("special ")) && !(input.startsWith("su ")) && !(input.startsWith("support ")) ) || (!(input.contains("0")) && !(input.contains("1")) && !(input.contains("2")))){
          works = false;
          TextBox(8,3,34,2,reprompt);
        }
        if (((enemies.size() < 3) && input.contains("2") && ((input.startsWith("attack ")) || (input.startsWith("a ")) ||(input.startsWith("sp ")) ||(input.startsWith("special "))  )) || ((enemies.size() < 2) && input.contains("1") && ((input.startsWith("attack ")) || (input.startsWith("a ")) || input.startsWith("special ") || input.startsWith("sp ")))){
          works = false;
          TextBox(8,3,34,2,reprompt);
        }
        if(whichPlayer < party.size() -1 && works && !end){
          //This is a player turn.
          //Decide where to draw the following prompt:
          whichPlayer++;
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          TextBox(20,3,34,10,prompt);
        }else if(works && !end){
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see next monster's turn";
          TextBox(22,3,34,10,prompt);
          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
        
      }
      else if(end){
        TextBox(22,3,34,10,"Game over");
        quit();
      }
      for(int i = 0; i < party.size(); i++){
        if(party.get(i).getHP() < 0){
          endParty = true;
        }
      }
       if (!(endParty) && !(partyTurn)){
        //not the party turn!
        

        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        int random = (int)(Math.random()*3);
        int randomPlayer = (int)(Math.random() * party.size());
        if(random == 0){
          if(randomPlayer == 0){
            TextBox(11,42,34,10,enemies.get(whichOpponent).attack(party.get(0)));
          }
          if((randomPlayer == 1) && party.size() >1 ){
            TextBox(11,42,34,10,enemies.get(whichOpponent).attack(party.get(1)));
          }
          if(randomPlayer == 2 && party.size()  > 2){
            TextBox(11,42,34,10,enemies.get(whichOpponent).attack(party.get(2)));
          }
        }
        if(random == 1){
          if(randomPlayer == 0){
            TextBox(11,42,34,10,enemies.get(whichOpponent).specialAttack(party.get(0)));
          }
          if((randomPlayer == 1) && party.size() >1 ){
            TextBox(11,42,34,10,enemies.get(whichOpponent).specialAttack(party.get(1)));
          }
          if(randomPlayer == 2 && party.size()  > 2){
            TextBox(11,42,34,10,enemies.get(whichOpponent).specialAttack(party.get(2)));
          }
        }
        if(random == 2){
        if(whichOpponent == 0){
          if (whichOpponent == 0){
            TextBox(11,42,34,10,enemies.get(whichOpponent).support());
          }
          else{
            TextBox(11,42,34,10,enemies.get(whichOpponent).support(enemies.get(0)));
          }
        }
        if(whichOpponent == 1 && enemies.size() >1 ){
          if (whichOpponent == 1){
            TextBox(11,42,34,10,enemies.get(whichOpponent).support());
          }
          else{
            TextBox(11,42,34,10,enemies.get(whichOpponent).support(enemies.get(1)));
          }
        }
        if(whichOpponent == 2 && enemies.size() > 2){
          if (whichOpponent == 2){
            TextBox(11,42,34,10,enemies.get(whichOpponent).support());
          }
          else{
            TextBox(11,42,34,10,enemies.get(whichOpponent).support(enemies.get(2)));
          }
        }
      }
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        if(whichOpponent < enemies.size()- 1){
          //This is a player turn.
          //Decide where to draw the following prompt:
          whichOpponent++;
          String prompt = "Press enter for "+enemies.get(whichOpponent)+" next turn.";
          TextBox(15,42,34,10,prompt);
        }
        else {
          //THIS BLOCK IS TO END THE ENEMY TURN
          //It only triggers after the last enemy goes.
          turn++;
          whichPlayer = 0;
          partyTurn=true;
          //display this prompt before player's turn
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          TextBox(20,3,34,10,prompt);
        }
      }
        

      //end of one enemy.
      //display the updated screen after input has been processed.
      drawScreen(enemies,party);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
