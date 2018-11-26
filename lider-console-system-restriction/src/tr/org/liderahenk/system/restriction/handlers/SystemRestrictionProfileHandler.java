package tr.org.liderahenk.system.restriction.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.liderconsole.core.editorinput.ProfileEditorInput;
import tr.org.liderahenk.liderconsole.core.handlers.LiderAbstractHandler;
import tr.org.liderahenk.system.restriction.constants.SystemRestrictionConstants;
import tr.org.liderahenk.system.restriction.dialogs.SystemRestrictionProfileDialog;
import tr.org.liderahenk.system.restriction.i18n.Messages;

/**
* Profile definition handler for system-restriction plugin.
*
*/
public class SystemRestrictionProfileHandler extends LiderAbstractHandler {

    private Logger logger = LoggerFactory.getLogger(SystemRestrictionProfileHandler.class);
    @Override
    public ProfileEditorInput getEditorInput() {
        // TODO Auto-generated method stub
        return  new ProfileEditorInput(Messages.getString("SCRIPT"), SystemRestrictionConstants.PLUGIN_NAME,
                SystemRestrictionConstants.PLUGIN_VERSION, new SystemRestrictionProfileDialog());
    }

}