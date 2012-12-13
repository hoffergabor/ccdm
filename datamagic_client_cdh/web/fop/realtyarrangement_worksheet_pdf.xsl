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
					margin-right="1cm" margin-top="0.6cm" margin-bottom="0.6cm"
					margin-left="1cm" page-height="29.7cm" page-width="21.0cm">
					<fo:region-body />
					<fo:region-after extent="0.2in" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="hello"
				initial-page-number="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block font-size="7pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
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
										<fo:block font-weight="normal" text-align="right">
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
					<fo:block text-align="center" margin-bottom="3pt">
						<fo:inline font-weight="bold" font-size="8pt">Munkalap
						</fo:inline>
					</fo:block>
					<fo:block font-size="7pt" font-weight="normal" font-style="normal"
						font-family="ArialNarrow">
						<xsl:for-each select="list/main/RealtyArrangementWorkSheetVO">
							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt">
								<fo:table-column column-width="5.1cm" />
								<fo:table-column column-width="4.5cm" />
								<fo:table-column column-width="5.15cm" />
								<fo:table-column column-width="4.25cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt">
										<fo:table-cell height="1.8cm">
											<fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Munkavégzők:</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Munkalap száma:</fo:inline>
											</fo:block>
											<fo:block text-align="center" line-height="12pt"
												font-size="9pt" padding-top="12pt" font-weight="bold">
												<xsl:value-of select="./munkalapSzama" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Kiállítás dátuma, kiállító:</fo:inline>
											</fo:block>
											<fo:block text-align="center" line-height="12pt"
												font-size="7pt" padding-top="6pt" font-weight="bold">
												<xsl:value-of select="./kiallitasDatuma" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.1cm">
								<fo:table-column column-width="13.3cm" />
								<fo:table-column column-width="5.7cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt" height="1.1cm">
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Hibabejelentő neve (telefon),
													bejelentés módja:</fo:inline>
											</fo:block>
											<fo:block text-align="left" line-height="12pt"
												font-size="7pt" padding-top="3pt" font-weight="bold">
												<xsl:value-of select="./hibabejelentoNeve" />
												<xsl:value-of select="./bejelentesModja" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Bejelentés dátuma, napja, időpontja:
												</fo:inline>
											</fo:block>
											<fo:block text-align="left" line-height="12pt"
												font-size="7pt" padding-top="3pt" font-weight="bold">
												<xsl:value-of select="./bejelentesDatuma" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt">
								<fo:table-column column-width="19cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt" height="3.15cm">
										<fo:table-cell border-left="solid 0.03cm black"
											border-right="solid 0.03cm black" border-bottom="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Bejelentett hiba, kért munka:
												</fo:inline>
												<fo:block text-align="left" line-height="12pt"
													font-size="7pt" padding-top="3pt" font-weight="bold">
													<xsl:value-of select="./bejelentettHiba" />
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.1cm">
								<fo:table-column column-width="6.9cm" />
								<fo:table-column column-width="6.4cm" />
								<fo:table-column column-width="5.7cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt" height="0.55cm">
										<fo:table-cell border="solid 0.03cm black"
											number-columns-spanned="2">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Munkát elrendelte (telefon):
											</fo:inline>
												<fo:inline padding-left="2cm" font-size="7pt"
													font-weight="bold">
													<xsl:value-of select="./munkatElrendelte" />
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black"
											number-rows-spanned="3" padding-top="2pt">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>Munkavégzés határideje:
											</fo:inline>
											</fo:block>
											<fo:block text-align="center" line-height="12pt"
												font-size="7pt" padding-top="12pt" font-weight="bold">
												<xsl:value-of select="./munkavegzesHatarideje" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="0.55cm">
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Munkafajta:</fo:inline>
												<fo:inline padding-left="2cm" font-size="7pt"
													font-weight="bold">
													<xsl:value-of select="./munkaFajta" />
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>
													Prioritás:
													<fo:inline padding-left="2cm" font-size="7pt"
														font-weight="bold">
														<xsl:value-of select="./prioritas" />
													</fo:inline>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="0.55cm">
										<fo:table-cell border-left="solid 0.03cm black"
											border-right="solid 0.03cm black" number-columns-spanned="2">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Kiadott és elvégzett munka (maradéktalanul
													elvégzett munkatételek, -fázisok áthúzva):
											</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="4.8cm">
										<fo:table-cell border="solid 0.03cm black"
											number-columns-spanned="3">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline font-size="7pt" font-weight="bold">
													<xsl:value-of select="./elvegzettMunka" />
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.1cm">
								<fo:table-column column-width="9.5cm" />
								<fo:table-column column-width="3.8cm" />
								<fo:table-column column-width="5.7cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt" height="1.15cm">
										<fo:table-cell border="solid 0.03cm black"
											number-rows-spanned="3">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Munkavégzés helye (cím):
											</fo:inline>
											</fo:block>
											<fo:block text-align="left" line-height="12pt"
												font-size="7pt" padding-top="3pt" font-weight="bold">
												<xsl:value-of select="./munkavegzesHelye" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Aktuális rendeltetés:
											</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black"
											number-rows-spanned="2">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Ingatlan tulajdonos / megbízó:
												</fo:inline>
											</fo:block>
											<fo:block text-align="center" line-height="12pt"
												font-size="7pt" padding-top="6pt" font-weight="bold">
												<xsl:value-of select="./ingatlanTulajdonos" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="0.45cm">
										<fo:table-cell border-top="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Aktuális állapot:
											</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="0.6cm">
										<fo:table-cell border-bottom="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt">
												<fo:inline>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left" line-height="12pt"
												padding-top="2pt">
												<fo:inline>Tarifa:
											</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>


							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.1cm">
								<fo:table-column column-width="7.75cm" />
								<fo:table-column column-width="0.1cm" />
								<fo:table-column column-width="11.15cm" />
								<fo:table-body>
									<fo:table-row height="7.35cm">
										<fo:table-cell border="solid 0.03cm black"
											font-size="6pt">
											<fo:block text-align="left">
												<fo:table table-layout="fixed" width="100%"
													border-spacing="2pt">
													<fo:table-column column-width="1.4cm" />
													<fo:table-column column-width="1.25cm" />
													<fo:table-column column-width="0.25cm" />
													<fo:table-column column-width="1.25cm" />
													<fo:table-column column-width="1.25cm" />
													<fo:table-column column-width="0.25cm" />
													<fo:table-column column-width="1.25cm" />
													<fo:table-column column-width="0.85cm" />
													<fo:table-body>
														<fo:table-row height="0.35cm">
															<fo:table-cell border-right="solid 0.06cm black"
																number-rows-spanned="3">
																<fo:block text-align="center" padding-top="0.5cm">
																	<fo:inline>Munkavégzési dátumok (hó.nap)
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="3"
																border-bottom="solid 0.03cm black" border-right="solid 0.06cm black">
																<fo:block text-align="left">
																	<fo:inline>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="3"
																border-bottom="solid 0.03cm black">
																<fo:block text-align="left">
																	<fo:inline>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-rows-spanned="3"
																border-left="solid 0.06cm black">
																<fo:block text-align="center" padding-top="0.75cm">
																	<fo:inline>S mvi
																</fo:inline>
																</fo:block>
																<fo:block text-align="center">
																	<fo:inline>(ó)
																</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="0.37cm">
															<fo:table-cell border-bottom="solid 0.03cm black"
																number-columns-spanned="6">
																<fo:block text-align="center" padding-top="2pt">
																	<fo:inline>Időpontok
																</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="1.1cm" border-bottom="solid 0.03cm black">
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block text-align="center" padding-top="12pt">
																	<fo:inline>Kezdés (ó/p)
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block text-align="center" padding-top="12pt">
																	<fo:inline>-
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.06cm black">
																<fo:block text-align="center" padding-top="8pt">
																	<fo:inline>Befejezés (ó/p)
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block text-align="center" padding-top="6pt">
																	<fo:inline>Kezdés (ó/p)
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block text-align="center" padding-top="12pt">
																	<fo:inline>-
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block text-align="center" padding-top="6pt">
																	<fo:inline>Befejezés (ó/p)
																</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="5.1cm" border-bottom="solid 0.03cm black">
															<fo:table-cell border-right="solid 0.06cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.06cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.06cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="0.43cm">
															<fo:table-cell border-right="solid 0.06cm black">
																<fo:block>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.06cm black"
																number-columns-spanned="3">
																<fo:block text-align="center" padding-top="3pt">
																	<fo:inline>S ksz (alkalom)
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell border-right="solid 0.06cm black"
																number-columns-spanned="3">
																<fo:block text-align="right" padding-top="3pt">
																	<fo:inline>mvi S S:
																</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
												<fo:inline>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 0.03cm black">
											<fo:block text-align="left">
												<fo:table table-layout="fixed" width="100%"
													border-spacing="2pt">
													<fo:table-column column-width="1.8cm" />
													<fo:table-column column-width="9.35cm" />
													<fo:table-body>
														<fo:table-row height="0.7cm" start-indent="3pt">
															<fo:table-cell number-columns-spanned="2">
																<fo:block text-align="left" padding-top="2pt">
																	<fo:inline>A munka során felhasznált anyagok:
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="0.65cm" start-indent="3pt">
															<fo:table-cell number-columns-spanned="2">
																<fo:block text-align="left" font-size="6pt">
																	<fo:inline>(A számla, szállítólevél száma, a csatolt
																		másolaton megjelölt tételek, mennyiségek) 
																</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="0.5cm" start-indent="3pt">
															<fo:table-cell number-columns-spanned="2"
																border-top="solid 0.03cm black" border-bottom="solid 0.03cm black">
																<fo:block text-align="left" padding-top="3pt">
																	<fo:inline>Mennyiség
																</fo:inline>
																	<fo:inline padding-left="1.1cm">Megnevezés
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row height="5.1cm">
															<fo:table-cell border-right="solid 0.03cm black">
																<fo:block text-align="left">
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row border-top="solid 0.03cm black"
															height="0.4cm">
															<fo:table-cell number-columns-spanned="2">
																<fo:block text-align="center" padding-top="2pt">
																	<fo:inline>Végösszeg:
																</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>

											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.1cm" border="solid 0.03cm black">
								<fo:table-column column-width="19cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt" height="1.15cm">
										<fo:table-cell>
											<fo:block text-align="left" line-height="12pt" padding-top="2pt">
												<fo:inline>Megjegyzések:
											</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="0.55cm">
										<fo:table-cell>
											<fo:block text-align="left">
												<fo:inline>Az elvégzett szolgáltatás minősítése:
												</fo:inline>
												<fo:instream-foreign-object
													content-height="1em" padding-left="2.55cm">
													<svg:svg xmlns:svg="http://www.w3.org/2000/svg"
														height="0.35cm" width="0.35cm" viewBox="0 0 1500 1500">
														<svg:rect width="100%" height="100%"
															style="fill:white; stroke:black" stroke-width="10%" />
													</svg:svg>
												</fo:instream-foreign-object>
												<fo:inline padding-left="0.25cm">Kiváló
											</fo:inline>
												<fo:instream-foreign-object
													content-height="1em" padding-left="2.2cm">
													<svg:svg xmlns:svg="http://www.w3.org/2000/svg"
														height="0.35cm" width="0.35cm" viewBox="0 0 1500 1500">
														<svg:rect width="100%" height="100%"
															style="fill:white; stroke:black" stroke-width="10%" />
													</svg:svg>
												</fo:instream-foreign-object>
												<fo:inline padding-left="0.25cm">Jó
											</fo:inline>
												<fo:instream-foreign-object
													content-height="1em" padding-left="2.2cm">
													<svg:svg xmlns:svg="http://www.w3.org/2000/svg"
														height="0.35cm" width="0.35cm" viewBox="0 0 1500 1500">
														<svg:rect width="100%" height="100%"
															style="fill:white; stroke:black" stroke-width="10%" />
													</svg:svg>
												</fo:instream-foreign-object>
												<fo:inline padding-left="0.25cm">Megfelelő
												</fo:inline>
												<fo:instream-foreign-object
													content-height="1em" padding-left="2.2cm">
													<svg:svg xmlns:svg="http://www.w3.org/2000/svg"
														height="0.35cm" width="0.35cm" viewBox="0 0 1500 1500">
														<svg:rect width="100%" height="100%"
															style="fill:white; stroke:black" stroke-width="10%" />
													</svg:svg>
												</fo:instream-foreign-object>
												<fo:inline padding-left="0.25cm">Javítást igényel
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>

							<fo:table table-layout="fixed" width="100%"
								border-spacing="2pt" margin-top="0.1cm" border="solid 0.03cm black">
								<fo:table-column column-width="6.2cm" />
								<fo:table-column column-width="12.8cm" />
								<fo:table-body>
									<fo:table-row start-indent="3pt" height="1cm"
										border-bottom="solid 0.03cm black">
										<fo:table-cell border-right="solid 0.03cm black">
											<fo:block text-align="left" line-height="28pt">
												<fo:inline>A kiadott munka a fenti leírás szerint
													elvégezve.
											</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block text-align="left" line-height="12pt" padding-top="2pt">
												<fo:inline>Munkavégzők aláírása:
											</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row start-indent="3pt" height="1cm">
										<fo:table-cell number-columns-spanned="2">
											<fo:block text-align="left" line-height="12pt" padding-top="2pt">
												<fo:inline>A munka elvégzésének igazolásául a munkát
													átvette:
											</fo:inline>
											</fo:block>
											<fo:block text-align="left" line-height="12pt"
												padding-top="3pt">
												<fo:inline padding-left="0.7cm">Dátum: 20
												</fo:inline>
												<fo:inline padding-left="4.2cm" padding-right="6.2cm">
													Név:
											</fo:inline>
												<fo:inline>Aláírás:
											</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:for-each>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>