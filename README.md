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
   
* Install PostgreSQL to your local machine

__Tools/Libraries Used__
* Spring Tool Suite (STS)
* Project Lombok 
* PostgreSQL 
* pgAdmin
* Java Cryptography Extension (JCE)
* Postman - Chrome Application

__Verify Local PostgreSQL Credentials__

Change the application.yml local PostgreSQL URL, User, and Password to reflect your local configuration
```
datasource:
   url: jdbc:postgresql://localhost:5432/[DATABASE NAME]
   username: [USER NAME]
   password: [PASSWORD]
```

__Generate the Encryption Key__

We will use the CryptoHelper class within the project to generate the encryption key.  The CryptoHelper.generateSymmetricKey() static method will create an AES 256 key.  This key will be used to encrypte and decrypt the columns.

It is very important that the encryption key is maintained in a safe environment and backed up to a safe and secure location.  If this key is compromised the data is at risk to criminal elements, and if the key is misplaced it will be impossible to decrypt the data.

1. Uncomment key generation code in the SpringJpaEncryptionApplication.java file
```
log.info("ENCRYPTION KEY: " + CryptoHelper.generateSymmetricKey());
```
2. Run the application
  Within STS - Right-click on the project and select Run As - Spring Boot App
  Within Command Prompt or Terminal
  ```
  cd [project directory]
  mvn spring-boot:run
  ```
3. Copy the encryption key within the console output
4. Replace the encryption key within the application.yml with this key

__Let's See Some Encrypted Data__
1. Run the application

  Make sure the active profile within the application.yml is "local"   
  Within STS - Right-click on the project and select Run As - Spring Boot App   
  Within Command Prompt or Terminal
  ```
  cd [project directory]
  mvn spring-boot:run
  ```
2. Open Postman
3. Create a record
   1. Select "POST"
   2. Enter URL http://localhost:8085/encryptionExample
   3. Authorization - No Auth
   4. Headers - Key = Content-Type, Value = application/json
   5. Body - Select raw, Enter "This is a test" in the text box
   6. Click Send
4. Create as many records as you wish.  Creating 2 records with the same value will show you the value of using the CBC encryption mode with an Initialization Vector (IV) since the stored encrypted values aren't the same.
5. List all the records
   1. Add a new tab in Postman
   2. Select "GET"
   3. Enter URL http://localhost:8085/encryptionExample
   4. Click Send
6. Open pgAdmin4 and select all the records in the table to review the stored values


#### References ####
<a href="https://tersesystems.com/2015/12/17/the-right-way-to-use-securerandom/" target="_blank">The Right Way to Use SecureRandom</a>   
<a href="https://gist.github.com/twuni/5668121" target="_blank">Crypto Helper</a>   

