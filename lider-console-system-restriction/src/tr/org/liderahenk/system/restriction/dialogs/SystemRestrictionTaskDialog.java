package tr.org.liderahenk.system.restriction.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.system.restriction.dialogs.SystemRestrictionTaskDialog;
import tr.org.liderahenk.liderconsole.core.dialogs.DefaultTaskDialog;
import tr.org.liderahenk.liderconsole.core.exceptions.ValidationException;
import tr.org.liderahenk.system.restriction.constants.SystemRestrictionConstants;
import tr.org.liderahenk.system.restriction.i18n.Messages;

/**
 * Task execution dialog for system-restriction plugin.
 * 
 */
public class SystemRestrictionTaskDialog extends DefaultTaskDialog {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemRestrictionTaskDialog.class);
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
	
	// TODO do not forget to change this constructor if SingleSelectionHandler is used!
	public SystemRestrictionTaskDialog(Shell parentShell, Set<String> dnSet) {
		super(parentShell, dnSet);
	}

	@Override
	public String createTitle() {
		// TODO dialog title
		return Messages.getString("SYSTEM_RESTRICTION");
	}

	@Override
	public Control createTaskDialogArea(Composite parent) {
		// TODO create your task-related widgets here
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		GridData gData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gData.widthHint = 400;
		gData.heightHint = 300;
		composite.setLayoutData(gData);
		
		tabFolder = new TabFolder(composite, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		createDesktopSettingsTab();
		createPanelSettingsTab();
//		createSystemSettingsTab();
		return composite;
	}
	
	private void createDesktopSettingsTab() {
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(Messages.getString("DESKTOP_SETTINGS"));
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
//		masaüstü sağ tık uygulama menüsü aktik/pasif yapma
		btnAppClick = new Button(group, SWT.CHECK);
		btnAppClick.setText(Messages.getString("APP_RIGHT_CLICK"));
		btnAppClick.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbAppClick.setEnabled(btnAppClick.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbAppClick = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbAppClick.setSize(10, 10 );
		String items [] = {Messages.getString("Enabled"), Messages.getString("Disabled")};
		cmbAppClick.setItems(items);
		cmbAppClick.setEnabled(false);
		
//		Masaüstü ayarını engelleme
		btnDesktopSettings = new Button(group, SWT.CHECK);
		btnDesktopSettings.setText(Messages.getString("DESKTOP_SETTINGS_DENY"));
		btnDesktopSettings.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbDesktopSettings.setEnabled(btnDesktopSettings.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbDesktopSettings = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbDesktopSettings.setSize(10, 10 );
		String items2 [] = {Messages.getString("Enabled"), Messages.getString("Disabled")};
		cmbDesktopSettings.setItems(items2);
		cmbDesktopSettings.setEnabled(false);
		
//		uyku, hazırda beklet ayarları
		btnHibernateClick = new Button(group, SWT.CHECK);
		btnHibernateClick.setText(Messages.getString("HIBERNATE_CLICK"));
		btnHibernateClick.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbHibernateClick.setEnabled(btnHibernateClick.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbHibernateClick = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbHibernateClick.setSize(10, 10);
		String items3 [] = {Messages.getString("Enabled"), Messages.getString("Disabled")};
		cmbHibernateClick.setItems(items3);
		cmbHibernateClick.setEnabled(false);
		
		tabItem.setControl(group);
	
	}

	private void createPanelSettingsTab() {
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(Messages.getString("PANEL_SETTINGS"));
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
		btnPanel = new Button(group, SWT.CHECK);
		btnPanel.setText(Messages.getString("TASK_AND_APPLICATION_SETTINGS"));
		btnPanel.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbPanel.setEnabled(btnPanel.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbPanel = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbPanel.setSize(10, 10);
		String items [] = {Messages.getString("Enabled"), Messages.getString("Disabled")};
		cmbPanel.setItems(items);
		cmbPanel.setEnabled(false);
		tabItem.setControl(group);
	}
	
	
	
	private void createSystemSettingsTab() {
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(Messages.getString("SYSTEM_SETTINGS"));
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setLayout(new GridLayout(1, false));

		
//		TableViewerColumn serviceNameColumn = SWTResourceManager.createTableViewerColumn(tableViewer,
//				Messages.getString("SERVICE_NAME"), 250);
//		
//		serviceNameColumn.setLabelProvider(new ColumnLabelProvider());
//		
//		TableViewerColumn desiredStatusColumn = SWTResourceManager.createTableViewerColumn(tableViewer,
//				Messages.getString("DESIRED_SERVICE_STAT"), 250);
//		

	}

	@Override
	public void validateBeforeExecution() throws ValidationException {
		// TODO triggered before task execution
	}
	
	@Override
	public Map<String, Object> getParameterMap() {
		// TODO custom parameter map
		Map<String, Object> params= new HashMap<>();
	
//		params.put("file-content", textMessage.getText());
		if (btnAppClick.getSelection()) {
			if (cmbAppClick.getSelectionIndex() == 0) {
			
				params.put("allow-right-click", "active");
			}
			else {
				params.put("allow-right-click", "passive");
			}
		}
			
		if (btnDesktopSettings.getSelection()) {		
			if (cmbDesktopSettings.getSelectionIndex() == 0) {
				params.put("deny-desktop-settings", "active");
			}
			else {
				params.put("deny-desktop-settings", "passive");
			}
		}
	
		if (btnHibernateClick.getSelection()) {
			if (cmbHibernateClick.getSelectionIndex() == 0) {
				params.put("close-hibernate", "active");
			}
			else {
				params.put("close-hibernate", "passive");
			}
		}
	
		if (btnPanel.getSelection()) {
			if (cmbPanel.getSelectionIndex() == 0) {
				params.put("deny-panel-settings", "active");
			}
			else {
				params.put("deny-panel-settings", "passive");
			}	
		}
		return params;
	}

	@Override
	public String getCommandId() {
		// TODO command id which is used to match tasks with ICommand class in the corresponding Lider plugin
		return "SYSTEM_RESTRICTION";
	}

	@Override
	public String getPluginName() {
		return SystemRestrictionConstants.PLUGIN_NAME;
	}

	@Override
	public String getPluginVersion() {
		return SystemRestrictionConstants.PLUGIN_VERSION;
	}
	
}
