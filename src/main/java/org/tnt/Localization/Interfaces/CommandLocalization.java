package org.tnt.Localization.Interfaces;

public interface CommandLocalization {
    DescriptionLocalization atCommand(String description);
    ChoiceLocalization atChoice(String choice);
}
