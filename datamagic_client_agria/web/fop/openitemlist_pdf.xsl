<?xml version="1.0" encoding="utf-8"?>

	<!-- stylesheet declaration -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:param name="symbol.font.family" select="'Symbol,ArialNarrow,Lucida Sans Unicode'" />

	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">

		<!-- xsl-fo root -->
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<fo:layout-master-set>
				<fo:simple-page-master master-name="hello"
					margin-right="0.75cm" margin-top="0.25cm" page-height="29.7cm"
					page-width="21.0cm" margin-left="0.75cm">
					<fo:region-body margin-left="1cm" margin-right="1cm" margin-top="2cm"  margin-bottom="1cm"/>
					<fo:region-before extent=".1in" />
					<fo:region-after extent=".2in" />
					<fo:region-start extent=".0in" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="hello">
				<fo:static-content flow-name="xsl-region-before">
					<fo:block font-size="8pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="0pt">

							<fo:table-column column-width="100%" />

							<fo:table-body>
								<fo:table-row border-bottom="solid 0.02cm black">
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="center" font-size="12pt"
											font-weight="bold">
											Nyitott tételek listája
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-bottom="18pt" padding-top="6pt">
										<fo:block text-align="center">
											nyitott tételek
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:static-content>

				<fo:static-content flow-name="xsl-region-after">
					<fo:block font-size="8pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="0pt" >

							<fo:table-column column-width="30%" />
							<fo:table-column column-width="70%" />

							<fo:table-body>
								<fo:table-row border-top="solid 0.1cm black">
									<fo:table-cell padding-top="3pt">
										<fo:block text-align="left" font-size="8pt"
											font-weight="bold">
											DATAMAGIC
											VÁLLALATIRÁNYÍTÁSI RENDSZER - WEB
											ÜGYVITELI MODUL -
											www.datamagic.hu
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="3pt">
										<fo:block text-align="right" font-size="7pt"
											font-weight="bold">
											Az adatok csak tájékoztató jelleggel vannak feltüntetve
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="8pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow" padding-top="3pt">
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="2pt">

							<fo:table-column column-width="4cm" />
							<fo:table-column column-width="2.25cm" />
							<fo:table-column column-width="2.25cm" />
							<fo:table-column column-width="2.25cm" />
							<fo:table-column column-width="2.25cm" />
							<fo:table-column column-width="2.25cm" />
							<fo:table-column column-width="2.25cm" />

							<fo:table-header>
								<fo:table-row border-bottom="solid 0.02cm black">
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="left">
											<fo:inline>Telephely</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="left">
											<fo:inline>Sorszám</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="left">
											<fo:inline>Teljesítés kelte</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="left">
											<fo:inline>Esedékesség kelte</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="left">
											<fo:inline>Kifizetés kelte</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="3pt">
										<fo:block text-align="right">
											<fo:inline>Nettó (Ft)</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt">
										<fo:block text-align="right">
											<fo:inline>Bruttó (Ft)</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-header>

							<fo:table-body>
								<xsl:for-each select="list/main/InvoiceDocVO">
									<fo:table-row>
										<fo:table-cell padding-top="12pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./telephely" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="12pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./sorszam" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="12pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./telj" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="12pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./esedkelt" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="12pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./fizetve" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="12pt">
											<fo:block text-align="right" font-weight="bold">
												<xsl:value-of select="./netto" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="12pt">
											<fo:block text-align="right" font-weight="bold">
												<xsl:value-of select="./brutto" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
								<fo:table-row>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								
								<fo:table-row border-top="solid 0.02cm black">
									<fo:table-cell padding-top="6pt">
										<fo:block font-weight="bold" font-size="12pt">
											Összesen
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block text-align="right" font-size="12pt"
											font-weight="bold">nettó 
											<xsl:value-of select="list/allNetto" />
											(Ft)
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block text-align="right" font-size="12pt"
											font-weight="bold">bruttó 
											<xsl:value-of select="list/allBrutto" />
											(Ft)
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

		</fo:root>

	</xsl:template>
</xsl:stylesheet>
