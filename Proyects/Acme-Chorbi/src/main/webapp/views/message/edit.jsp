<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="chirp/chorbi/edit.do" modelAttribute="chirp">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender"/>
	<form:hidden path="folder"/>
	<jstl:if test="${chirp.id!=0}">
		<form:hidden path="recipient"/>
	</jstl:if>
	
	

	<form:label path="subject">
		<spring:message code="message.title" />:
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />
	<br />
	
	<form:label path="text">
		<spring:message code="message.body" />:
	</form:label>
	<form:textarea path="text" />
	<form:errors cssClass="error" path="text" />
	<br />
	
	<form:label path="moment">
		<spring:message code="message.moment" />:
	</form:label>
	<form:input path="moment" readonly="true" />
	<form:errors cssClass="error" path="moment" />
	<br />
	
	<jstl:if test="${chirp.id==0}">
	<form:label path="recipient">
		<spring:message code="message.recipient" />:
	</form:label>
	<form:select id="actors" path="recipient" >
		<form:option value="0" label="----"/>
		<jstl:forEach items="${actors}" var="actor">
			<form:option value="${actor.id}" label="${actor.surname}, ${actor.name}" />
		</jstl:forEach>
	</form:select>
	<form:errors cssClass="error" path="recipient" />
	<br />
	</jstl:if>
	
	<acme:textbox code="message.attachment" path="attachments"/>


	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="location.href = ('folder/actor/list.do');" />
	<br />

	

</form:form>
