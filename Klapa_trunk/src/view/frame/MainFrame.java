package view.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeModel;

import FileHandling.WorkSpaceSerializationHandler;
import actions.ActionManager;
import errorHandling.ErrorHandler;
import errorHandling.ErrorHandlerView;
import interfaces.DatabaseModelInterface;
import interfaces.DatabaseViewInterface;
import observer.MyObserverInterface;
import tree.workspace.WorkSpace;
import tree.workspace.WorkSpaceModel;
import tree.workspace.WorkSpaceTree;
import view.components.Menu;
import view.components.MyDesktop;
import view.components.PaintToolBar;
import view.components.Toolbar;
import view.desktop.ProjectViewManager;

public class MainFrame extends JFrame implements ClipboardOwner{
	
	private static final long serialVersionUID = 1L;
	private static MainFrame instance = null;
	private ActionManager actionManager;
	private Menu menu;
	private Toolbar toolbar;
	private JSplitPane sp;
	private JPanel left;
	private DatabaseModelInterface wsModel;
	private DatabaseViewInterface wsTree;
	private MyDesktop desktop;
	private PaintToolBar paintBar;
	ErrorHandlerView errorHandlerView;
    private Clipboard clipboard;

	
	private void initialize(){
		 clipboard=new Clipboard("Gerudok clipboard"); 
		 actionManager = ActionManager.getInstance();
		 errorHandlerView = new ErrorHandlerView();
		 ErrorHandler.getInstance().addObserver(errorHandlerView);
		 
         initializeGUI();
         initializeWorkspace();
         left.add((Component) wsTree);
    }
	 
	public Clipboard getClipboard() {
		return clipboard;
	}

	public void setClipboard(Clipboard clipboard) {
		this.clipboard = clipboard;
	}

	public void initializeWorkspace() {
		
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(ActionManager.getInstance().getOnFrameClose());
		
		WorkSpace ws;
		
		if(( ws = (WorkSpace)WorkSpaceSerializationHandler.getInstance().open())!=null) {
			wsModel = new WorkSpaceModel(ws);
		}else  {
			wsModel = new WorkSpaceModel(new WorkSpace());
		}
		
        wsTree = new WorkSpaceTree();
        ((JTree) wsTree).setModel((TreeModel) wsModel);
        ((Component) wsTree).setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        ((JTree) wsTree).setRowHeight(30);
        wsModel.addObserver(wsTree);
        wsModel.addObserver((MyObserverInterface) ProjectViewManager.getInstance());
        SwingUtilities.updateComponentTreeUI(this);
        left.removeAll();
        left.add((Component) wsTree);
        this.repaint();
	}
	
	public void initializeGUI() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        screenWidth = screenWidth - (screenWidth/2)/2;
        screenHeight = screenHeight - (screenHeight/2)/2+100;
        setSize(screenWidth , screenHeight );      
        setLocationRelativeTo(null);
        
        setTitle("Klapa");
        setLayout(new BorderLayout());

        menu = new Menu();
        setJMenuBar(menu);
        toolbar = new Toolbar();
        add(toolbar, BorderLayout.NORTH);
       
        paintBar = new PaintToolBar();
        paintBar.setOrientation(JToolBar.VERTICAL);
        paintBar.setFloatable(false);
        add(paintBar,BorderLayout.EAST);
        
      
        left = new JPanel();
        desktop = new MyDesktop();
        
   
        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,desktop); 
        sp.setDividerLocation(150);
        sp.setEnabled(false);
        add(sp, BorderLayout.CENTER);
        setVisible(true);
	}
	
	public static MainFrame getInstance(){
		   if (instance == null){
			  instance  = new MainFrame();
			  instance.initialize();
			  
		   }
		   return instance;
	   }
	
	public DatabaseViewInterface getDatabaseView() {
		return wsTree;
	}
	
	public void setDatabaseView(DatabaseViewInterface dv) {
		this.wsTree = dv;
	}
	
	public ActionManager getActionManager() {
		return actionManager;
	}
	
	public JDesktopPane getDesktop() {
		return desktop;
	}
	
	public WorkSpace getWorkSpace() {
		return (WorkSpace)((WorkSpaceModel)wsModel).getRoot();
	}
	
	public JToolBar getPaintBar() {
		return paintBar;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}
	
}
