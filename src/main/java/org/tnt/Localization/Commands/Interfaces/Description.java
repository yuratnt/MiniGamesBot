package org.tnt.Localization.Commands.Interfaces;

public interface Description {
    String getDescription();
    Description atSubcommand(String subcommand);
    Option atOption(String subcommand);
}
