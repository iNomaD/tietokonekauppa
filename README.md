<h2>RESTfull web service with Jersey, Spring and Hibernate using PostgreSQL database (Java configuration).</h2>

<b>Backend Technologies:</b>
<ul>
<li>Java 8</li>
<li>Maven 3</li>
<li>Jersey 2.23</li>
<li>Spring 4</li>
<li>Hibernate 4</li>
<li>PostgreSQL</li>
</ul>
  
<b>Frontend Technologies:</b>
<ul>
<li>Bootstrap</li>
<li>AngularJS 2</li>
<li>ng-admin</li>
</ul>

<h2>Prepare database</h2>
<p>Install PostgreSQL</p>
<p>Create table tietokonekauppa with pgAdmin or cmd</p>
<pre>createdb -E utf8 -U postgres tietokonekauppa</pre>
<p>Update credentials in resources/application.properties</p>
<pre>
jdbc.url = jdbc:postgresql://localhost:5432/tietokonekauppa
jdbc.username = postgres (your username)
jdbc.password = 12345 (your password)
</pre>

<h2>Use app</h2>
<p>Common GUI: localhost:8080/index.jsp</p>
<p>Admin GUI: http://localhost:8080/admin</p>

<p>Common API: localhost:8080/api/</p>
<p>Admin  API: localhost:8080/api/admin/</p>
