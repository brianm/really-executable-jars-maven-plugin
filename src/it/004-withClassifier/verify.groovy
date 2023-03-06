File jar = new File(new File(basedir, "target"), 'testapp-1.0.jar')
assert jar.exists()
try {
    result = jar.toString().execute().text
    throw new AssertionError("this should not have worked!")
} catch (IOException ignored) {
}

File jar2 = new File(new File(basedir, "target"), 'testapp-1.0-secondary.jar')
assert jar2.exists()
result = jar2.toString().execute().text
assert result.contains("Hello world!")
