# Calculator-Grey

Utalized Java, JS, JQuery, HTML, CSS, etc

Status: Complete

# ABOUT

A frontend component was constructed using JS, HTML & CSS in which a user can enter any mathematical operation. Then, the mathematical expression is sent to a Java server through a String Query and AJAX request (Example: http://localhost:8080/leftOperand=5&rightOperand=10&operation=+). The server then parses the request and performs the operation. When done, the result is sent back to the user (client-side) in JSON format. Finally the data in the JSON string is displayed on the UI where the user can then make another request.

Error Handling was Implemented: Missing operator, operand, invalid inputs, etc.

# Installing

1- git clone (https/ssh) depending on your permissions<br>
2- download the .jar file at this link https://jar-download.com/artifacts/org.json<br>
3- locate and extract the json.jar file right click on the project and go to build path -add external jar -hit apply (eclipse)<br>
4- javac Manin.java<br>
5- java Main to start the server<br>
6- Run the HTML page (live-server)<br>
