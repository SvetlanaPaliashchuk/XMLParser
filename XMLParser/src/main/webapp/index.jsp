<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Travel Order XML parser</title>
</head>
<body>
<div>
    <h1 align="center">Browse your XML</h1>
    <form action="Controller" method="POST" enctype="multipart/form-data">
        <p align="center">
        	<input type="file" name="file" accept=".xml"/>
        </p>
        <p align="center">   
        	<button type="submit" name="command" value="DOM">DOM</button>
            <button type="submit" name="command" value="SAX">SAX</button>
            <button type="submit" name="command" value="STAX">STAX</button>
        </p>
    </form>
</div>
</body>
</html>
