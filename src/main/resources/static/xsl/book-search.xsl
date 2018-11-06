<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html"/>

  <xsl:param name="searchValue" select="5"/>

  <xsl:template match="books">
      <table class="table">
          <thead>
              <tr>
                  <th>No.</th>
                  <th class="w200">Title</th>
                  <th>Authors</th>
                  <th>Price</th>
                  <th>Site</th>
              </tr>
          </thead>
          <tbody>
          <xsl:for-each select="book[contains(title, $searchValue) or contains(authors, $searchValue)]">
              <tr>
                  <td>
                      <xsl:number level="single" count="book[contains(title, $searchValue) or contains(authors, $searchValue)]"/>
                  </td>
                  <td class="w200">
                      <xsl:value-of select="title"/>
                  </td>
                  <td>
                      <xsl:value-of select="authors"/>
                  </td>
                  <td>
                      <xsl:value-of select="price"/>
                      <xsl:if test="oldPrice and oldPrice/text() != price/text()">
                      <br/>
                      <span class="oldPrice">
                        <xsl:value-of select="oldPrice"/>
                      </span>
                      </xsl:if>
                  </td>
                  <td>
                      <a target="_blank" href="{link}"><xsl:value-of select="siteName"/></a>
                  </td>
              </tr>
          </xsl:for-each>
          </tbody>
      </table>
  </xsl:template>

</xsl:stylesheet>