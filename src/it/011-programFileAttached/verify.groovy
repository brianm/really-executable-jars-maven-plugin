import java.nio.file.Files

File bananaJar = new File(new File(basedir, "target"), 'bananas')
assert bananaJar.exists()
result = bananaJar.toString().execute().text
assert result.contains("Hello world!")

File installedPlainJar = new File(localRepositoryPath, "org/skife/maven/test-011/1.0-SNAPSHOT/test-011-1.0-SNAPSHOT.jar")
assert installedPlainJar.exists()

// seems that Maven will forcibly rename the attached artifact. this monkey gets no banana.
File installedBanana = new File(localRepositoryPath, "org/skife/maven/test-011/1.0-SNAPSHOT/test-011-1.0-SNAPSHOT.sh")
assert installedBanana.exists()

assert Files.size(bananaJar.toPath()) == Files.size(installedBanana.toPath())
