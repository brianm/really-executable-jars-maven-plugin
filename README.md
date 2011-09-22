To use it, add a plugin to your pom like

``` xml
<plugin>
  <groupId>org.skife.maven</groupId>
  <artifactId>really-executable-jar</artifactId>
  <version>1.0.0</version>
  <configuration>
    <flags>-Xmx1G</flags>
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

It isn't in maven central yet :-(
