import java.util.*;
import java.awt.*;
public class BattleshipAI
{
  Grid compGrid;
  Random r;
  ArrayList<GridBax> shiplist;
 public BattleshipAI(Grid compGrid)
 {
   compGrid = compGrid;
   newShip(2,guessX(),guessY());
   newShip(3,guessX(),guessY());
   newShip(3,guessX(),guessY());
   newShip(4,guessX(),guessY());
   newShip(5,guessX(),guessY());
 }
 public void init()
 {
   r = new Random();
   shiplist = new ArrayList<GridBax>();
 }
 public ArrayList<GridBax> getShips()
 {
   return shiplist;
 }
 public int guessX()
 {
   return r.nextInt(11);
 }
 public int guessY()
 {
   return r.nextInt(11);
 }
 public void newShip(int length, int x, int y)
{
   boolean orientation = true;
  if(r.nextInt(2) == 0)
  {
    orientation = true;
  }
  else if(r.nextInt(2) == 1)
  {
    orientation = false;
  }
 if(orientation == true)
 {
  for(int i = 0; i < length; i++)
  {
   shiplist.add(compGrid.getBox(x,y+i));
  }
 }
 else if(orientation == false)
 {
  for(int i = 0; i < length; i++)
  {
   shiplist.add(compGrid.getBox(x+i,y));
  }
 }

}
}