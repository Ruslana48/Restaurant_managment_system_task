package filters;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Locale;

public enum SupportedLocaleStorage {
    ENG(Locale.US),
    UA(new Locale("ua", "UA"));

    private static final org.apache.log4j.Logger log = Logger.getLogger(SupportedLocaleStorage.class);

    private final Locale locale;

    SupportedLocaleStorage(Locale locale) {
        this.locale = locale;
    }

    public static SupportedLocaleStorage getLocaleFromLanguage(String inputLanguage) {
        for (SupportedLocaleStorage currentLocale: SupportedLocaleStorage.values()) {
            if (currentLocale.locale.getLanguage().equals(inputLanguage)) {
                return currentLocale;
            }
        }
        log.warn(String.format("locale %s is not found, default locale is %s", inputLanguage, ENG.locale.toString()));
        return ENG;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public Locale getLocale() {
        return locale;
    }
}
