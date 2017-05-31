<!-- XSLT Examples for HTML translation -->

<!--**********************************************************
******************************************************************-->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Hospital</th>
      <th>Location</th>
	  <th>Range</th>
      <xsl:for-each select="Blood/Hospital">
         <xsl:if test="@Range &lt; 5">
            <tr>
            <td><i><xsl:value-of select="Name" /></i></td>
            <td><xsl:value-of select="Location" /></td>
            <td><xsl:value-of select="@Range" /></td>
            </tr>
         </xsl:if>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>

<!--*****************************************************************
     TRANSFORMATION TO HTML WITH ITERATION AND SORT
     Table of books sorted by price
	 (note that they're not properly sorted)
******************************************************************-->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Book</th>
      <th>Cost</th>
      <xsl:for-each select="Bookstore/Book">
      <xsl:sort select="@Price" />
         <tr>
         <td><i><xsl:value-of select="Title" /></i></td>
         <td><xsl:value-of select="@Price" /></td>
         </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>

<!--*****************************************************************
     TRANSFORMATION TO HTML WITH ITERATION, SORT AND CONDITIONAL
     Table of books with an author named "Hector",
	 sorted by price
	 (they're properly sorted now)
	 (note the single quotes at contains)
******************************************************************-->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Book</th>
      <th>Cost</th>
      <xsl:for-each select="Bookstore/Book">
      <xsl:sort select="@Price" data-type="number"/>
         <xsl:if test="Authors/Author[contains(First_Name,'Hector')]">
            <tr>
            <td><i><xsl:value-of select="Title" /></i></td>
            <td><xsl:value-of select="@Price" /></td>
            </tr>
         </xsl:if>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>