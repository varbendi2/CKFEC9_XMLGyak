<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- Elveti a felesleges szóközöket az XML elemekből -->
    <xsl:strip-space elements="*"/>
    
    <!-- A kimenet XML formátumban, UTF-8 kódolással és behúzással -->
    <xsl:output method="xml" encoding="UTF-8" indent="yes" standalone="yes"/>

    <!-- Identity template: Másolja az összes elemet és attribútumot -->
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Sablon a gyökérelem (Hibajegy) közvetlen gyermekelemeire és kommentekre -->
    <xsl:template match="/*/*|/*/comment()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
        <!-- Új sor hozzáadása minden gyermekelem után -->
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- Sablon a Hibajegy elem feldolgozására -->
    <xsl:template match="Hibajegy">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Sablon a HibajegyRecord elem feldolgozására -->
    <xsl:template match="HibajegyRecord">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Sablon a KapcsolattartoiAdatok elem formázására -->
    <xsl:template match="KapcsolattartoiAdatok">
        <KapcsolattartoiAdatok>
            <xsl:apply-templates select="@*|node()"/>
        </KapcsolattartoiAdatok>
    </xsl:template>

    <!-- Sablon a TV, Telefon és Internet elemek formázására -->
    <xsl:template match="TV|Telefon|Internet">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Sablon a Hiba elem kiírására -->
    <xsl:template match="Hiba">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
