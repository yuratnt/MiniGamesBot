package org.tnt.Localization.Interfaces;

public interface DescriptionLocalization {
    String getDescription();
    SubcommandLocalization atSubcommand(String subcommand);
    OptionLocalization atOption(String subcommand);
}
