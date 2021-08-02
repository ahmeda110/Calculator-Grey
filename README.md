# Calculator-Grey

Utalized Java, JS, JQuery, HTML, CSS, etc

status: Complete

# ABOUT

A frontend component was constructed using JS, HTML & CSS in which a user can enter any operation they wish to. Then, the mathematical expression is sent to a Java server through String Query and AJAX (Example: http://localhost:8080/leftOperand=5&rightOperand=10&operation=+). The server then parses the request and performs the operation. When done, the result is sent back to the user (client-side) in JSON format. Finally the data in the JSON string is displayed on the UI where the user can then make another request.

Error Handling was Implemented: Missing operator, operand, invalid inputs, etc.
