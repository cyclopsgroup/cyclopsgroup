function rsstransform(rssHref, stylesheetHref) {
    try{
        var rssDocument = new ActiveXObject("MSXML2.DOMDocument");
        rssDocument.async=false;
        rssDocument.load(rssHref);
        
        var rssStylesheet = new ActiveXObject("MSXML2.DOMDocument");
        rssStylesheet.async=false;
        rssStylesheet.load(stylesheetHref);
        
        return rssDocument.transformNode(rssStylesheet);
    } catch (e) {
        document.write("<p>" + e + "</p>");
    }
}