<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%! 
  public class MyFunctions {
  
    public String escapeXml(String input) {
      if (input == null) {
        return null;
      }
  
      StringBuilder result = new StringBuilder();
      for (char c : input.toCharArray()) {
        switch (c) {
          case '<':
            result.append("&lt;");
            break;
          case '>':
            result.append("&gt;");
            break;
          case '&':
            result.append("&amp;");
            break;
          case '"':
            result.append("&quot;");
            break;
          case '\'':
            result.append("&#39;");
            break;
          default:
            result.append(c);
        }
      }
      return result.toString();
    }
  }


  MyFunctions myFunctions = new MyFunctions();

%>