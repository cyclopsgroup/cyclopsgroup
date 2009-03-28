<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title/>
            </head>
            <body>
                <xsl:apply-templates select="rss"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="rss">
        <xsl:apply-templates select="channel"/>
    </xsl:template>
    <xsl:template match="channel">
        <table width="100%" style="background-color: white">
            <tbody>
                <tr>
                    <td bgcolor="#F7941C" style="padding:3px">
                        <a>
                            <xsl:attribute name="href"><xsl:value-of select="link"/></xsl:attribute>
                            <xsl:attribute name="title"><xsl:value-of select="description"/></xsl:attribute>
                            <b>
                                <font color="white">
                                    <xsl:value-of select="title"/>
                                </font>
                            </b>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>
                            <ul>
                                <xsl:for-each select="item">
                                    <li>
                                        <a>
                                            <xsl:attribute name="href"><xsl:value-of select="link"/></xsl:attribute>
                                            <xsl:attribute name="title"><xsl:value-of select="comments"/></xsl:attribute>
                                            <xsl:value-of select="title"/>
                                        </a>
                                        <br/>
                                        <script language="javascript">document.write('<xsl:value-of select="description"/>');</script>
                                    </li>
                                </xsl:for-each>
                            </ul>
                        </span>
                    </td>
                </tr>
            </tbody>
        </table>
    </xsl:template>
</xsl:stylesheet>
