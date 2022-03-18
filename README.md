# AMGS - Armageddon Space

> AMGS was a INSA Lyon, 2nd year project (March - May 2020)

A game by Florian RASCOUSSIER, Grégoire CLAUS and Martin MALLET.

The game is made in vanilla Java using no external library.

## Java commands

`java -jar <jar_file>`: execute `.jar`.


## Maven commands

### Useful links

[Install Maven](https://www.journaldev.com/33588/install-maven-linux-ubuntu)

[maven tutorial | Tutorialspoint](https://www.tutorialspoint.com/maven/maven_creating_project.htm)

### build & run

`mvn --version`: Give back the version of maven, java and directory.
`mvn package`: Compile, run tests and build the .jar file of the project.
`mvn exec:java`: Run the .jar file (run the game).
`mvn package exec:java`: Compile, run tests, build and run the .jar file.

### new Maven project

`mvn archetype:generate -DgroupId=amgs -DartifactId=amgs_maven -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false`

NB: don't forget to create the resources folder inside ./src/main/

### installation

> Make sure to install the `exec-maven-plugin` to be able to have the `exec:java` command in order to execute the `.jar` file. Add it in `pom.xml` file.

```toml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>1.6.0</version>
    <configuration>
        <mainClass>amgs.App</mainClass>
    </configuration>
</plugin>
```
