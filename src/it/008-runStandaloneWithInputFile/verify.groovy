File jar = new File(new File(basedir, "target"), 'test-008-1.0-SNAPSHOT.jar')
assert jar.exists()
result = jar.toString().execute().text
assert result.contains("Hello world!")
