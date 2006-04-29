function displayRss(url) {
    var jsUrl = "http://jade.mcli.dist.maricopa.edu/feed/feed2js.php?src="
    	+ escape(url.replace(/&amp;/g, "&"))
    	+ "&date=y&utf=y&html=a";
    document.writeln('<script language="javascript" src="' + jsUrl + '"></script>');
}