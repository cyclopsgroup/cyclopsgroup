function languageLink(link, lang, title, base) {
    if(lang == "en"){
        document.write('<a href="'+link+'">');
    } else {
        document.write('<a href="http://babelfish.altavista.com/babelfish/urltrurl?url='+link+'&lp=en_' + lang + '">');
    }
    document.write('<img src="'+base+'/images/countryflags/'+lang+'.gif" width="27" height="18" border="0" alt="'+title+'">');
    document.write("</a>");
}