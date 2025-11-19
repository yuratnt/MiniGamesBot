package org.tnt.Localization.Commands.Interfaces;

public interface Command {
    Description atCommand(String command);
    Description atSubcommand(String subcommand);
    Option atOption(String option);
    Choice atChoice(String choice);
    String getDescription();
}
