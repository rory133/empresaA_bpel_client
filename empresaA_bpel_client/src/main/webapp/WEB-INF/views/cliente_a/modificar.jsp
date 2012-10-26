<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Modificar cliente</title>


</head>
<body>

<h2>modificar Cliente_B</h2>

<c:if  test="${!empty cliente_a}">

<form:form method="POST" modelAttribute="cliente_a" id="cliente_a" type="cliente_a" action="${pageContext.request.contextPath}/clientes/cliente/modificarCliente_B" enctype="multipart/form-data">


			
	<fieldset>
	 
	  <table>
	  
	  	     <tr>
			
	     	<!-- <th><label for="nombre_a">idusuarios_a :</label></th> -->
			<td><form:hidden path="idusuarios_a" maxlength="15"  id="nombre_a" value= "${cliente_a.idusuarios_a}" />
				<form:errors path="idusuarios_a" cssClass="error" />
				
			</td>
		</tr>
	     <tr>
			
	     	<th><label for="nombre_a">Nombre :</label></th>
			<td><form:input path="nombre_a" maxlength="15"  id="nombre_a" value= "${cliente_a.nombre_a}"/>
				<form:errors path="nombre_a" cssClass="error" />
				
			</td>
		</tr>
		<tr>
				<th><label for="apellidos_a">Apellidos :</label></th>
				<td><form:input path="apellidos_a" maxlength="15" id="apellidos_a" value= "${cliente_a.apellidos_a}"/>
					<form:errors path="apellidos_a" cssClass="error" />
					
				</td>
		</tr>
		
		<tr>
				<th><label for="dni_nif_a">DNI/NIF :</label></th>
				<td><form:input path="dni_nif_a" maxlength="15" id="dni_nif_a" value= "${cliente_a.dni_nif_a}"/>
					<form:errors path="dni_nif_a" cssClass="error" />
				</td>
		</tr>
		<tr>
				<th><label for="email_a">e-mail :</label></th>
				<td><form:input path="email_a" maxlength="75" id="email_a"  value= "${cliente_a.email_a}"/>
					<form:errors path="email_a" cssClass="error" />
				</td>
		</tr>
		<tr>
				<th><label for="login_usuario_a">Login :</label></th>
				<td><form:input path="login_usuario_a" maxlength="15" id="login_usuario_a" value= "${cliente_a.login_usuario_a}"/>
					<form:errors path="login_usuario_a" cssClass="error" />
					<form:errors cssClass="error" element="loginInvalido"/>
					<small>login de usuario</small>
				</td>
		</tr>
		<tr>
		<th><label for="password_a">Password :</label></th>
				<td><form:password path="password_a" maxlength="15" id="password_a" value= "${cliente_a.password_a}"/>
					<form:errors path="password_a" cssClass="error" />
					<small>de 4 a 10 caracteres</small>
				</td>
		</tr>
				
		<tr>
				<th><label for="direccion_a">Dirección :</label></th>
				<td><form:input path="direccion_a" maxlength="15" id="direccion_a" value= "${cliente_a.direccion_a}"/>
					<form:errors path="direccion_a" cssClass="error" />
				</td>
		</tr>	

		
		<tr>
      <td>provincia_a:</td>
      <td>
       <select name= provincia_a>
		<option value="Alava" selected="selected">Alava</option>
		<option value="Albacete">Albacete</option>
		<option value="Alicante">Alicante</option>
		<option value="Almeria">Almeria</option>
		<option value="Asturias">Asturias</option>
		<option value="Avila">Avila</option>
		<option value="Badajoz">Badajoz</option>
		<option value="Barcelona">Barcelona</option>
		<option value="Burgos">Burgos</option>
		<option value="Caceres">Caceres</option>
		<option value="Cadiz">Cadiz</option>
		<option value="Cantabria">Cantabria</option>
		<option value="Castellon">Castellon</option>
		<option value="Ceuta">Ceuta</option>
		<option value="Ciudad Real">Ciudad Real</option>
		<option value="Cordoba">Cordoba</option>
		<option value="Coruña, A">Coruña, A</option>
		<option value="Cuenca">Cuenca</option>
		<option value="Girona">Girona</option>
		<option value="Granada">Granada</option>
		<option value="Guadalajara">Guadalajara</option>
		<option value="Guipuzcoa">Guipuzcoa</option>
		<option value="Huelva">Huelva</option>
		<option value="Huesca">Huesca</option>
		<option value="Illes Balears">Illes Balears</option>
		<option value="Jaen">Jaen</option>
		<option value="Leon">Leon</option>
		<option value="Lleida">Lleida</option>
		<option value="Lugo">Lugo</option>
		<option value="Madrid">Madrid</option>
		<option value="Malaga">Malaga</option>
		<option value="Melilla">Melilla</option>
		<option value="Murcia">Murcia</option>
		<option value="Navarra">Navarra</option>
		<option value="Ourense">Ourense</option>
		<option value="Palencia">Palencia</option>
		<option value="Palmas, Las">Palmas, Las</option>
		<option value="Pontevedra">Pontevedra</option>
		<option value="Rioja, La">Rioja, La</option>
		<option value="Salamanca">Salamanca</option>
		<option value="Santa Cruz De Tenerife">Santa Cruz De Tenerife</option>
		<option value="Segovia">Segovia</option>
		<option value="Sevilla">Sevilla</option>
		<option value="Soria">Soria</option>
		<option value="Tarragona">Tarragona</option>
		<option value="Teruel">Teruel</option>
		<option value="Toledo">Toledo</option>
		<option value="Valencia">Valencia</option>
		<option value="Valladolid">Valladolid</option>
		<option value="Vizcaya">Vizcaya</option>
		<option value="Zamora">Zamora</option>
		<option value="Zaragoza">Zaragoza</option>
       </select>
      </td>
   </tr>
	
		<tr>
				<th><label for="codigopostal_a">Codigo postal :</label></th>
				<td><form:input path="codigopostal_a" maxlength="15" id="codigopostal_a" value= "${cliente_a.codigopostal_a}"/>
					<form:errors path="codigopostal_a" cssClass="error" />
				</td>
		</tr>	
				

		
	     <tr>
    	  
    	     <td><input name="cliente_a" type="submit" value="Gruardar cambios" /></td>
  	   </tr>

	</table>	

	</fieldset>
	





	
</form:form>
</c:if>
<c:url var="editUrl" value="../../productos/listado" />
			<a href="${editUrl}"    onmouseover="window.status = 'Pulse para volver al listado de productos'; return true" onmouseout="window.status=''"> <span title='Pulse para volver al listado de productos'> <img border=0 src="../../resources/imagenes/listado.jpg" height=68 width=53> </a>
</body>
</html>