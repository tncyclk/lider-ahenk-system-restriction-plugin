package tr.org.liderahenk.system.restriction.dialogs;

import java.util.Map;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.liderconsole.core.dialogs.IProfileDialog;
import tr.org.liderahenk.liderconsole.core.exceptions.ValidationException;
import tr.org.liderahenk.liderconsole.core.model.Profile;

public class SystemRestrictionProfileDialog implements IProfileDialog {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemRestrictionProfileDialog.class);
	private TableViewer viewerDesktopSettings;
	private TableViewer viewerSystemSettins; 
	private TabFolder tabFolder;
	private TableViewer tableViewer;
	private Button btnAppClick;
	private Button btnDesktopSettings;
	private Button btnHibernateClick;
	private Button btnPanel;
	private final String[] statusArr = new String[] { "ENABLE", "DISABLE" };
	
	private Combo cmbPanel;
	private Combo cmbAppClick;
	private Combo cmbDesktopSettings;
	private Combo cmbHibernateClick;
	
	
	@Override
	public void init() {
		// TODO initialize 
	}
	
	@Override
	public void createDialogArea(Composite parent, Profile profile) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		btnAppClick = new Button(composite, SWT.CHECK);
		
		
//		Composite composite = new Composite(parent, SWT.NONE);
//		composite.setLayout(new GridLayout(2, false));
//
//		GridData gData = new GridData(SWT.FILL, SWT.FILL, false, false);
//		gData.widthHint = 400;
//		gData.heightHint = 300;
//		composite.setLayoutData(gData);
//		
//		tabFolder = new TabFolder(composite, SWT.BORDER);
//		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//		
//		// TODO create input widgets
//		logger.debug("Profile recieved: {}", profile != null ? profile.toString() : null);
//		createSystemRestrictionInputs(parent, profile);
	}
	
//	private void createSystemRestrictionInputs(Composite parent, Profile profile) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public Map<String, Object> getProfileData() throws Exception {
		// TODO return profile data collected from the input widgets
		return null;
	}
	
	@Override
	public void validateBeforeSave() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
}
