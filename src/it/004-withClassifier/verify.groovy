File jar = new File(new File(basedir, "target"), 'test-004-1.0-SNAPSHOT.jar')
assert jar.exists()
try {
    result = jar.toString().execute().text
    throw new AssertionError("this should not have worked!")
} catch (IOException ignored) {
}

File jar2 = new File(new File(basedir, "target"), 'test-004-1.0-SNAPSHOT-secondary.jar')
assert jar2.exists()
result = jar2.toString().execute().text
assert result.contains("Hello world!")
