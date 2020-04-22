<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Result</title>
    <style>
		table, th, td {
  			border: 1px solid black;
  			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<h1 align="center">Travel orders: </h1>
	<table border="1">
    	<thead>
    		<tr>
        		<th scope="col" rowspan="2">id</th>
        		<th scope="col" rowspan="2">Tour type</th>
		        <th scope="col" rowspan="2">Country</th>
		        <th scope="col" rowspan="2">City</th>
		        <th scope="col" rowspan="2">Start Date</th>
		        <th scope="col" rowspan="2">Duration</th>
		        <th scope="col" rowspan="2">Number <br>Of Tourists</th>
		        <th scope="col" rowspan="2">Transport</th>
		        <th scope="col" rowspan="2">Meal</th>
		        <th scope="col" rowspan="2">Price</th>
		        <th scope="col" colspan="3">Tourists Info</th>
    		</tr>
    		<tr>
		        <th scope="col">Surname</th>
		        <th scope="col">Name</th>
		        <th scope="col">Passport</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="order" items="${orderSet}">
		        <tr align="center">
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.id}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.tourType}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.country}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.city}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.date}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.duration}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.numberOfTourists}"/></td> 
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.transport}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.meal}"/></td>
	            <td rowspan="${order.numberOfTourists}"><c:out value="${order.price}"/></td>
	            <c:forEach items="${order.tourists}" var="tourist">
	                        <td align="center">${tourist.surname}</td>
	                        <td align="center">${tourist.name}</td>
	                        <td align="center">${tourist.passport}</td>
	                        <tr>
	            </c:forEach>
    		</c:forEach>
    	</tbody>
	</table>
	<br>
	

		<form action="Controller" method="POST">
				<p align="right"> 
		
                          <input type="hidden" name="command" value="home" />
                          <button type="submit">Home</button>
                          	    		</p>
                          
                     </form>
</body>
</html>