function tableStart() {
    document.write('<table border="0">');
}
function tableEnd() {
    document.write('</table>');
}
function lineStart() {
    document.write('<tr><td>');
}
function lineEnd() {
    document.write('</td></tr>');
}
function languageLink(href, img, title) {
    document.write('<a href="' + href  + '"><img src="' + img + '" alt="' + title + '" border="0" width="36" height="21"/></a>');
}