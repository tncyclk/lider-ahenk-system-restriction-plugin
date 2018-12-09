#!/usr/bin/python3
# -*- coding: utf-8 -*-
# Author: Tuncay ÇOLAK <tuncay.colak@tubitak.gov.tr>

from base.plugin.abstract_plugin import AbstractPlugin


class Safe(AbstractPlugin):
    def __init__(self, context):
        super(Safe, self).__init__()
        self.context = context
        self.username = str(context.get_username())
        self.logger = self.get_logger()
        self.polkit_pkla_folder_path = "/etc/polkit-1/localauthority/99-ahenk.d"

    def handle_safe_mode(self):
        # TODO Do what do you want to do!
        hibernate_settings_path = "/etc/polkit-1/localauthority/50-local.d/lider.ahenk.enable-hibernate.pkla"
        if self.is_exist(self.polkit_pkla_folder_path):
            self.delete_folder(self.polkit_pkla_folder_path)
            self.logger.info("System restrictions returned to default settings")
        else:
            self.logger.info("{} File not found".format(self.polkit_pkla_folder_path))

def handle_mode(context):
    safe = Safe(context)
    safe.handle_safe_mode()
