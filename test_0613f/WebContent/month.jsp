<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select name="month">
	<c:choose>
		<c:when test="${month eq 1}">
			<option value="1" selected>1月</option>
		</c:when>
		<c:otherwise>
			<option value="1">1月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 2}">
			<option value="2" selected>2月</option>
		</c:when>
		<c:otherwise>
			<option value="2">2月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 3}">
			<option value="3" selected>3月</option>
		</c:when>
		<c:otherwise>
			<option value="3">3月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 4}">
			<option value="4" selected>4月</option>
		</c:when>
		<c:otherwise>
			<option value="4">4月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 5}">
			<option value="5" selected>5月</option>
		</c:when>
		<c:otherwise>
			<option value="5">5月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 6}">
			<option value="6" selected>6月</option>
		</c:when>
		<c:otherwise>
			<option value="6">6月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 7}">
			<option value="7" selected>7月</option>
		</c:when>
		<c:otherwise>
			<option value="7">7月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 8}">
			<option value="8" selected>8月</option>
		</c:when>
		<c:otherwise>
			<option value="8">8月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 9}">
			<option value="9" selected>9月</option>
		</c:when>
		<c:otherwise>
			<option value="9">9月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 10}">
			<option value="10" selected>10月</option>
		</c:when>
		<c:otherwise>
			<option value="10">10月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 11}">
			<option value="11" selected>11月</option>
		</c:when>
		<c:otherwise>
			<option value="11">11月</option>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${month eq 12}">
			<option value="12" selected>12月</option>
		</c:when>
		<c:otherwise>
			<option value="12">12月</option>
		</c:otherwise>
	</c:choose>
</select>