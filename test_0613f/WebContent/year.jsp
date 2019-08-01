<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select name="year">
	<c:choose>
		<c:when test="${year eq 2010}">
			<option value="2010" selected>2010年</option>
		</c:when>
		<c:otherwise>
			<option value="2010">2010年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2011}">
			<option value="2011" selected>2011年</option>
		</c:when>
		<c:otherwise>
			<option value="2011">2011年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2012}">
			<option value="2012" selected>2012年</option>
		</c:when>
		<c:otherwise>
			<option value="2012">2012年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2013}">
			<option value="2013" selected>2013年</option>
		</c:when>
		<c:otherwise>
			<option value="2013">2013年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2014}">
			<option value="2014" selected>2014年</option>
		</c:when>
		<c:otherwise>
			<option value="2014">2014年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2015}">
			<option value="2015" selected>2015年</option>
		</c:when>
		<c:otherwise>
			<option value="2015">2015年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2016}">
			<option value="2016" selected>2016年</option>
		</c:when>
		<c:otherwise>
			<option value="2016">2016年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2017}">
			<option value="2017" selected>2017年</option>
		</c:when>
		<c:otherwise>
			<option value="2017">2017年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2018}">
			<option value="2018" selected>2018年</option>
		</c:when>
		<c:otherwise>
			<option value="2018">2018年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2019}">
			<option value="2019" selected>2019年</option>
		</c:when>
		<c:otherwise>
			<option value="2019">2019年</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${year eq 2020}">
			<option value="2020" selected>2020年</option>
		</c:when>
		<c:otherwise>
			<option value="2020">2020年</option>
		</c:otherwise>
	</c:choose>
</select>