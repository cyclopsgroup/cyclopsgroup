function transformXml(xml, xsl){
    var t = "http://crazytmac.s42.eatj.com/laputa/xslt.js?";
    t += "xml=" + xml;
    t += "&xsl=" + xsl;
    document.writeln('<script language="javascript" type="text/javascript" src="' + t + '"></script>');
}