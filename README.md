# really-executable-jar-maven-plugin

There is an introductory blog post at http://skife.org/java/unix/2011/06/20/really_executable_jars.html

To use it, add a plugin to your pom like:

``` xml
<!-- You need to build an exectuable uberjar, I like Shade for that -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.4.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">

                        <!-- note that the main class is set *here* -->

                        <mainClass>com.example.Main</mainClass>
                    </transformer>
                </transformers>
                <createDependencyReducedPom>false</createDependencyReducedPom>
            </configuration>
        </execution>
    </executions>
</plugin>

<!-- now make the jar chmod +x style executable -->
<plugin>
  <groupId>org.skife.maven</groupId>
  <artifactId>really-executable-jar-maven-plugin</artifactId>
  <version>2.1.0</version>
  <configuration>
    <!-- value of flags will be interpolated into the java invocation -->
    <!-- as "java $flags -jar ..." -->
    <flags>-Xmx1G</flags>

    <!-- (optional) name for binary executable, if not set will just -->
    <!-- make the regular jar artifact executable -->
    <programFile>nifty-executable</programFile>

    <!-- (optional) support other packaging formats than jar -->
    <!-- <allowOtherTypes>true</allowOtherTypes> -->
    
    <!-- (optional) name for a file that will define what script gets -->
    <!-- embedded into the executable jar.  This can be used to -->
    <!-- override the default startup script which is -->
    <!-- `#!/bin/sh -->
    <!--            -->
    <!-- exec java " + flags + " -jar "$0" "$@" -->
    <!-- <scriptFile>src/packaging/someScript.extension</scriptFile> -->
  </configuration>

  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>really-executable-jar</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

Changes:

```
2.1.0 - use zip-prefixer instead of rebuilding JAR
      - improve handling of multi-artifact builds
      - add inputFile parameter to select any file
      - add basic integration testing
      - require JDK 11+ to build (plugin still targets JDK 8)

2.0.0 - support ZIP64 format
      - require Java 8, drop support for JDK7
      - support packaging other file formats than jars

1.4.0 - require Java 7, change code to use JDK7 APIs
      - Support Windows
      - Don't suppress errors

1.3.0 - add helpmojo
      - allow attachment of executable instead of unconditional replacement
      - make extension configurable
      - allow script replacement in the resulting executable

1.2.0 - never released

1.1.0 - If programFile is set, do not make the base artifact (the
.jar) executable, just the programFile one.

1.0.0 - Stable
```


