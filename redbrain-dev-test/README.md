This application will take an unordered list of boarding passes, 
and print out to the console an ordered list of instructions.

To run the application, from the command line:
1. Navigate to the unzipped project directory
2. Run Maven goals "clean" and "package"
3. Run the output JAR file located in the target directory, using "java -jar <name_of_jar>"

The project is compiled and executed with Java 8.

To run the tests, run Maven goals "clean" and "test".

The application uses static data, inside the main Application class. 
To use a different list of boarding passes, go into this class and add/remove/change
any of the predefined boarding passes (uses a builder pattern).