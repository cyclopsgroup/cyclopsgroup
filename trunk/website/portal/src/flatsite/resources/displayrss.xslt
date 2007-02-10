<?xml version="1.0" encoding="UTF-8"?>
<x:stylesheet version="1.0" xmlns:x="http://www.w3.org/1999/XSL/Transform">
    <x:template match="/">
        <ul>
            <x:for-each select="rss/channel/item">
                <li>
                    <a>
                        <x:attribute name="href">
                            <x:value-of select="link" />
                        </x:attribute>
                        <x:value-of select="title" />
                    </a>
                    -
                    <small>
                        <x:value-of select="pubDate" />
                    </small>
                </li>
            </x:for-each>
        </ul>
    </x:template>
</x:stylesheet>