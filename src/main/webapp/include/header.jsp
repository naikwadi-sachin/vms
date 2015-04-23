<%
String prjName="";
String user="";
%>
<div class="header">
	<table width="100%" border="0" cellspacing="0" cellpadding="15">
		<tr>
			<td><div
					style="font-weight: normal; color: #FFFFFF; word-spacing: -20pt; font-size: 40px; text-align: left; font-family: calibri, sans-serif; line-height: 1;"><%=prjName%></div>
			</td>
			<td></td>
			<td style="vertical-align: bottom;"><div
					style="font-weight: normal; color: #FFFFFF; font-size: 16px; text-align: right; margin: 0px; padding: 0px;"><%=user%></div>
			</td>
			<td style="vertical-align: bottom;">
				<%
					//if (!"".equals(user)) {
				%> <a href="logout.do">
					<div
						style="font-weight: normal; color: #FFFFFF; font-size: 16px; text-align: left;">Logout</div>
			</a> <%
 	//}
 %>
			</td>

		</tr>
	</table>
</div>