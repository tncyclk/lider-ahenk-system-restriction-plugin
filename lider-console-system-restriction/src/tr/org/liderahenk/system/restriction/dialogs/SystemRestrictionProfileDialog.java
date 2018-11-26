package tr.org.liderahenk.system.restriction.dialogs;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.org.liderahenk.liderconsole.core.dialogs.IProfileDialog;
import tr.org.liderahenk.liderconsole.core.exceptions.ValidationException;
import tr.org.liderahenk.liderconsole.core.model.Profile;
import tr.org.liderahenk.system.restriction.constants.SystemRestrictionConstants;
import tr.org.liderahenk.system.restriction.i18n.Messages;
import tr.org.liderahenk.system.restriction.utils.SystemRestrictionUtils;

public class SystemRestrictionProfileDialog implements IProfileDialog {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemRestrictionProfileDialog.class);
	private TabFolder tabFolder;
	
	private Button btnAppRightClick;
	private Button btnDesktopSettings;
	private Button btnHibernateClick;
	private Button btnPanelPreferences;
	
	private final String[] statusArr = new String[] { "ENABLE", "DISABLE" };
	private final String[] statusValueArr = new String[] { "1", "0" };
	
	private Combo cmbPanelPreferences;
	private Combo cmbAppRightClick;
	private Combo cmbDesktopSettings;
	private Combo cmbHibernateClick;
	
	private SystemSettingsDialog systemSettingsDialog;
	
	@Override
	public void init() {	
		// TODO initialize
	}
	
	@Override
	public void createDialogArea(Composite parent, Profile profile) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		tabFolder = new TabFolder(composite, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		createDesktopSettingsTab(profile);
		createPanelSettingsTab(profile);
		systemSettingsDialog = new SystemSettingsDialog(parent.getShell(), tabFolder);
		systemSettingsDialog.createSystemSettingsTab(profile);
	}
	
	private boolean selectOption(Combo combo, Object value) {
		if (value == null) {
			return false;
		}
		for (int i = 0; i < statusValueArr.length; i++) {
			if (statusValueArr[i].equalsIgnoreCase(value.toString())) {
				combo.select(i);
				return true;
			}
		}
		combo.select(0); // select first option by default.
		return false;
	}
	
	private void createDesktopSettingsTab(Profile profile) {
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(Messages.getString("DESKTOP_SETTINGS"));
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
//		masaüstü sağ tık uygulama menüsü aktik/pasif yapma
		btnAppRightClick = new Button(group, SWT.CHECK);
		btnAppRightClick.setText(Messages.getString("APP_RIGHT_CLICK"));
		btnAppRightClick.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbAppRightClick.setEnabled(btnAppRightClick.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbAppRightClick = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbAppRightClick.setSize(10, 10 );
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbAppRightClick.add(i18n);
				cmbAppRightClick.setData(i + "", statusValueArr[i]);
			}
		}
		boolean isSelected_1 = selectOption(cmbAppRightClick, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.APP_RIGHT_CLICK) : null);
		cmbAppRightClick.setEnabled(isSelected_1);
		btnAppRightClick.setSelection(isSelected_1);
		
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
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbDesktopSettings.add(i18n);
				cmbDesktopSettings.setData(i + "", statusValueArr[i]);
			}
		}
		
		boolean isSelected_2 = selectOption(cmbDesktopSettings, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.DESKTOP_SETTINGS) : null);
		cmbDesktopSettings.setEnabled(isSelected_2);
		btnDesktopSettings.setSelection(isSelected_2);
		
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
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbHibernateClick.add(i18n);
				cmbHibernateClick.setData(i + "", statusValueArr[i]);
			}
		}
		boolean isSelected_3 = selectOption(cmbHibernateClick, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.HIBERNATE) : null);
		cmbHibernateClick.setEnabled(isSelected_3);
		btnHibernateClick.setSelection(isSelected_3);
		tabItem.setControl(group);
	}

	private void createPanelSettingsTab(Profile profile) {
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(Messages.getString("PANEL_SETTINGS"));
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
		btnPanelPreferences = new Button(group, SWT.CHECK);
		btnPanelPreferences.setText(Messages.getString("TASK_AND_APPLICATION_SETTINGS"));
		btnPanelPreferences.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbPanelPreferences.setEnabled(btnPanelPreferences.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		cmbPanelPreferences = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbPanelPreferences.setSize(10, 10);
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbPanelPreferences.add(i18n);
				cmbPanelPreferences.setData(i + "", statusValueArr[i]);
			}
		}
		boolean isSelected = selectOption(cmbPanelPreferences, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.PANEL_PREFERENCES) : null);
		cmbPanelPreferences.setEnabled(isSelected);
		btnPanelPreferences.setSelection(isSelected);
		tabItem.setControl(group);
	}
	
	@Override
	public Map<String, Object> getProfileData() throws Exception {
		// TODO return profile data collected from the input widgets
		Map<String, Object> profileData = new HashMap<String, Object>();
		
		if (btnAppRightClick.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.APP_RIGHT_CLICK, SystemRestrictionUtils.getSelectedValue(cmbAppRightClick));
		}
		if (btnDesktopSettings.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.DESKTOP_SETTINGS, SystemRestrictionUtils.getSelectedValue(cmbDesktopSettings));
		}
		
		if (btnHibernateClick.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.HIBERNATE, SystemRestrictionUtils.getSelectedValue(cmbHibernateClick));
		}
		
		if (btnPanelPreferences.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.PANEL_PREFERENCES, SystemRestrictionUtils.getSelectedValue(cmbPanelPreferences));
		}
		if (systemSettingsDialog.btnXfce4Terminal.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.XFCE4_TERMINAL, SystemRestrictionUtils.getSelectedValue(systemSettingsDialog.cmbXfce4Terminal));
		}
		if (systemSettingsDialog.btnXterm.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.XTERM_TERMINAL, SystemRestrictionUtils.getSelectedValue(systemSettingsDialog.cmbXterm));
		}
		if (systemSettingsDialog.btnUxterm.getSelection()) {
			profileData.put(SystemRestrictionConstants.PARAMETERS.UXTERM_TERMINAL, SystemRestrictionUtils.getSelectedValue(systemSettingsDialog.cmbUxterm));
		}
		
		return profileData;
	}
	
	@Override
	public void validateBeforeSave() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
}
