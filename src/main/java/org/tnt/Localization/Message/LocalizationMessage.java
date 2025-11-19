package org.tnt.Localization.Message;

import org.tnt.Localization.LocalizationManager;
import org.tnt.Localization.Message.Interfaces.Description;
import org.tnt.Localization.Message.Interfaces.Field;
import org.tnt.Localization.Message.Interfaces.Language;
import org.tnt.Localization.Message.Interfaces.Message;

public class LocalizationMessage extends LocalizationManager implements Language, Message, Description, Field {


    @Override
    public Message setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Description atMessage(String message) {

        return this;
    }

    @Override
    public Field atField() {

        return this;
    }

    @Override
    public String getTitle() {

        return getData();
    }

    @Override
    public String getDescription() {

        return getData();
    }
}
