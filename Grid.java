import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends JPanel
{
 JRadioButton verticle;
 JRadioButton horizontal;
 JRadioButton patrol;
 JRadioButton battleship;
 JRadioButton carrier;
 JRadioButton destroyer;
 JRadioButton sub;
 GridBax[][] grid;
 int shipLength;
 boolean orientation;
 int shipType;
 BattleshipDriver driver;
 JPanel radioPanel;
 public Grid(BattleshipDriver driver)
 {
	 radioPanel = new JPanel(new GridLayout(1,7));
	this.driver = driver;
  setLayout(new GridLayout(10,10));
  grid = new GridBax[10][10];
  char row = 'A';
  verticle = new JRadioButton("verticle");
  horizontal = new JRadioButton("horizontal");
  patrol = new JRadioButton("patrol boat (2)");
  battleship = new JRadioButton("Battleship (4)");
  carrier = new JRadioButton("Aircraft Carrier (5)");
  destroyer = new JRadioButton("Destroyer (3)");
  sub = new JRadioButton("Submarine (3)");
  ButtonGroup ship = new ButtonGroup();
  ButtonGroup position = new ButtonGroup();
  position.add(verticle);
  position.add(horizontal);
  ship.add(patrol);
  ship.add(battleship);
  ship.add(carrier);
  ship.add(destroyer);
  ship.add(sub);

 for(int a = 0; a<10; a++)
 {

  for(int b = 1; b<11; b++)
  {
   grid[a][b-1] = new GridBax(0);
   grid[a][b-1].setText(row + "" + b);
   add(grid[a][b-1]);
   grid[a][b-1].addActionListener(driver);
  }
  row++;
 }

 }
 public JPanel getRadioPanel()
 {
	 radioPanel.add(patrol);
	 radioPanel.add(sub);
	 radioPanel.add(destroyer);
	 radioPanel.add(battleship);
	 radioPanel.add(carrier);
	 radioPanel.add(horizontal);
	 radioPanel.add(verticle);
 	 return radioPanel;
 }
 public void end(boolean win)
 {
	 if(win == true)
	 {
	 	for(int x = 0;x<10;x++)
	 	{
		for(int y = 0;y<10;y++)
		{
			getBox(x,y).setEnabled(false);
		}
		}
	 }
 }
 public boolean getOrientation()
 {
  return orientation;
 }
 public int getLength()
 {
  return shipLength;
 }
 public JRadioButton getPatrol()
 {
	 return patrol;
 }
 public JRadioButton getDestroyer()
  {
 	 return destroyer;
 }
 public JRadioButton getSub()
  {
 	 return sub;
 }
 public JRadioButton getBattleship()
  {
 	 return battleship;
 }
 public JRadioButton getCarrier()
  {
 	 return carrier;
 }
 public JRadioButton getHorizontal()
  {
 	 return horizontal;
 }
 public JRadioButton getVertical()
  {
 	 return verticle;
 }
 public GridBax[][] getGrid()
 {
  return grid;
 }
 public void setState(GridBax box, int x)
 {
  box.setState(x);
 }
 public GridBax getBox(int x, int y)
 {
  return grid[x][y];
 }
 public void active()
 {
   if(patrol.isSelected())
     patrol.setEnabled(false);
   if(destroyer.isSelected())
     destroyer.setEnabled(false);
   if(sub.isSelected())
     sub.setEnabled(false);
   if(battleship.isSelected())
     battleship.setEnabled(false);
   if(carrier.isSelected())
    carrier.setEnabled(false);
 }
 public int getType()
 {
   return shipType;
 }

}