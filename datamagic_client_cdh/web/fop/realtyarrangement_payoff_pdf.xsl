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
					<fo:region-body />
					<fo:region-after extent="0.2in" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="hello"
				initial-page-number="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block font-size="7pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
						<fo:block text-align="right">
							<fo:page-number />
							. oldal
						</fo:block>
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="0pt">
							<fo:table-column column-width="50%" />
							<fo:table-column column-width="50%" />
							<fo:table-body>
								<fo:table-row border-top="solid 0.03cm black">
									<fo:table-cell padding-top="3pt">
										<fo:block font-weight="normal">
											<fo:inline>
												DataMagic Vllalatirányítási Rendszer - WEB
												Könyvviteli Modul </fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="3pt">
										<fo:block font-weight="normal"
											text-align="right">
											<fo:inline>
												www.datamagic.hu</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">

					<fo:block font-size="7pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
						<xsl:for-each select="list/main/RealtyArrangementPayOffDocVO">
							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt">
								<fo:table-column column-width="4.2cm" />
								<fo:table-column column-width="5.6cm" />
								<fo:table-column column-width="0.2cm" />
								<fo:table-column column-width="5.2cm" />
								<fo:table-column column-width="0.2cm" />
								<fo:table-column column-width="3.7cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell>
											<fo:block>
												<fo:inline></fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black"
											height="1.1cm">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Ingatlan tulajdonos</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./tulajdonos" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black"
											font-size="9pt" font-weight="bold">
											<fo:block text-align="center" line-height="30pt">
												<fo:inline>Intézkedés elszámolás</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Sorszám</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./sorszam" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.2cm">
								<fo:table-column column-width="9.8cm" />
								<fo:table-column column-width="0.2cm" />
								<fo:table-column column-width="9.1cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell border="solid 0.03cm black"
											height="0.9cm">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Számla címzettje</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./szCimzett" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Munkavégzés helye, ingatlan címe
												</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./ingCim" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.2cm">
								<fo:table-column column-width="9.8cm" />
								<fo:table-column column-width="0.2cm" />
								<fo:table-column column-width="9.1cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell border="solid 0.03cm black"
											height="0.65cm">
											<fo:block text-align="left" line-height="20pt">
												<fo:inline>Bejelentő</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./bejelento" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="20pt">
												<fo:inline>Bejelentés időpontja</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./bejelentesIdo" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.2cm">
								<fo:table-column column-width="19.1cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell border="solid 0.03cm black"
											height="4.65cm">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Bejelentett hiba / kért munka leírása
												</fo:inline>
											</fo:block>
											<fo:block font-weight="bold" font-size="10pt">
												<xsl:value-of select="./hibaLeiras" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.2cm">
								<fo:table-column column-width="19.1cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell border="solid 0.03cm black"
											height="4.65cm">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Elvégzett munka leírása</fo:inline>
											</fo:block>
											<fo:block font-weight="bold" font-size="10pt">
												<xsl:value-of select="./munkaLeiras" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.2cm">
								<fo:table-column column-width="11.75cm" />
								<fo:table-column column-width="0.2cm" />
								<fo:table-column column-width="7.15cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell border="solid 0.03cm black"
											height="1.5cm">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Bizonylatok</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./bizonylatok" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Nettó összérték</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./osszNettoAr" />
												Ft
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.2cm" margin-bottom="0.6cm">
								<fo:table-column column-width="11.75cm" />
								<fo:table-column column-width="0.2cm" />
								<fo:table-column column-width="7.15cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell border="solid 0.03cm black"
											height="0.65cm">
											<fo:block text-align="left" line-height="20pt">
												<fo:inline>Munkát végző vállalkozó</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="20pt">
												<fo:inline>Teljesítés dátuma</fo:inline>
											</fo:block>
											<fo:block font-weight="bold">
												<xsl:value-of select="./teljesites" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:for-each>
						<fo:block text-align="center">
							<fo:inline font-weight="bold" font-size="8pt">Munkát
								végző szervíz dolgozó
							</fo:inline>
						</fo:block>
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="6pt" margin-bottom="0.5cm">

							<fo:table-column column-width="2.3cm" />
							<fo:table-column column-width="1.7cm" />
							<fo:table-column column-width="1.7cm" />
							<fo:table-column column-width="1.7cm" />
							<fo:table-column column-width="1.7cm" />
							<fo:table-column column-width="10cm" />

							<fo:table-header>
								<fo:table-row border="solid 0.03cm black"
									start-indent="3pt" font-weight="bold">
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Dolgozó</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Dátum</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Kezd.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Befej.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Időtart.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Megjegyzés</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-header>

							<fo:table-body>
								<xsl:for-each
									select="list/main/RealtyArrangementPayOffDocVO/dolgozo/RealtyArrangementPayOffDolgDocVO">
									<fo:table-row start-indent="3pt">
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./dolgNev" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./dolgDatum" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./dolgKezd" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./dolgBefej" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold">
												<xsl:value-of select="./dolgIdotart" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block text-align="left" font-weight="bold">
												<xsl:value-of select="./dolgMegj" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>

						<fo:block text-align="center">
							<fo:inline font-weight="bold" font-size="8pt">Felhasznált
								anyagok,nyújtott szolgáltatások 
							</fo:inline>
						</fo:block>
						<fo:table border-bottom-color="black" table-layout="fixed"
							width="100%" border-spacing="6pt" margin-bottom="2cm">

							<fo:table-column column-width="6.7cm" />
							<fo:table-column column-width="1.4cm" />
							<fo:table-column column-width="1.4cm" />
							<fo:table-column column-width="2.5cm" />
							<fo:table-column column-width="2.5cm" />
							<fo:table-column column-width="2.5cm" />
							<fo:table-column column-width="2cm" />

							<fo:table-header>
								<fo:table-row border="solid 0.03cm black"
									start-indent="3pt" end-indent="3pt"  font-weight="bold">
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Megnevezés</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>Menny.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="left" line-height="12pt">
											<fo:inline>M.e.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="right" line-height="12pt">
											<fo:inline>Egységár</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="right" line-height="12pt">
											<fo:inline>Érték bonyolítás</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="right" line-height="12pt">
											<fo:inline>Anyagig.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block text-align="right" line-height="12pt">
											<fo:inline>Össz. nettó</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-header>

							<fo:table-body>
								<xsl:for-each
									select="list/main/RealtyArrangementPayOffDocVO/anyag/RealtyArrangementPayOffAnyagDocVO">
									<fo:table-row start-indent="3pt" end-indent="3pt">
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold" text-align="left">
												<xsl:value-of select="./anyagMegn" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold" text-align="left">
												<xsl:value-of select="./anyagMenny" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold" text-align="left">
												<xsl:value-of select="./anyagME" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold" text-align="right">
												<xsl:value-of select="./anyagEar" /> Ft
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block font-weight="bold" text-align="right">
												<xsl:value-of select="./anyagBonyolit" /> Ft
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block text-align="right" font-weight="bold">
												<xsl:value-of select="./anyagIg" /> Ft
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-top="6pt">
											<fo:block text-align="right" font-weight="bold" font-size="9pt">
												<xsl:value-of select="./anyagOsszNetto" /> Ft
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>