<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Error</title>

</head>

<body>
	<h3>Error</h3>

	<div class="error">
		<h2 style="color: red; font-weight: bold">${errorMsg}</h2>
		<h3 style="color: red; font-weight: bold">${error}</h3>
	</div>

<form action="Controller" method="POST">
				<p align="left"> 
		
                          <input type="hidden" name="command" value="home" />
                          <button type="submit">Home</button>
                          	    		</p>
                          
                     </form>
</html>