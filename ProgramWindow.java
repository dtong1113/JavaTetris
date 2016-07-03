import java.awt.*;

public class ProgramWindow extends Frame
{

	TetrisPanel panel=new TetrisPanel();
	public ProgramWindow()
	{
		add(panel);
		setResizable(false);
		setVisible(true);
		setSize (800, 600);
		setLocation (100,150);

	}
}