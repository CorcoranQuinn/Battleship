import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class BattleshipDriver extends JApplet implements ActionListener
{
Grid playerGrid;
Grid compGrid;
ArrayList<GridBax> patrol;
ArrayList<GridBax> destroyer;
ArrayList<GridBax> sub;
ArrayList<GridBax> battleship;
ArrayList<GridBax> carrier;
ArrayList<GridBax> shiplist;
BattleshipGUI screen;
int play;
int win;
int shipType;
BattleshipAI comp;
Random r;

boolean orientation;

public void init()
{
  r = new Random();
  shiplist = new ArrayList<GridBax>();
  Container c = getContentPane();
  c.setLayout(new GridLayout(3,1));
  playerGrid = new Grid(this);
  compGrid = new Grid(this);
  orientation = false;
  patrol = new ArrayList<GridBax>();
  destroyer = new ArrayList<GridBax>();
  sub = new ArrayList<GridBax>();
  battleship = new ArrayList<GridBax>();
  carrier = new ArrayList<GridBax>();
  screen = new BattleshipGUI();
  c.add(playerGrid);
  c.add(compGrid);
  c.add(playerGrid.getRadioPanel());
  play = 0;
  //comp = new BattleshipAI(compGrid);
  //newCompShip(2,r.nextInt(11),r.nextInt(11));
  //newCompShip(3,r.nextInt(11),r.nextInt(11));
  //newCompShip(3,r.nextInt(11),r.nextInt(11));
  //newCompShip(4,r.nextInt(11),r.nextInt(11));
  //newCompShip(5,r.nextInt(11),r.nextInt(11));
}

public boolean canPlace(int length, int x, int y, ArrayList<GridBax> ship)
{
  boolean place = false;
  for(int a = 0; a < length; a++)
  {
    if(orientation == true)
    {
      if(y+a > 10)
        place = false;
    }
    else if(orientation == false)
    {
      if(x+a > 10)
        place = false;
    }
    else
    {
      place = true;
    }

    for(int i = 0; i < shiplist.size(); i++)
    {
      if(ship.get(a) == shiplist.get(i))
      {
        place = false;
      }
    }

  }
  return place;
}
public ArrayList<GridBax> newShip(int length,int x, int y)
{

 ArrayList<GridBax> newShip = new ArrayList<GridBax>();

 if(orientation == true)
 {
  for(int i = 0; i < length; i++)
  {
   System.out.println("x: "+ x + "y: " + y);
   newShip.add(playerGrid.getBox(x,y+i));
   playerGrid.getBox(x+i,y).setBackground(Color.RED);
   shiplist.add(playerGrid.getBox(x,y+i));
  }
 }
 else if(orientation == false)
 {
  for(int i = 0; i < length; i++)
  {
   playerGrid.getBox(x,y+i).setBackground(Color.RED);
   newShip.add(playerGrid.getBox(x+i,y));
   shiplist.add(playerGrid.getBox(x+i,y));
  }
 }
 if(canPlace(length,x,y,newShip) == false)
   return null;
 else
 return newShip;
}
public void used()
{
 for(int x = 0; x < 10; x++)
  {
   for(int y = 0; y < 10; y++)
   {
     if(playerGrid.getBox(x,y).getState() == 2)
     {
   playerGrid.getBox(x,y).setEnabled(false);
  }
    }
  }
}
public ArrayList<GridBax> newCompShip(int length,int x, int y)
{

 ArrayList<GridBax> newShip = new ArrayList<GridBax>();

 if(orientation == true)
 {
  for(int i = 0; i < length; i++)
  {
   System.out.println("x: "+ x + "y: " + y);
   newShip.add(compGrid.getBox(x,y+i));
   compGrid.getBox(x+i,y).setBackground(Color.RED);
   shiplist.add(compGrid.getBox(x,y+i));
  }
 }
 else if(orientation == false)
 {
  for(int i = 0; i < length; i++)
  {
   compGrid.getBox(x,y+i).setBackground(Color.RED);
   newShip.add(compGrid.getBox(x+i,y));
   shiplist.add(compGrid.getBox(x+i,y));
  }
 }
 if(canPlace(length,x,y,newShip) == false)
   return null;
 else
 return newShip;
}


public void actionPerformed(ActionEvent e)
{
 //playerGrid.end(win);
 used();
 for(int y = 0; y < 10; y++)
 {
  for(int x = 0; x < 10; x++)
  {

   if(playerGrid.getBox(x,y) == e.getSource() && play < 5)
   {


   if(playerGrid.getPatrol().isSelected())
   //playerGrid.getBox(x,y).setBackground(Color.RED);
   shipType = 1;

   if(playerGrid.getSub().isSelected())
   shipType = 2;
   if(playerGrid.getDestroyer().isSelected())
   shipType = 3;
   if(playerGrid.getBattleship().isSelected())
   shipType = 4;
   if(playerGrid.getCarrier().isSelected())
   shipType = 5;
   if(playerGrid.getVertical().isSelected())
   orientation = true;
   if(playerGrid.getHorizontal().isSelected())
   orientation = false;
   System.out.println(shipType);
    if(shipType == 1)
    {
      patrol = newShip(2,x,y);
      playerGrid.active();
      play++;
 }
    if(shipType == 2)
    {
  sub = newShip(3,x,y); playerGrid.active(); play++;
 }
    if(shipType == 3)
    {
  destroyer = newShip(3,x,y); playerGrid.active(); play++;
    }
    if(shipType == 4)
    {
    battleship = newShip(4,x,y); playerGrid.active(); play++;
 }
    if(shipType == 5)
    {
     //if(newShip(5,x,y) == null)
     //{
      // System.out.println("You can't place a ship there");
    // }
     //else
     {
      carrier = newShip(5,x,y);
      playerGrid.active();
      play++;
     }
     }
   }
   if(compGrid.getBox(x,y) == e.getSource() && play >=5)
   {
     System.out.println(comp.getShips().size());
     if(compGrid.getBox(x,y).getState() == 0)
     {
       compGrid.getBox(x,y).setBackground(Color.BLUE);
         System.out.println("miss");
     }
     else if(compGrid.getBox(x,y).getState() == 1)
     {
      compGrid.getBox(x,y).setState(2);
      compGrid.getBox(x,y).setBackground(Color.BLACK);
      System.out.println("hit");
      win++;
  }
     if(win == 17)
     {
       System.out.println("You win!");
     }
   }
  }
 }

}

}