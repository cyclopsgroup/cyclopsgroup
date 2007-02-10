<?xml version="1.0" encoding="UTF-8"?>
<x:stylesheet version="1.0" xmlns:x="http://www.w3.org/1999/XSL/Transform">
    <x:template match="/">
        <table class="bodyTable" widht="100%">
            <thead>
                <th>Publish Date</th>
                <th>Title</th>
                <th>Download</th>
            </thead>
            <tbody>
                <x:for-each select="rss/channel/item">
                    <tr>
                        <x:attribute name="class">
                            <x:if test="position() mod 2 = 0">a</x:if>
                            <x:if test="position() mod 2 = 1">b</x:if>
                        </x:attribute>
                        <td>
                            <small>
                                <x:value-of select="pubDate" />
                            </small>
                        </td>
                        <td>
                            <x:value-of select="title" />
                        </td>
                        <td>
                            <a>
                                <x:attribute name="href">
                                    <x:value-of select="link" />
                                </x:attribute>
                                <img src="images/archive.png" alt="Download" />
                            </a>
                        </td>
                    </tr>
                </x:for-each>
            </tbody>
        </table>
    </x:template>
</x:stylesheet>