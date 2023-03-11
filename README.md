# really-executable-jar-maven-plugin

[![Maven](https://img.shields.io/maven-central/v/org.skife.maven/really-executable-jar-maven-plugin)](https://central.sonatype.com/search?q=really-executable-jar-maven-plugin&namespace=org.skife.maven)

## Usage

There is an introductory blog post at http://skife.org/java/unix/2011/06/20/really_executable_jars.html

To use:

1. Make sure you build an "uberjar" with a main class in the MANIFEST. See the [Maven Shade plugin's documentation](https://maven.apache.org/plugins/maven-shade-plugin/examples/executable-jar.html) for one way of doing this. 
2. Add this plugin to your build to make the JAR directly executable. All configuration parameters are optional; the defaults should work for many cases.

```xml
<plugin>
  <groupId>org.skife.maven</groupId>
  <artifactId>really-executable-jar-maven-plugin</artifactId>
  <version>2.1.1</version>
  <configuration>
    <!-- (optional) flags to be interpolated into the java invocation -->
    <!-- as "java $flags -jar ..." -->
    <flags>-Xmx1G</flags>
    
    <!-- (optional) input file name: only this specific file will be made executable -->
    <inputFile>target/fooBla.jar</inputFile>
    
    <!-- (optional) classifier name: only artifacts with this classifier are made executable -->
    <classifier>shaded</classifier>

    <!-- (optional) support other packaging formats than jar -->
    <allowOtherTypes>true</allowOtherTypes>

    <!-- (optional) name for a new binary executable, if not set will just
         make the original artifact executable -->
    <programFile>nifty-executable</programFile>
    
    <!-- (optional) name for a file that will define what script gets
         embedded into the executable jar.  This can be used to
         override the default startup script which is
         `#!/bin/sh
         
         exec java " + flags + " -jar "$0" "$@" -->
    <scriptFile>src/packaging/someScript.extension</scriptFile>
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

## Changes

2.1.1 
- Overwrite existing files (@dkfellows)

2.1.0
- use zip-prefixer instead of rebuilding JAR (@klausbrunner)
- improve handling of multi-artifact builds (@klausbrunner)
- add inputFile parameter to select any file (@klausbrunner)
- add basic integration testing (@klausbrunner)
- require JDK 11+ to build (plugin still targets JDK 8) 

2.0.0 
- support ZIP64 format
- require Java 8, drop support for JDK7
- support packaging other file formats than jars

1.4.0 
- require Java 7, change code to use JDK7 APIs
- Support Windows
- Don't suppress errors

1.3.0 
- add helpmojo
- allow attachment of executable instead of unconditional replacement
- make extension configurable
- allow script replacement in the resulting executable

1.2.0 
- never released

1.1.0 
- If programFile is set, do not make the base artifact (the .jar) executable, just the programFile one.

1.0.0 
- Stable


