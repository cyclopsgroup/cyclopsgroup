<?xml version="1.0" encoding="UTF-8"?>
<x:stylesheet version="1.0" xmlns:x="http://www.w3.org/1999/XSL/Transform">
    <x:template match="/">
        <x:for-each select="rss/channel/item">
            <p />
            <p>
                <a>
                    <x:attribute name="href">
                        <x:value-of select="link" />
                    </x:attribute>
                    <b>
                        <x:value-of select="title" />
                    </b>
                </a>
            </p>
            <p>
                <x:value-of select="description" />
            </p>
            <div align="right">
                <p>
                    <small>
                        Publish at
                        <x:value-of select="pubDate" />
                        by
                        <x:value-of select="author" />
                    </small>
                </p>
            </div>
        </x:for-each>
    </x:template>
</x:stylesheet>