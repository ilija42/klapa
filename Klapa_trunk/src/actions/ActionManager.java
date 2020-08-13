package actions;

public class ActionManager {
	
	private static ActionManager instance = null;
	private AboutAction aboutAction;
	private RightClickAction rightClickAction;
	private DoubleClickAction doubleClickAction;

	private NewAction newAction;
	private DeleteAction deleteAction;
	private RenameAction renameAction;
	private ShareDocumentAction shareDocumentAction;
 	private ShareHereAction shareHereAction;
 	private CutDocumentAction cutDocumentAction;
 	private CopyDocumentAction copyDocumentAction;
 	private PasteDocumentAction pasteDocumentAction;
 	private UndoAction undoAction;
 	private RedoAction redoAction;

 	private SaveFileAsAction saveFileAsAction;
 	private ImportFileAction importFileAction;
 	private SwitchAction switchAction;
 	private SaveFileAction saveFileAction;
 	
 	private JinternalFrameResizeAction jinternalFrameResizeAction;
 	private OnFrameClose onFrameClose;
 	private CloseDocumentsAction closeDocumentAction;

 	private PaintRectangleAction paintRectangleAction;
 	private PaintTriangleAction paintTriangleAction;
 	private PaintCircleAction paintCircleAction;
 	private PaintRotateAction paintRotateAction;
 	private PaintResizeAction paintResizeAction;
 	private PaintSelection paintSelection;
 	private PaintDeleteAction paintDeleteAction;
 	private EditElementAction editElementAction;
 	
	private ActionManager() {
		initializeActions();
	}
	
	private void initializeActions() {
		renameAction = new RenameAction();
		aboutAction = new AboutAction();
		rightClickAction = new RightClickAction();
		newAction = new NewAction();
		deleteAction = new DeleteAction();
		doubleClickAction = new DoubleClickAction();
		saveFileAsAction = new SaveFileAsAction();
		importFileAction = new ImportFileAction();
		switchAction = new SwitchAction();
		onFrameClose = new OnFrameClose();
		saveFileAction = new SaveFileAction();
		jinternalFrameResizeAction = new JinternalFrameResizeAction();
		paintRectangleAction = new PaintRectangleAction();
		paintTriangleAction = new PaintTriangleAction();
		paintCircleAction = new PaintCircleAction();
		closeDocumentAction = new CloseDocumentsAction();
		paintRotateAction = new PaintRotateAction();
		paintResizeAction = new PaintResizeAction();
		setPaintSelection(new PaintSelection());
		shareDocumentAction = new ShareDocumentAction();
		shareHereAction = new ShareHereAction();
		cutDocumentAction = new CutDocumentAction();
		copyDocumentAction = new CopyDocumentAction();
		pasteDocumentAction = new PasteDocumentAction();
		paintDeleteAction = new PaintDeleteAction();
		undoAction = new UndoAction();
		redoAction = new RedoAction();
		editElementAction = new EditElementAction();
	}
	
	public EditElementAction getEditElementAction() {
		return editElementAction;
	}

	public PaintDeleteAction getPaintDeleteAction() {
		return paintDeleteAction;
	}

	public PasteDocumentAction getPasteDocumentAction() {
		return pasteDocumentAction;
	}

	public CutDocumentAction getCutDocumentAction() {
		return cutDocumentAction;
	}

	public CopyDocumentAction getCopyDocumentAction() {
		return copyDocumentAction;
	}

	public JinternalFrameResizeAction getJinternalFrameResizeAction() {
		return jinternalFrameResizeAction;
	}
	
	public OnFrameClose getOnFrameClose() {
		return onFrameClose;
	}
	
	public SwitchAction getSwitchAction() {
		return switchAction;
	}
	
	public SaveFileAsAction getSaveFileAsAction() {
		return saveFileAsAction;
	}
	
	public SaveFileAction getSaveFileAction() {
		return saveFileAction;
	}
	
	public ImportFileAction getImportFileAction() {
		return importFileAction;
	}
	
	public RenameAction getRenameAction() {
		return renameAction;
	}
	
	public AboutAction getAboutAction() {
		return aboutAction;
	}
	
	public void setAboutAction(AboutAction aboutAction) {
		this.aboutAction = aboutAction;
	}

	public RightClickAction getRightClickAction() {
		return rightClickAction;
	}
	
	public DoubleClickAction getDoubleClickAction() {
		return doubleClickAction;
	}
	
	public NewAction getNewAction() {
		return newAction;
	}
	
	public DeleteAction getDeleteAction() {
		return deleteAction;
	}

	public static ActionManager getInstance() {
		if (instance == null) {
			instance = new ActionManager();
		}
		return instance;
	}

	public CloseDocumentsAction getCloseDocumentAction() {
		return closeDocumentAction;
	}
	
	public PaintRectangleAction getPaintRectangleAction() {
		return paintRectangleAction;
	}
	
	public PaintCircleAction getPaintCircleAction() {
		return paintCircleAction;
	}
	
	public PaintTriangleAction getPaintTriangleAction() {
		return paintTriangleAction;
	}
	
	public PaintRotateAction getPaintRotateAction() {
		return paintRotateAction;
	}
	
	public PaintResizeAction getPaintResizeAction() {
		return paintResizeAction;
	}

	public PaintSelection getPaintSelection() {
		return paintSelection;
	}

	public void setPaintSelection(PaintSelection paintSelection) {
		this.paintSelection = paintSelection;
	}
	
	public ShareDocumentAction getShareDocumentAction() {
		return shareDocumentAction;
	}
	
	public ShareHereAction getShareHereAction() {
		return shareHereAction;
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}
	
}
