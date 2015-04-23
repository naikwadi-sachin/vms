<!--left start-->
<div class="left_div">
	<div class="adCntnr">
		<div class="acco2">
			<%
				if (session.getAttribute("projectName") != null) {
			%>
			<div class="expand">
				<a title="expand/collapse" href="#"
					onclick="location.href='showProject.do'" style="display: block;">Show
					All Projects </a>
			</div>

			<div class="expand">
				<a title="expand/collapse" href="#"
					onclick="location.href='project.do?action=select'"
					style="display: block;">Project Home </a>
			</div>

			<div class="expand">
				<a title="expand/collapse" href="#"
					onclick="location.href='addExpenses.jsp'" style="display: block;">Add
					Expenses</a>
			</div>
			<div class="expand">
				<a title="expand/collapse" href="#"
					onclick="location.href='expenseType.do?action=selectAll'"
					style="display: block;">Add Expense Type</a>
			</div>
			<div class="expand">
				<a title="expand/collapse" href="#"
					onclick="location.href='laborType.do?action=selectAll'"
					style="display: block;">Add Labor Type</a>
			</div>
			<div class="expand">
				<a title="expand/collapse" href="#"
					onclick="location.href='listDoc.do?action=selectAll'"
					style="display: block;">Add Document</a>
			</div>
			<%
				} else {
			%>

			<div class="expand">
				<logic:present name="currentUser" scope="session">
					<logic:equal name="currentUser" property="userType" value="admin">
						<a title="expand/collapse" href="#"
							onclick="location.href='addProject.jsp'" style="display: block;">Add
							Project</a>
					</logic:equal>
				</logic:present>
			</div>

			<!-- 		<div class="expand">
				<a title="expand/collapse" href="addExpenseType.do"
					onclick="location.href='addExpenseType.do'"
					style="display: block;">Add Expense Type</a>
			</div>
			
			<div class="expand">
				<a title="expand/collapse" href="viewExpenses.jsp"
					onclick="location.href='viewExpenses.jsp'" style="display: block;">View
					Expenses</a>
			</div>
			
			<div class="expand">
				<a title="expand/collapse" href="addLaborType.jsp"
					onclick="location.href='addLaborType.jsp'"
					style="display: block;">Add Labor Type</a>
			</div>
 -->
			<%
				}
			%>
		</div>
	</div>
</div>
<!--left end-->
