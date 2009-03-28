/** A single link to translated page provided by babelfish.altavista.com
 * @param link full url to be translated
 * @param lang Language abbreviation
 * @param title Title of image
 * @base base url
 */
function languageLink(link, lang, title, base) {
    if(lang == "en"){
        document.write('<a href="'+link+'" title="' + title + '">');
    } else {
        document.write('<a href="http://babelfish.altavista.com/babelfish/urltrurl?url='+link+'&lp=en_' + lang + '" title="' + title + '">');
    }
    document.write('<img src="'+base+'/images/countryflags/'+lang+'.gif" width="27" height="18" border="0" alt="'+title+'">');
    document.write("</a>");
}

/** Show translation links
 * @url Full url to be translated
 * @base path to the base
 */
function languageLinks(url, base) {
    languageLink(url, "nl", "Translate this page into Dutch", base);
    languageLink(url, "de", "Translate this page into German", base);
    languageLink(url, "el", "Translate this page into Greek", base);
    languageLink(url, "es", "Translate this page into Spanish", base);
    languageLink(url, "fr", "Translate this page into French", base);
    languageLink(url, "it", "Translate this page into Italian", base);
    languageLink(url, "ja", "Translate this page into Japanese", base);
    languageLink(url, "ko", "Translate this page into Korean", base);
    languageLink(url, "pt", "Translate this page into Portuguese", base);
    languageLink(url, "ru", "Translate this page into Russian", base);
    languageLink(url, "zh", "Translate this page into Simplified Chinese", base);
    languageLink(url, "zt", "Translate this page into Traditional Chinese", base);
    
    languageLink(url, "en", "Back to English version", base);
}