function displayRss(url) {
    var jsUrl = "http://leeds.bates.edu/rss/rss2js.php?src="
    	+ escape(url.replace(/&amp;/g, "&"))
    	+ "&chan=yes&num=10&desc=yes&date=yes";
    document.writeln('<script language="javascript" src="' + jsUrl + '"></script>');
}