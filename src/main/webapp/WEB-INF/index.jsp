<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Linjen over må alltid være med som første linje i filen --%>

<!DOCTYPE html>
<html>
  <head>
    <style>
      .button {
        background-color: black;
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
      }
      input[type="text"] {
        width: 50%;
        padding: 12px 20px;
        margin: 8px 0;
        box-sizing: border-box;
        border: 2px solid black;
        border-radius: 4px;
      }
    </style>
  </head>
  <body>
    <h2>Bilutleige</h2>
    <input type="text" placeholder="Search for available cars">
    <button class="button">Search Available Cars</button>
    <br><br>
    <button class="button">Logg inn</button>
    <button class="button">Velg lokasjon</button>
  </body>
</html>







