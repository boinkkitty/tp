---
layout: page
title: Gaanesh's Project Portfolio Page
---

### Project: TutorSynch

Given below are my contributions to the project.

* **New Feature**: Added the `sort` command. [\#97](https://github.com/AY2425S2-CS2103-F15-2/tp/pull/97)
    * **What it does**: Allows users to sort the existing list of students by name in alphabetical order without affecting newly added students (which appear at the bottom until another `sort` is performed).
    * **Highlights**:
        - Involves a new command that interacts with the `Model` to reorder the internal list of `Person` objects.
        - Required additional parser logic (a `SortCommandParser` that rejects extra arguments).
        - Needed system tests to ensure correct behavior even when the address book is empty or partially sorted.

* **New Feature**: Added the ability to append tags. [\#79](https://github.com/AY2425S2-CS2103-F15-2/tp/pull/79)
    * **What it does**: Introduces a new `t+/` prefix in `edit` commands that appends tags instead of overwriting them.
    * **Highlights**:
        - Works in tandem with existing `t/` (overwrite) and `t-/` (remove) prefixes.
        - Required changes in `EditPersonDescriptor` and `EditCommandParser` to handle multiple tag operations at once.
        - Wrote tests to confirm that overwriting, removing, and appending tags can coexist without conflicts.

* **Documentation**:
    * **User Guide**:
        - Documented the usage of `sort` with examples. [\#99](https://github.com/AY2425S2-CS2103-F15-2/tp/pull/99)
        - Explained new `t+/` prefix under the `edit` command, including sample use cases. [\#79](https://github.com/AY2425S2-CS2103-F15-2/tp/pull/79)
    * **Developer Guide**:
        - Added an implementation overview of `SortCommand`
        - Included a UML sequence diagram in the Developer Guide. [\#153](https://github.com/AY2425S2-CS2103-F15-2/tp/pull/153)

* **Testing**:
    * Performed smoke testing on v1.3 and v1.4 releases. [\#100](https://github.com/AY2425S2-CS2103-F15-2/tp/issues/100)


<!--
### Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
-->
