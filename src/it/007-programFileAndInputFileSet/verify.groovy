File jar1 = new File(new File(basedir, "target"), 'test-007-1.0-SNAPSHOT.jar')
assert jar1.exists()
try {
    result = jar1.toString().execute().text
    throw new AssertionError("this should not have worked!")
} catch (IOException ignored) {
}

File jar2 = new File(new File(basedir, "target"), 'test-007-1.0-SNAPSHOT-secondary.jar')
assert jar2.exists()
try {
    result = jar2.toString().execute().text
    throw new AssertionError("this should not have worked!")
} catch (IOException ignored) {
}

File bananaJar = new File(new File(basedir, "target"), 'bananas')
assert bananaJar.exists()
result = bananaJar.toString().execute().text
assert result.contains("Hello world!")
