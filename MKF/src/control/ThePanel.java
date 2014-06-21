package control;

import javax.swing.JPanel;


/**
 * The JPanel
 * 
 * @author Tom Capach
 *
 */
public class ThePanel extends JPanel
{

	public ThePanel()
	{
		
		this.setIgnoreRepaint(true);
		this.setFocusable(true);
		this.addMouseListener(CoreClass.mightyMouse);
		this.addMouseMotionListener(CoreClass.mightyMouse);
		
	}
	
}
