<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="no"/>
    <xsl:template match="/">
        <xsl:apply-templates select="html"/>
    </xsl:template>
    <xsl:template match="input[@type='text' or @type='password']">
        <input class="frame">
            <xsl:apply-templates select="*|@*"/>
        </input>
    </xsl:template>
    <xsl:template match="input[@type='button' or @type='submit']">
        <input class="button">
            <xsl:apply-templates select="*|@*"/>
        </input>
    </xsl:template>
    <xsl:template match="table[@class='frame']">
    	<table cellspacing="1">
    		<xsl:apply-templates select="*|@*"/>
    	</table>
    </xsl:template>
    <xsl:template match="textarea">
        <textarea class="frame">
            <xsl:apply-templates select="@*"/>
            <xsl:value-of select="text()"/>
        </textarea>
    </xsl:template>
    <xsl:template match="*|@*|text()">
        <xsl:copy xml:space="default">
            <xsl:apply-templates select="*|@*|text()"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
