package kz.mediation.api.mediation_executor.util.enums;

import lombok.Getter;

@Getter
public enum LanguageEnum {
    //Language codes (ISO 639-1 format)
    AFRIKAANS("af", "Afrikaans"),
    ALBANIAN("sq", "Shqip"),
    AMHARIC("am", "አማርኛ"),
    ARABIC("ar", "العربية"),
    ARMENIAN("hy", "Հայերեն"),
    AZERBAIJANI("az", "Azərbaycan"),
    BASQUE("eu", "Euskara"),
    BELARUSIAN("be", "Беларуская"),
    BENGALI("bn", "বাংলা"),
    BOSNIAN("bs", "Bosanski"),
    BULGARIAN("bg", "Български"),
    CATALAN("ca", "Català"),
    CEBUANO("ceb", "Cebuano"),
    CHINESE_SIMPLIFIED("zh-CN", "简体中文"),
    CHINESE_TRADITIONAL("zh-TW", "繁體中文"),
    CORSICAN("co", "Corsu"),
    CROATIAN("hr", "Hrvatski"),
    CZECH("cs", "Čeština"),
    DANISH("da", "Dansk"),
    DUTCH("nl", "Nederlands"),
    ENGLISH("en", "English"),
    ESPERANTO("eo", "Esperanto"),
    ESTONIAN("et", "Eesti"),
    FILIPINO("fil", "Filipino"),
    FINNISH("fi", "Suomi"),
    FRENCH("fr", "Français"),
    FRISIAN("fy", "Frysk"),
    GALICIAN("gl", "Galego"),
    GEORGIAN("ka", "ქართული"),
    GERMAN("de", "Deutsch"),
    GREEK("el", "Ελληνικά"),
    GUJARATI("gu", "ગુજરાતી"),
    HAITIAN_CREOLE("ht", "Kreyòl Ayisyen"),
    HAUSA("ha", "Hausa"),
    HAWAIIAN("haw", "ʻŌlelo Hawaiʻi"),
    HEBREW("he", "עברית"),
    HINDI("hi", "हिन्दी"),
    HMONG("hmn", "Hmong"),
    HUNGARIAN("hu", "Magyar"),
    ICELANDIC("is", "Íslenska"),
    IGBO("ig", "Igbo"),
    INDONESIAN("id", "Bahasa Indonesia"),
    IRISH("ga", "Gaeilge"),
    ITALIAN("it", "Italiano"),
    JAPANESE("ja", "日本語"),
    JAVANESE("jv", "Basa Jawa"),
    KANNADA("kn", "ಕನ್ನಡ"),
    KAZAKH("kk", "Қазақ"),
    KHMER("km", "ខ្មែរ"),
    KOREAN("ko", "한국어"),
    KURDISH("ku", "Kurdî"),
    KYRGYZ("ky", "Кыргызча"),
    LAO("lo", "ລາວ"),
    LATIN("la", "Latina"),
    LATVIAN("lv", "Latviešu"),
    LITHUANIAN("lt", "Lietuvių"),
    LUXEMBOURGISH("lb", "Lëtzebuergesch"),
    MACEDONIAN("mk", "Македонски"),
    MALAGASY("mg", "Malagasy"),
    MALAY("ms", "Bahasa Melayu"),
    MALAYALAM("ml", "മലയാളം"),
    MALTESE("mt", "Malti"),
    MAORI("mi", "Māori"),
    MARATHI("mr", "मराठी"),
    MONGOLIAN("mn", "Монгол"),
    MYANMAR("my", "မြန်မာ"),
    NEPALI("ne", "नेपाली"),
    NORWEGIAN("no", "Norsk"),
    NYANJA("ny", "Chichewa"),
    ODIA("or", "ଓଡ଼ିଆ"),
    PASHTO("ps", "پښتو"),
    PERSIAN("fa", "فارسی"),
    POLISH("pl", "Polski"),
    PORTUGUESE("pt", "Português"),
    PUNJABI("pa", "ਪੰਜਾਬੀ"),
    ROMANIAN("ro", "Română"),
    RUSSIAN("ru", "Русский"),
    SAMOAN("sm", "Gagana Samoa"),
    SCOTS_GAELIC("gd", "Gàidhlig"),
    SERBIAN("sr", "Српски"),
    SESOTHO("st", "Sesotho"),
    SHONA("sn", "Shona"),
    SINDHI("sd", "سنڌي"),
    SINHALA("si", "සිංහල"),
    SLOVAK("sk", "Slovenčina"),
    SLOVENIAN("sl", "Slovenščina"),
    SOMALI("so", "Soomaali"),
    SPANISH("es", "Español"),
    SUNDANESE("su", "Basa Sunda"),
    SWAHILI("sw", "Kiswahili"),
    SWEDISH("sv", "Svenska"),
    TAGALOG("tl", "Tagalog"),
    TAJIK("tg", "Тоҷикӣ"),
    TAMIL("ta", "தமிழ்"),
    TATAR("tt", "Татар"),
    TELUGU("te", "తెలుగు"),
    THAI("th", "ไทย"),
    TURKISH("tr", "Türkçe"),
    TURKMEN("tk", "Türkmen"),
    UKRAINIAN("uk", "Українська"),
    URDU("ur", "اردو"),
    UYGHUR("ug", "ئۇيغۇرچە"),
    UZBEK("uz", "O'zbek"),
    VIETNAMESE("vi", "Tiếng Việt"),
    WELSH("cy", "Cymraeg"),
    XHOSA("xh", "isiXhosa"),
    YIDDISH("yi", "ייִדיש"),
    YORUBA("yo", "Yorùbá"),
    ZULU("zu", "isiZulu");

    private final String code;
    private final String nativeName;

    LanguageEnum(String code, String nativeName) {
        this.code = code;
        this.nativeName = nativeName;
    }

    /**
     * Find a Language enum by its language code
     *
     * @param code the ISO language code to search for
     * @return the corresponding Language enum value
     * @throws IllegalArgumentException if no matching language is found
     */
    public static LanguageEnum findByCode(String code) {
        for (LanguageEnum language : values()) {
            if (language.getCode().equalsIgnoreCase(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unsupported language code: " + code);
    }

    /**
     * Check if a language code is supported
     *
     * @param code the ISO language code to check
     * @return true if the language code is supported, false otherwise
     */
    public static boolean isSupported(String code) {
        for (LanguageEnum language : values()) {
            if (language.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.nativeName, this.code);
    }
}

//
//// Get language code
//String russianCode = Language.RUSSIAN.getCode();  // "ru"
//
//// Get native name
//String chineseName = Language.CHINESE_SIMPLIFIED.getNativeName();  // "简体中文"
//
//// Find language by code
//Language spanish = Language.findByCode("es");  // Language.SPANISH
//
//// Check if language is supported
//boolean isSupported = Language.isSupported("fr");  // true
//
//// Get formatted string representation
//String formatted = Language.JAPANESE.toString();  // "日本語 (ja)"
