import javax.swing.*;
import java.awt.*;

public class GridBax extends JButton
{
 int boxState;
 public GridBax(int State)
 {
  boxState = State;
 }

 public void setState(int set)
 {
  boxState = set;
 }
 public int getState()
 {
  return boxState;
 }
}