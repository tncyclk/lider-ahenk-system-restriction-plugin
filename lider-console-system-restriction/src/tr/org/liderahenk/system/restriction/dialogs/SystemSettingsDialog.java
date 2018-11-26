package tr.org.liderahenk.system.restriction.dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import tr.org.liderahenk.liderconsole.core.model.Profile;
import tr.org.liderahenk.system.restriction.constants.SystemRestrictionConstants;
import tr.org.liderahenk.system.restriction.i18n.Messages;

/**
 * 
 * @author <a href="mailto:tuncay.colak@tubitak.gov.tr">Tuncay ÇOLAK</a>
 *
 */

public class SystemSettingsDialog extends TitleAreaDialog {
	private TabFolder tabFolder;
	
	private final String[] statusArr = new String[] { "ENABLE", "DISABLE" };
	private final String[] statusValueArr = new String[] { "1", "0" };

	//	button definitions
	public Button btnXfce4Terminal;
	public Button btnXterm;
	public Button btnUxterm;
	
	//	combo box definitions
	public Combo cmbXfce4Terminal;
	public Combo cmbXterm;
	public Combo cmbUxterm;


	public SystemSettingsDialog(Shell parentShell, TabFolder tabFolder) {
		super(parentShell);
		this.tabFolder = tabFolder;
		
		// TODO Auto-generated constructor stub
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
	
	public void createSystemSettingsTab(Profile profile) {
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(Messages.getString("SYSTEM_SETTINGS"));
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
//		XFCE4-TeRMINAL settings
		btnXfce4Terminal = new Button(group, SWT.CHECK);
		btnXfce4Terminal.setText(Messages.getString("XFCE4_TERMINAL"));
		btnXfce4Terminal.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbXfce4Terminal.setEnabled(btnXfce4Terminal.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbXfce4Terminal = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbXfce4Terminal.setSize(10, 10 );
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbXfce4Terminal.add(i18n);
				cmbXfce4Terminal.setData(i + "", statusValueArr[i]);
			}
		}
		boolean isSelected_xfce4 = selectOption(cmbXfce4Terminal, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.XFCE4_TERMINAL) : null);
		cmbXfce4Terminal.setEnabled(isSelected_xfce4);
		btnXfce4Terminal.setSelection(isSelected_xfce4);
		
//		XTerm Settings
		btnXterm = new Button(group, SWT.CHECK);
		btnXterm.setText(Messages.getString("XTERM"));
		btnXterm.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbXterm.setEnabled(btnXterm.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbXterm = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbXterm.setSize(10, 10 );
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbXterm.add(i18n);
				cmbXterm.setData(i + "", statusValueArr[i]);
			}
		}
		boolean isSelected_xterm = selectOption(cmbXterm, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.XTERM_TERMINAL) : null);
		cmbXterm.setEnabled(isSelected_xterm);
		btnXterm.setSelection(isSelected_xterm);
		
//		UXTerm Settings
		btnUxterm = new Button(group, SWT.CHECK);
		btnUxterm.setText(Messages.getString("UXTERM"));
		btnUxterm.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cmbUxterm.setEnabled(btnUxterm.getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmbUxterm = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		cmbUxterm.setSize(10, 10 );
		for (int i = 0; i < statusArr.length; i++) {
			String i18n = Messages.getString(statusArr[i]);
			if (i18n != null && !i18n.isEmpty()) {
				cmbUxterm.add(i18n);
				cmbUxterm.setData(i + "", statusValueArr[i]);
			}
		}
		boolean isSelected_uxterm = selectOption(cmbUxterm, profile != null && profile.getProfileData() != null
				? profile.getProfileData().get(SystemRestrictionConstants.PARAMETERS.UXTERM_TERMINAL) : null);
		cmbUxterm.setEnabled(isSelected_uxterm);
		btnUxterm.setSelection(isSelected_uxterm);
		
		tabItem.setControl(group);
}
}
