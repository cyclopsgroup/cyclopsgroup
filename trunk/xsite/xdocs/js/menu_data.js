fixMozillaZIndex=true; //Fixes Z-Index problem  with Mozilla browsers but causes odd scrolling problem, toggle to see if it helps
_menuCloseDelay=500;
_menuOpenDelay=150;
_subOffsetTop=2;
_subOffsetLeft=-2;




with(XPMainStyle=new mm_style()){
styleid=1;
bordercolor="#8A867A";
borderstyle="solid";
borderwidth=1;
fontfamily="Tahoma,Helvetica,Verdana";
fontsize="70%";
fontstyle="normal";
fontweight="normal";
offbgcolor="#ECE9D8";
offcolor="#000000";
onbgcolor="#C1D2EE";
onborder="1px solid #316AC5";
oncolor="#000000";
padding=3;
rawcss="padding-left:5px;padding-right:5px";
}

with(XPMenuStyle=new mm_style()){
bordercolor="#8A867A";
borderstyle="solid";
borderwidth=1;
fontfamily="Tahoma,Helvetica,Verdana";
fontsize="70%";
fontstyle="normal";
fontweight="normal";
image="xpblank.gif";
imagepadding=3;
menubgimage="winxp.gif";
offbgcolor="transparent";
offcolor="#000000";
onbgcolor="#C1D2EE";
onborder="1px solid #316AC5";
oncolor="#000000";
outfilter="randomdissolve(duration=0.3)";
overfilter="Fade(duration=0.2);Alpha(opacity=90);Shadow(color=#999999, Direction=135, Strength=5)";
padding=4;
separatoralign="right";
separatorcolor="#C5C2B8";
separatorpadding=1;
separatorwidth="80%";
subimage="arrow.gif";
subimagepadding=3;
menubgcolor="#ffffff";
}

with(milonic=new menuname("Main Menu")){
alwaysvisible=1;
//left=10;
margin=2;
orientation="horizontal";
style=XPMainStyle;
//top=10;
aI("status=Back To Cyclops Group home page;text=Home;url=http://www.cyclopsgroup.com;");
aI("showmenu=Samples;text=Menu Samples;");
aI("showmenu=Milonic;text=Milonic;");
aI("showmenu=Partners;text=Partners;");
aI("showmenu=Links;text=Links;");
aI("showmenu=My Milonic;text=My Milonic;");
}

with(milonic=new menuname("Samples")){
margin=2;
overflow="scroll";
style=XPMenuStyle;
aI("text=Plain Text Horizontal Style DHTML Menu Bar;url=http://www.milonic.com/menusample1.php;")
aI("text=Vertical Plain Text Menu;url=http://www.milonic.com/menusample2.php;")
aI("text=All Horizontal Menus;url=http://www.milonic.com/menusample25.php;")
aI("text=Using The Popup Menu Function Positioned by Images;url=http://www.milonic.com/menusample24.php;")
aI("text=Classic XP Style Menu;url=http://www.milonic.com/menusample82.php;")
aI("text=XP Style;url=http://www.milonic.com/menusample86.php;")
aI("text=XP Taskbar Style Menu;url=http://www.milonic.com/menusample83.php;")
aI("text=Office XP Style Menu;url=http://www.milonic.com/menusample47.php;")
aI("text=Office 2003 Style Menu;url=http://www.milonic.com/menusample46.php;")
aI("text=Apple Mac Style Menu;url=http://www.milonic.com/menusample72.php;")
aI("text=Amazon Style Tab Menu;url=http://www.milonic.com/menusample74.php;")
aI("text=Milonic Home Menu;url=http://www.milonic.com/menusample78.php;")
aI("text=Windows 98 Style Menu;url=http://www.milonic.com/menusample13.php;")
aI("text=Multiple Styles Menu;url=http://www.milonic.com/menusample5.php;")
aI("text=Multi Colored Menu Items;url=http://www.milonic.com/menusample80.php;")
aI("text=Multi Colored Menus Using Styles;url=http://www.milonic.com/menusample7.php;")
aI("text=Multi Tab;url=http://www.milonic.com/menusample50.php;")
aI("text=Tab Top;url=http://www.milonic.com/menusample52.php;")
aI("text=Multi Columns;url=http://www.milonic.com/menusample73.php;")
aI("text=100% Width Span Menu;url=http://www.milonic.com/menusample26.php;")
aI("text=Follow Scrolling Style Menu;url=http://www.milonic.com/menusample10.php;")
aI("text=Menu Positioning With Offsets;url=http://www.milonic.com/menusample23.php;")
aI("text=Table Based (Relative) Menus;url=http://www.milonic.com/menusample9.php;")
aI("text=Opening Windows and Frames with the Menu;url=http://www.milonic.com/menusample11.php;")
aI("text=Menus Over Form Selects, Flash and Java Applets;url=http://www.milonic.com/menusample14.php;")
aI("text=Activating Functions on Mouseover;url=http://www.milonic.com/menusample15.php;")
aI("text=Drag Drop Menus;url=http://www.milonic.com/menusample22.php;")
aI("text=Menus with Header Type Items;url=http://www.milonic.com/menusample8.php;")
aI("text=Right To Left Openstyle;url=http://www.milonic.com/menusample65.php;")
aI("text=Up Openstyle Featuring Headers;url=http://www.milonic.com/menusample33.php;")
aI("text=DHTML Menus and Tooltips;url=http://www.milonic.com/menusample6.php;")
aI("text=Unlimited Level Menus Example;url=http://www.milonic.com/menusample67.php;")
aI("text=Context Right Click Menu;url=http://www.milonic.com/menusample27.php;")
aI("text=Menus built entirely from images;url=http://www.milonic.com/menusample18.php;")
aI("text=Static Images Sample;url=http://www.milonic.com/menusample16.php;")
aI("text=Rollover Swap Images Sample;url=http://www.milonic.com/menusample17.php;")
aI("text=Background Item Images;url=http://www.milonic.com/menusample20.php;")
aI("text=Background Image Buttons;url=http://www.milonic.com/menusample89.php;")
aI("text=Menu Background Images;url=http://www.milonic.com/menusample76.php;")
aI("text=Creating Texture with Menu Background Images;url=http://www.milonic.com/menusample19.php;")
aI("text=Static Background Item Images;url=http://www.milonic.com/menusample71.php;")
aI("text=Vertical Static Background Item Images;url=http://www.milonic.com/menusample87.php;")
aI("text=World Map Sample;url=http://www.milonic.com/menusample92.php;")
aI("text=US Map Sample;url=http://www.milonic.com/menusample91.php;")
aI("text=Image Map Sample;url=http://www.milonic.com/menusample4.php;")
aI("text=Rounded Edges Imperial Style;url=http://www.milonic.com/menusample21.php;")
aI("text=Corporation;url=http://www.milonic.com/menusample40.php;")
aI("text=International;url=http://www.milonic.com/menusample70.php;")
aI("text=Clean;url=http://www.milonic.com/menusample32.php;")
aI("text=3D Gradient Block;url=http://www.milonic.com/menusample57.php;")
aI("text=Descreet;url=http://www.milonic.com/menusample42.php;")
aI("text=Agriculture;url=http://www.milonic.com/menusample41.php;")
aI("text=Breeze;url=http://www.milonic.com/menusample29.php;")
aI("text=Chart;url=http://www.milonic.com/menusample66.php;")
aI("text=Cartoon;url=http://www.milonic.com/menusample77.php;")
aI("text=Start Button Menu;url=http://www.milonic.com/menusample69.php;")
aI("text=Tramline;url=http://www.milonic.com/menusample54.php;")

}

with(milonic=new menuname("Milonic")){
margin=2;
style=XPMenuStyle;
aI("image=xp_preview.gif;separatorsize=1;text=Product Purchasing Page;url=http://www.milonic.com/cbuy.php;");
aI("text=Contact Us;url=http://www.milonic.com/contactus.php;");
aI("image=xp_settings.gif;text=Newsletter Subscription;url=http://www.milonic.com/newsletter.php;");
aI("image=xp_question.gif;text=FAQ;url=http://www.milonic.com/menufaq.php;");
aI("text=Discussion Forum;url=http://www.milonic.com/forum/;");
aI("image=xp_width.gif;text=Software License Agreement;url=http://www.milonic.com/license.php;");
aI("image=xp_search.gif;text=Privacy Policy;url=http://www.milonic.com/privacy.php;");
}

with(milonic=new menuname("Partners")){
margin=2;
style=XPMenuStyle;
aI("status=(aq) Web Server Hosting & Services;text=(aq) Web Hosting;url=http://www.a-q.co.uk/;");
aI("text=SMS 2 Email;url=http://www.sms2email.com/;");
aI("text=WebSmith;url=http://www.softidiom.com/;");
}

with(milonic=new menuname("Links")){
margin=2;
style=XPMenuStyle;
aI("status=Apache Web Server, the basis of Milonic's Web Site;text=Apache Web Server;url=http://www.apache.org/;");
aI("status=MySQL, Milonic's Prefered Choice of Database Server;text=MySQL Database Server;url=http://ww.mysql.com/;");
aI("status=PHP - Web Server Scripting as used by Milonic;text=PHP - Development;url=http://www.php.net/;");
aI("status=PHP Based Web Forum, Milonic's Recommended Forum Software;text=phpBB Web Forum System;url=http://www.phpbb.net/;");
aI("showmenu=Anti Spam;status=Anti Spam Solutions, as used by Milonic;text=Anti Spam Tools;");
}

with(milonic=new menuname("Anti Spam")){
margin=2;
style=XPMenuStyle;
aI("text=Spam Cop;url=http://www.spamcop.net/;");
aI("text=Mime Defang;url=http://www.mimedefang.org/;");
aI("text=Spam Assassin;url=http://www.spamassassin.org/;");
}

drawMenus();

