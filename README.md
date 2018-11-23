# lider-ahenk-system-restriction-plugin

system-restriction plugin for [Lider Ahenk project](http://www.liderahenk.org.tr).

|                   | **user-oriented** | **machine-oriented** |
|:-----------------:|:-----------------:|:--------------------:|
| **task plugin**   |        x          |           x          |
| **policy plugin** |        x          |           x          |

## How to Build

> We use Eclipse for Lider and Lider Console related development. See these documentation [here](https://github.com/Pardus-Kurumsal/lider-console/wiki/01.-Setup-Development-Environment) and [here](https://github.com/Pardus-Kurumsal/lider/wiki/01.-Setup-Development-Environment) to setup Eclipse IDE with Lider and Lider Console projects imported.

1. Clone the plugin project by running `git clone https://github.com/Pardus-Kurumsal/lider-ahenk-system-restriction-plugin.git`.
2. Open Eclipse and import the plugin project into Eclipse as 'Existing Maven Projects'.
3. Navigate to _lider-ahenk-system-restriction-plugin/scripts_ directory and run `build-plugin.sh`.

## How to Run

> Make sure you have Lider, Lider Console and Ahenk running. See these documentation:  [\[1\]](https://github.com/Pardus-Kurumsal/lider/wiki/02.-Building-&-Running), [\[2\]](https://github.com/Pardus-Kurumsal/lider-console/wiki/02.-Building-&-Running) and [\[3\]](https://github.com/Pardus-Kurumsal/ahenk/wiki/02.-Running).

#### Lider

1. Type `feature:repo-add  mvn:tr.org.liderahenk/lider-system-restriction-feature/1.0.0/xml/features` on Karaf shell. This will add plugin repository to the Karaf instance.
2. Again on Karaf shell, run `feature:install lider-system-restriction` to install and run plugin bundles.
3. Use `log:tail` and `plugin:list` commands to ensure the plugin is installed and working properly.

#### Lider Console

1. Open Eclipse, go to 'Run --> Debug Configurations' menu and on 'Plugins' tab, select _lider-console-system-restriction_
2. Click 'Add Required Plugins' button to add any plugins the project depend on.
3. Finally you can run Lider Console as explained in its [documentation](https://github.com/Pardus-Kurumsal/lider-console/wiki/02.-Building-&-Running).

#### Ahenk

1. Create a soft link for _ahenk-system-restriction/system-restriction_ directory via `sudo ln -s lider-ahenk-system-restriction-plugin/ahenk-system-restriction/system-restriction/ /opt/ahenk/plugins`

## Contribution

We encourage contributions to the project. To contribute:

* Fork the project and create a new bug or feature branch.
* Make your commits with clean, understandable comments
* Perform a pull request

## Other Lider Ahenk Projects

* [Lider Console](https://github.com/Pardus-Kurumsal/lider-console): Administration console built as Eclipse RCP project.
* [Ahenk](https://github.com/Pardus-Kurumsal/ahenk): Agent service running on remote machines.
* [Lider Ahenk Installer](https://github.com/Pardus-Kurumsal/lider-ahenk-installer): Installation wizard for Ahenk and Lider (and also its LDAP, database, XMPP servers).
* [Lider Ahenk Archetype](https://github.com/Pardus-Kurumsal/lider-ahenk-archetype): Maven archetype for easy plugin development.

## License

Lider Ahenk and its sub projects are licensed under the [LGPL v3](https://github.com/Pardus-Kurumsal/lider/blob/master/LICENSE).

# lider-ahenk-system-restriction-plugin
# lider-ahenk-system-restriction-plugin
# lider-ahenk-system-restriction-plugin
