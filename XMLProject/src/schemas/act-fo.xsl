<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:b="http://www.skupstinans.rs/akti" 	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="akt-page">
					<fo:region-body margin="1in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="akt-page">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-family="sans-serif" font-size="16px" font-weight="normal"  text-align="center">
						<xsl:value-of select="b:Preambula"/>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="32px" font-weight="bold"  text-align="center">
						<xsl:value-of select="b:Naslov"/>
					</fo:block>
					<xsl:for-each select="b:Akt/b:Tacke">
						<fo:block font-family="sans-serif" font-size="28px" font-weight="bold" padding="30px">
							<fo:block>
								<xsl:for-each select="b:Tacka">
									<fo:block font-family="sans-serif" font-size="24px" font-weight="bold"  text-align="center">
										<xsl:value-of select="@BrojTacke"/>
										<fo:block font-family="sans-serif" font-size="18px" font-weight="normal" text-align="center">
											<xsl:for-each select="b:Stav">
												<fo:block>
													<xsl:value-of select="b:Sadrzaj"/>
													<fo:block font-family="sans-serif" font-size="16px" font-weight="normal" text-align="center">
														<xsl:for-each select="b:Alineje">
															<fo:block>
																<fo:block font-family="sans-serif" font-size="17px" font-weight="bold" text-align="center">
																	<xsl:for-each select="b:Alineja">
																		<fo:block>
																			<xsl:value-of select="b:Sadrzaj"/>
																			<fo:block font-family="sans-serif" font-size="17px" font-weight="normal" text-align="center">
																				<xsl:for-each select="b:Podalineje">
																					<fo:block>
																						<fo:block font-family="sans-serif" font-size="16px" font-weight="bold" text-align="left">
																							<xsl:for-each select="b:Alinejapodalineje">
																								<fo:block>
																									<fo:block font-family="sans-serif" font-size="14px" font-weight="normal" text-align="left">
																										<xsl:value-of select="b:Sadrzaj"/>

																									</fo:block>
																								</fo:block>
																							</xsl:for-each>
																						</fo:block>
																					</fo:block>
																				</xsl:for-each>
																			</fo:block>
																		</fo:block>
																	</xsl:for-each>
																</fo:block>
															</fo:block>
														</xsl:for-each>
													</fo:block>
												</fo:block>
											</xsl:for-each>
										</fo:block>
									</fo:block>
								</xsl:for-each>
							</fo:block>
						</fo:block>
					</xsl:for-each>

					<fo:block font-family="sans-serif" font-size="28px" font-weight="bold" padding="30px">
						<xsl:for-each select="b:Akt/b:NormativniDelovi">
							<fo:block>
								<xsl:for-each select="b:NormativniDeo">
									<fo:block font-family="sans-serif" font-size="24px" font-weight="bold"  text-align="center">
										<xsl:value-of select="b:Sadrzaj" />
										<fo:block font-family="sans-serif" font-size="18px" font-weight="normal" text-align="center">
											<xsl:for-each select="b:Clan">
												<fo:block>
													<xsl:value-of select="@BrojClana"/>
													<fo:block font-family="sans-serif" font-size="16px" font-weight="normal" text-align="center">
														<xsl:for-each select="b:Stav">
															<fo:block>
																<xsl:value-of select="@Naziv"/>
																<fo:block font-family="sans-serif" font-size="17px" font-weight="bold" text-align="center">
																	<xsl:for-each select="b:Clan">
																		<fo:block>
																			<xsl:value-of select="@Naziv"/>
																			<fo:block font-family="sans-serif" font-size="17px" font-weight="normal" text-align="center">
																				<xsl:for-each select="b:Stav">
																					<fo:block>
																						<xsl:value-of select="b:Sadrzaj"/>
																						<fo:block font-family="sans-serif" font-size="16px" font-weight="normal" text-align="center">
																							<xsl:for-each select="b:Alineje">
																								<fo:block>
																									<fo:block font-family="sans-serif" font-size="17px" font-weight="bold" text-align="center">
																										<xsl:for-each select="b:Alineja">
																											<fo:block>
																												<xsl:value-of select="b:Sadrzaj"/>
																												<fo:block font-family="sans-serif" font-size="17px" font-weight="normal" text-align="center">
																													<xsl:for-each select="b:Podalineje">
																														<fo:block>
																															<fo:block font-family="sans-serif" font-size="16px" font-weight="bold" text-align="left">
																																<xsl:for-each select="b:Alinejapodalineje">
																																	<fo:block>
																																		<fo:block font-family="sans-serif" font-size="14px" font-weight="normal" text-align="left">
																																			<xsl:value-of select="b:Sadrzaj"/>

																																		</fo:block>
																																	</fo:block>
																																</xsl:for-each>
																															</fo:block>
																														</fo:block>
																													</xsl:for-each>
																												</fo:block>
																											</fo:block>
																										</xsl:for-each>
																									</fo:block>
																								</fo:block>
																							</xsl:for-each>
																						</fo:block>
																					</fo:block>
																				</xsl:for-each>
																			</fo:block>
																		</fo:block>
																	</xsl:for-each>
																</fo:block>
															</fo:block>
														</xsl:for-each>
													</fo:block>
												</fo:block>
											</xsl:for-each>
										</fo:block>
									</fo:block>
								</xsl:for-each>

							</fo:block>
						</xsl:for-each>
					</fo:block>

			
				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>
</xsl:stylesheet>