#### Spring JPA Encryption Example ####

Provides a working application that performs column level encryption using a JPA Converter.

__Pre-Requisites__
* Understanding of the Spring Framework (Spring Boot, Spring Data, Spring Rest)
* Understanding of PostgreSQL and simple SQL queries
* Understanding of how encryption works and the importance of safely storing the encryption key
* Install and Configure Project Lombok with Spring Tool Suite (STS)
   * <a href="https://projectlombok.org/download.html" target="_blank">Download lombok.jar</a>
   * Run the lombok.jar from a command prompt or terminal as an administrator
   ```
   java -jar lombok.jar
   ```
   * Follow the instructions
* Install the Java Cryptography Extension (JCE) within your JAVA_HOME/jre/lib/security folder
   * Allows keys greater than 128
   * <a href="http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html" target="_blank">Download JCE Files</a>  
   * Replace the existing jar files in the JAVA_HOME/jre/lib/security folder (US_export_policy.jar and local_policy.jar) with the JCE jar files 
   > Backup existing US_export_policy.jar and local_policy.jar files before replacing them
