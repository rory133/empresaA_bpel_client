<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Crear administrador</title>


</head>
<body>

<h2>Introducir Administrador_B</h2>

<form:form method="POST" modelAttribute="administrador_a" id="administrador_a" type="administrador_a" enctype="multipart/form-data">


			
	<fieldset>
	 
	  <table>
	     <tr>
			
	     	<th><label for="nombre_a">Nombre :</label></th>
			<td><form:input path="nombre_a" maxlength="55"  id="nombre_a"/>
				<form:errors path="nombre_a" cssClass="error" />
				
			</td>
		</tr>
		<tr>
				<th><label for="apellidos_a">Apellidos :</label></th>
				<td><form:input path="apellidos_a" maxlength="55" id="apellidos_a"/>
					<form:errors path="apellidos_a" cssClass="error" />
					
				</td>
		</tr>
		
		<tr>
				<th><label for="dni_nif_a">DNI/NIF :</label></th>
				<td><form:input path="dni_nif_a" maxlength="9" id="dni_nif_a"/>
					<form:errors path="dni_nif_a" cssClass="error" />
				</td>
		</tr>
		<tr>
				<th><label for="email_a">e-mail :</label></th>
				<td><form:input path="email_a" maxlength="75" id="email_a"/>
					<form:errors path="email_a" cssClass="error" />
				</td>
		</tr>
		
		<tr>
				<th><label for="login_usuario_a">Login :</label></th>
				<td><form:input path="login_usuario_a" maxlength="45" id="login_usuario_a"/>
					<form:errors path="login_usuario_a" cssClass="error" />
					<form:errors cssClass="error" element="loginInvalido"/>
					<small>de 4 a 15 caracteres</small>
				</td>
		</tr>
		<tr>
		<th><label for="password_a">Password :</label></th>
				<td><form:password path="password_a" maxlength="15" id="password_a"/>
					<form:errors path="password_a" cssClass="error" />
					<small>de 4 a 10 caracteres</small>
				</td>
		</tr>
		
		<tr>
				<th><label for="cargo_a">Cargo :</label></th>
				<td><form:input path="cargo_a" maxlength="45" id="cargo_a"/>
					<form:errors path="cargo_a" cssClass="error" />
					
				</td>
		</tr>
		<tr>
		<th><label for="matricula_a">matricula :</label></th>
				<td><form:input path="matricula_a" maxlength="8" id="matricula_a"/>
					<form:errors path="matricula_a" cssClass="error" />
					<small>maximo 8 caracteres</small>
				</td>
		</tr>

				

		
	     <tr>
    	  
    	     <td><input name="administrador_a" type="submit" value="Crear Cuenta administrador" /></td>
  	   </tr>

	</table>	

	</fieldset>
	





	
</form:form>

</body>
</html>