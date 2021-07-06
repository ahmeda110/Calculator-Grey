package backend;

import java.io.*;
import java.net.Socket;

import org.json.simple.JSONObject;

class RequestProcessor implements Runnable {
    private Socket socket = null;
    private OutputStream os = null;
    private BufferedReader in = null;
    private String msgToClient = "HTTP/1.1 200 OK\n" + "Server: HTTP server/0.1\n"
            + "Access-Control-Allow-Origin: *\n\n";
    private JSONObject jsonObject = new JSONObject();

    public RequestProcessor(Socket Socket) {
        super();
        try {
            socket = Socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        // Collecting request Query String
        try {
            StringBuilder payload = new StringBuilder(); // Building it as a string
            while (in.ready()) {
                payload.append((char) in.read());
            }

            // Parsing
            String[] arr = payload.toString().split("&");

            String[] leftRaw = arr[0].split("leftOperand=");
            String left = leftRaw[1];
            String right = arr[1].replaceAll("[^0-9]", "");

            String operandFirstExtraction = arr[2];
            String[] operandSecondExtraction = operandFirstExtraction.split("=");
            String operand = operandSecondExtraction[1];

            // Both inputs and operation sign collected and extracted but all in form of
            // string now

            // Conversion to human understandable signs
            if (operand.equals("%2B")) {
                operand = "+";
            }
            if (operand.equals("%2F")) {
                operand = "/";
            }
            if (operand.equals("%25")) {
                operand = "%";
            }

            String expression = left.concat(" ").concat(operand).concat(" ").concat(right);
            double result = 0;
            switch (operand) {
                case "+":
                    result = Double.parseDouble(left) + Double.parseDouble(right);
                    break;
                case "-":
                    result = Double.parseDouble(left) - Double.parseDouble(right);
                    break;
                case "*":
                    result = Double.parseDouble(left) * Double.parseDouble(right);
                    break;
                case "/":
                    result = Double.parseDouble(left) / Double.parseDouble(right);
                    break;
                case "%":
                    result = Double.parseDouble(left) % Double.parseDouble(right);
                    break;
            }
            // Send back the result and expression
            jsonObject.put("Expression", expression);
            jsonObject.put("Result", result);

            String response = msgToClient + jsonObject.toString();
            os.write(response.getBytes());
            os.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}