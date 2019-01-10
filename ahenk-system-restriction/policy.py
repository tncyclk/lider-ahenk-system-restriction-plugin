#!/usr/bin/python3
# -*- coding: utf-8 -*-
# Author: Tuncay ÇOLAK <tuncay.colak@tubitak.gov.tr>

from base.plugin.abstract_plugin import AbstractPlugin
import json

class Sample(AbstractPlugin):
    def __init__(self, profile_data, context):
        super(Sample, self).__init__()
        self.profile_data = profile_data
        self.context = context
        self.message_code = self.get_message_code()
        self.logger = self.get_logger()
        self.parameters = json.loads(self.profile_data)
        self.username = self.context.get('username')
        self.default_action_pref = "lider.ahenk.pkexec.{command}.pkla"
        self.polkit_pkla_folder_path = "/etc/polkit-1/localauthority/99-ahenk.d"
        self.apparmor_policy_path = "/etc/apparmor.d/{command_path}"

        if not self.is_exist(self.polkit_pkla_folder_path):
            self.create_directory(self.polkit_pkla_folder_path)

    def handle_policy(self):
        # TODO Do what do you want to do!
        # TODO Don't Forget returning response with <self.context.create_response(..)>
        try:
            self.logger.debug('Permissions will be applied for profile')

            # DESKTOP SETTINGS
            if self.has_attr_json(self.parameters, 'hibernate') is True:
                hibernate = self.parameters['hibernate']
                self.hibernate_settings(hibernate)
            else:
                self.logger.debug('Data has no parameter "hibernate"')

            if self.has_attr_json(self.parameters, 'desktop_settings') is True:
                desktop_settings = self.parameters['desktop_settings']
                action_id = self.default_action_pref.format(command="desktop_settings")
                self.desktop_settings(desktop_settings, action_id)
            else:
                self.logger.debug('Data has no parameter "desktop_settings"')

            if self.has_attr_json(self.parameters, 'app_right_click') is True:
                app_right_click = self.parameters['app_right_click']
                action_id = self.default_action_pref.format(command="app_right_click")
                self.desktop_settings(app_right_click, action_id)
            else:
                self.logger.debug('Data has no parameter "app_right_click"')

            # PANEL PREFERENCE
            if self.has_attr_json(self.parameters, 'panel_preferences') is True:
                panel_preferences = self.parameters['panel_preferences']
                action_id = self.default_action_pref.format(command="panel_preferences")
                self.panel_preferences(panel_preferences, action_id)
            else:
                self.logger.debug('Data has no parameter "panel_preferences"')

            # SYSTEM SETTINGS
            if self.has_attr_json(self.parameters, 'xterm_terminal') is True:
                rest_status = self.parameters['xterm_terminal']
                self.terminal_settings("xterm", rest_status)
            else:
                self.logger.debug('Data has no parameter "xterm_terminal"')

            if self.has_attr_json(self.parameters, 'xfce4_terminal') is True:
                rest_status = self.parameters['xfce4_terminal']
                self.terminal_settings("xfce4-terminal", rest_status)
            else:
                self.logger.debug('Data has no parameter "xfce4_terminal"')

            if self.has_attr_json(self.parameters, 'uxterm_terminal') is True:
                rest_status = self.parameters['uxterm_terminal']
                self.terminal_settings("uxterm", rest_status)
            else:
                self.logger.debug('Data has no parameter "uxterm_terminal"')

            self.logger.info('System restriction profile is handled successfully.')
            self.context.create_response(code=self.message_code.POLICY_PROCESSED.value,
                                         message='Sistem kısıtlamaları politikası başarıyla güncellendi.')

        except Exception as e:
            self.logger.error('A problem occurred while handling USB policy. Error Message: {0}'.format(str(e)))
            self.context.create_response(code=self.message_code.POLICY_ERROR.value,
                                         message='Sistem kısıtlamaları politikası uygulanırken bir hata oluştu: {0}'.format(str(e)))

    def hibernate_settings(self, hibernate_data):

        temp_hibernate = "[Re-enable hibernate by default for login1]\n" \
                         "  Identity=unix-user:{1}\n" \
                         "  Action=org.freedesktop.login1.hibernate\n" \
                         "  ResultActive={0}\n" \
                         "[Re-enable hibernate for multiple users by default in logind]\n" \
                         "  Identity=unix-user:{1}\n" \
                         "  Action=org.freedesktop.login1.hibernate-multiple-sessions\n" \
                         "  ResultActive={0}"

        # close hibernate if data is active
        privilege = None
        if hibernate_data == "1":
            privilege = "yes"
        # open hibernate if data is active
        elif hibernate_data == "0":
            privilege = "no"
        hibernate_settings_path = self.polkit_pkla_folder_path+"/"+self.default_action_pref.format(command="hibernate")
        if not self.is_exist(hibernate_settings_path):
            self.logger.info("lider-ahenk.enable-hibernate.pkla does not exist.")
            self.create_file(hibernate_settings_path)
            self.write_file(hibernate_settings_path, temp_hibernate.format(privilege, self.username))
            self.logger.info("Create file and write hibernate data to {0} file ".format(hibernate_settings_path))
            self.logger.info("Enabled hibernate settings successfully")

        else:
            self.logger.info("{} file already exists".format(hibernate_settings_path))

    # restrictions with Apparmor

    def terminal_settings(self, command, rest_status):
        command_path = self.get_command_path(command)
        command_path = command_path.replace('/', '.').lstrip('.')
        if not self.is_exist(self.apparmor_policy_path.format(command_path=command_path)):
            self.create_file(self.apparmor_policy_path.format(command_path=command_path))
            self.logger.info("Created file {} ".format(self.apparmor_policy_path.format(command_path=command_path)))
        else:
            self.logger.info("{} file already exists".format(self.apparmor_policy_path.format(command_path=command_path)))

        #apparmor enforce mode
        if rest_status == "0":
            self.execute("aa-enforce {command}".format(command=command))
            self.logger.info(command + " is restriction successfully")
        # apparmor complain mode
        else:
            self.execute("aa-complain {command}".format(command=command))
            self.logger.info("{} restriction removed".format(command))

    def desktop_settings(self, desktop_settings, action_id):
        pass

    def panel_preferences(self, panel_preferences, action_id):
        pass

def handle_policy(profile_data, context):
    print('Sample Plugin Policy')
    sample = Sample(profile_data, context)
    sample.handle_policy()
