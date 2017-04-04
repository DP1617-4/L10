<%--
 * list.jsp
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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="chorbis" requestURI="${requestURI}" id="row">

	<!-- Attributes -->
	<spring:message code="chorbi.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="chorbi.surname" var="chorbiHeader" />
	<display:column property="surname" title="${chorbiHeader}" sortable="true" />
	
	<spring:message code="chorbi.description" var="descriptionHeader" />
	<display:column property="stars" title="${starsHeader}" sortable="true" />

	<spring:message code="chorbi.actor" var="actorHeader" />
	<display:column title="${actorHeader}">
		<a href="chorbi/actor/display.do?actorId=${row.actor.id}">${row.actor.name} ${row.actor.surname }</a>
	</display:column>
	
</display:table>


<security:authorize access="hasAnyRole('LESSOR','TENANT')">
	<div>
		<a href="comment/actor/create.do?commentableId=${commentable.id}"> <spring:message
				code="comment.create" />
		</a>
	</div>
</security:authorize>
<br/>

<input type="button" name="back"
		value="<spring:message code="comment.back" />"
		onclick="location.href = 'actor/list.do';" />&nbsp;
	<br />