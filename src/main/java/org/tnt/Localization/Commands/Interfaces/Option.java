package org.tnt.Localization.Commands.Interfaces;

public interface Option {
    String getDescription();
    Choice atChoice(String choice);
}
