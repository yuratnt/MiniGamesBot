package org.tnt.Localization.Commands.Interfaces;

public interface Subcommand {
    String getDescription();
    Option atOption(String choice);
}
