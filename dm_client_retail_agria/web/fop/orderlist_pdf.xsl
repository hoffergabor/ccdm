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
					margin="1cm" page-height="29.7cm" page-width="21.0cm">
					<fo:region-body margin-top="1.5cm" />
					<fo:region-before extent=".1in" />
					<fo:region-after extent=".2in" />
					<fo:region-start extent=".0in" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="hello">
				<fo:static-content flow-name="xsl-region-before">
					<fo:block font-size="16pt" font-weight="normal"
						font-style="normal" font-family="ArialNarrow">
						<fo:table table-layout="fixed" width="100%"
							border-spacing="0pt">

							<fo:table-column column-width="100%" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-bottom="3pt">
										<fo:block text-align="left" font-size="16pt"
											font-weight="bold">
											Rendelés
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-bottom="3pt" font-size="16pt">
										<fo:block text-align="left">
											SORSZÁM:
											<xsl:value-of select="list/main/sorszam" />
											- KELT:
											<xsl:value-of select="list/main/kelt" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:static-content>

				<fo:static-content flow-name="xsl-region-after">
					<fo:block font-size="7pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="0pt">

							<fo:table-column column-width="50%" />
							<fo:table-column column-width="50%" />

							<fo:table-body>
								<fo:table-row border-top="solid 0.1cm black">
									<fo:table-cell padding-top="6pt">
										<fo:block text-align="left" font-size="7pt"
											font-weight="bold">
											DATAMAGIC
											VÁLLALATIRÁNYÍTÁSI RENDSZER - WEB
											ÜGYVITELI MODUL -
											www.datamagic.hu
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
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
					<fo:block font-size="10pt" font-weight="normal"
						font-style="normal" font-family="ArialNarrow" padding-top="3pt">
						<fo:table border="solid 0.1cm black" table-layout="fixed"
							width="100%" border-spacing="2pt">

							<fo:table-column column-width="15%" />
							<fo:table-column column-width="35%" />
							<fo:table-column column-width="15%" />
							<fo:table-column column-width="35%" />

							<fo:table-body>
								<xsl:for-each select="list/main">
									<fo:table-row>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>szállító</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:text>Agria Drink Kft. - logisztika</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>vevő</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./vnev" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>cím</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:text>3300 Eger, Kistályai út 18.</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>cím</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./virsz" />
												<xsl:text> </xsl:text>
												<xsl:value-of select="./vvaros" />
												<xsl:text> </xsl:text>
												<xsl:value-of select="./vcim" />
												<xsl:text> </xsl:text>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>telefon</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:text>36/517-577</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>telefon</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./vkapcstel" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>email</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:text>rendeles@agriadrink.hu</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>ügyintéző</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./ugyint" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>adószám</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:text>11175724-2-10</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>fizetési mód</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./fizmod" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>közösségi adósz.</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:text>HU11175724</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>szállítási mód</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt"  border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./fuvmod" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block border-bottom="solid 0.03cm black">
												<xsl:text></xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-right="solid 0.03cm black">
											<fo:block border-bottom="solid 0.03cm black">
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-left="3pt">
												<xsl:text>megjegyzés</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-right="solid 0.03cm black">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./megj" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:text>átvevő neve</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./atvevo" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>

									<fo:table-row>
										<fo:table-cell padding-bottom="12pt">
											<fo:block margin-left="3pt">
												<xsl:text></xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-right="solid 0.03cm black"
											padding-bottom="12pt">
											<fo:block margin-left="3pt">
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" padding-bottom="12pt">
											<fo:block margin-left="3pt">
												<xsl:text>igényelt határidő</xsl:text>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="3pt" padding-bottom="12pt">
											<fo:block margin-left="3pt">
												<xsl:value-of select="./esedkelt" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="10pt" font-weight="normal"
						font-style="normal" font-family="ArialNarrow">
						<fo:table table-layout="fixed" width="100%"
							border-spacing="2pt">

							<fo:table-column column-width="13%" />
							<fo:table-column column-width="37%" />
							<fo:table-column column-width="12.5%" />
							<fo:table-column column-width="12.5%" />
							<fo:table-column column-width="12.5%" />
							<fo:table-column column-width="12.5%" />

							<fo:table-header border="solid 0.1cm black">
								<fo:table-row background-color="black">
									<fo:table-cell padding-bottom="6pt" padding-top="6pt">
										<fo:block>
											<fo:inline color="white">cikkszám</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt" padding-top="6pt">
										<fo:block>
											<fo:inline color="white">megnevezés</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt" padding-top="6pt">
										<fo:block text-align="right">
											<fo:inline color="white">mennyiség</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt" padding-top="6pt">
										<fo:block text-align="right">
											<fo:inline color="white">egységár (nettó)
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-bottom="6pt" padding-top="6pt">
										<fo:block text-align="right">
											<fo:inline color="white">érték (nettó)</fo:inline>
										</fo:block>
									</fo:table-cell>									
									<fo:table-cell padding-bottom="6pt" padding-top="6pt">
										<fo:block text-align="right">
											<fo:inline color="white">érték (bruttó)</fo:inline>
										</fo:block>
									</fo:table-cell>									
								</fo:table-row>
							</fo:table-header>

							<fo:table-body>
								<xsl:for-each select="list/item/OrderItemDocVO">
									<fo:table-row border-bottom="dotted 0.01cm black">
										<fo:table-cell padding-top="6pt" padding-bottom="6pt">
											<fo:block>
												<xsl:value-of select="./acikksz" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt" padding-bottom="6pt">
											<fo:block>
												<xsl:value-of select="./amegn" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt" padding-bottom="6pt">
											<fo:block text-align="right">
												<xsl:value-of select="./amenny" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt" padding-bottom="6pt">
											<fo:block text-align="right">
												<xsl:value-of select="./egysegar" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt" padding-bottom="6pt">
											<fo:block text-align="right">
												<xsl:value-of select="./nertek" />
											</fo:block>
										</fo:table-cell>										
										<fo:table-cell padding-top="6pt" padding-bottom="6pt">
											<fo:block text-align="right">
												<xsl:value-of select="./bertek" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>

							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="10pt" font-weight="normal"
						font-style="normal" font-family="ArialNarrow">
						<fo:table table-layout="fixed" width="100%"
							border-spacing="2pt">

							<fo:table-column column-width="50%" />
							<fo:table-column column-width="12.5%" />
							<fo:table-column column-width="12.5%" />
							<fo:table-column column-width="5%" />
							<fo:table-column column-width="20%" />

							<fo:table-body background-color="black">
								<xsl:for-each select="list/main">
								<fo:table-row>
									<fo:table-cell padding-top="6pt">
										<fo:block text-align="right" color="white">
											<xsl:text>összes tétel: </xsl:text>
											<xsl:value-of select="./items" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block text-align="right" color="white">
											<xsl:text>összesen (nettó)</xsl:text>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt">
										<fo:block text-align="right" font-size="12pt"
											font-weight="bold" color="white">
											<xsl:value-of select="./allNetto" />
											(Ft)
											<xsl:text> </xsl:text>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-top="6pt" padding-bottom="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt" padding-bottom="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt" padding-bottom="6pt">
										<fo:block text-align="right" color="white">
											<xsl:text>összesen (bruttó)</xsl:text>
										</fo:block>
									</fo:table-cell>

									<fo:table-cell padding-top="6pt" padding-bottom="6pt">
										<fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="6pt" padding-bottom="6pt">
										<fo:block text-align="right" font-size="12pt"
											font-weight="bold" color="white">
											<xsl:value-of select="./allBrutto" />
											(Ft)
											<xsl:text> </xsl:text>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="10pt" font-weight="normal"
						font-style="normal" font-family="ArialNarrow" padding-top="3cm">
						<fo:table table-layout="fixed" width="100%"
							border-spacing="2pt">

							<fo:table-column column-width="50%" />
							<fo:table-column column-width="50%" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block text-align="left">
											<xsl:text>Kelt:</xsl:text>
											<xsl:text> Eger, </xsl:text>
											<xsl:value-of select="list/now" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="center">
										<fo:block><xsl:text>aláírás</xsl:text>
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
