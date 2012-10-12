SQLDump
=======

[Download SQLDump-0.4.jar](https://github.com/downloads/aparsons/SQLDump/SQLDump-0.4.jar)

Command line utility to execute SQL queries and export results to a CSV file.

Currently only supports Oracle and MySQL. For additional driver support please contact me or fork the project.

Usage
-----

```java
java -jar SQLDump-0.4.jar -url [jdbc:oracle:thin:@hostname:port:sid] -user [username] -pass [password] -sql [query]
```
Remember to enclose your SQL query with quotes!

### Required

+ -url <arg>                   A database url of the form jdbc:subprotocol:subname
+ -user,--username <arg>       The database user on whose behalf the connection is being made
+ -pass,--password <arg>       The user's password
+ -sql,--query <arg>           Any SQL statement

### Optional

+ -f,--file <arg>              File path of the csv report
+ -headers,--include-headers   Include column headers in generated file
