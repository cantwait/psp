<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns2="http://service.psp.pdvsa.com/" exclude-result-prefixes="soap ns2">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<opcItemsRequest>
		<opcItems>
		<xsl:for-each select="/soap:Envelope/soap:Body/ns2:findUntransferredItemsResponse/items">
			<opcItem>
				<activo><xsl:value-of select="activo" /></activo>
				<descripcion><xsl:value-of select="descripcion" /></descripcion>  
				<hda><xsl:value-of select="hda"/></hda>  
				<id><xsl:value-of select="id"/></id>
				<itemOpc><xsl:value-of select="itemOpc"/></itemOpc>									
				<nombre><xsl:value-of select="nombre"/></nombre>
				<tipoDato><xsl:value-of select="tipoDato"/></tipoDato>
				<tipoItem><xsl:value-of select="tipoItem"/></tipoItem>
				<transferred><xsl:value-of select="transferred"/></transferred>
				<unidadMedida>
					<descripcion><xsl:value-of select="unidadMedida/descripcion"/></descripcion>
					<id><xsl:value-of select="unidadMedida/id"/></id>
					<nombre><xsl:value-of select="unidadMedida/nombre"/></nombre>
					<tipoUnidadMedida>
						<descripcion><xsl:value-of select="unidadMedida/tipoUnidadMedida/descripcion"/></descripcion>
						<id><xsl:value-of select="unidadMedida/tipoUnidadMedida/id"/></id>
						<nombre><xsl:value-of select="unidadMedida/tipoUnidadMedida/nombre"/></nombre>
						<version><xsl:value-of select="unidadMedida/tipoUnidadMedida/version"/></version>
					</tipoUnidadMedida>
					<version><xsl:value-of select="unidadMedida/version"/></version>
				</unidadMedida>
				<version><xsl:value-of select="version"/></version>									
			</opcItem>
		</xsl:for-each>
		</opcItems>
		</opcItemsRequest>
	</xsl:template>
</xsl:stylesheet>