package huster.crawl;

import org.python.util.PythonInterpreter;
import org.python.core.*;

class test3 {
    public static void main(String a[]) {
        System.setProperty("python.console.encoding", "UTF-8");

        // Set the python.import.site property to false
        System.setProperty("python.import.site", "false");

        // PySystemState sys = new PySystemState();

        // // Clear the existing sys.path and set the correct paths
        // sys.path.clear();
        // sys.path.append(new PyString("C:\\Users\\hieuh\\.m2\\repository\\org\\python\\jython\\2.7.4b1\\Lib"));
        // sys.path.append(new PyString("__classpath__"));
        // sys.path.append(new PyString("__pyclasspath__/"));

        // sys.prefix = new PyString("C:\\Users\\hieuh\\.m2\\repository\\org\\python\\jython\\2.7.4b1");

        PythonInterpreter python = new PythonInterpreter();

        int number1 = 10;
        int number2 = 32;

        python.set("number1", new PyInteger(number1));
        python.set("number2", new PyInteger(number2));
        python.exec("number3 = number1 + number2");
        PyObject number3 = python.get("number3");
        System.out.println("val : " + number3.toString());
    }

}