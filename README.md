<h2>RESTfull web service with Jersey and hibernate using PostgreSQL database (Java configuration).</h2>

<b>Backend Technologies:</b>
<ul>
<li>Java 8</li>
<li>Maven 3</li>
<li>Jersey 2.23</li>
<li>Hibernate 4</li>
<li>PostgreSQL</li>
</ul>
  
<b>Frontend Technologies:</b>
<ul>
<li>Bootstrap</li>
<li>AngularJS 2</li>
</ul>

<h2>Prepare database</h2>
<p>Install PostgreSQL</p>
<p>Create table tietokonekauppa with pgAdmin or cmd</p>
<pre>createdb -E utf8 -U postgres tietokonekauppa</pre>
<p>Update credentials in resources/hibernate.cfg.xml</p>
<pre>
connection.url = jdbc:postgresql://localhost:5432/tietokonekauppa
connection.username = postgres (your username)
connection.password = 12345 (your password)
</pre>

<h2>Use app</h2>
<p>Go to GUI: localhost:8080/index.jsp</p>
<p>Common API: localhost:8080/api/</p>
<p>Admin  API: localhost:8080/api/admin/</p>
