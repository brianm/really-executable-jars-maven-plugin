File jar = new File(new File(basedir, "target"), 'test-010-1.0-SNAPSHOT.jar')
assert jar.exists()
result = jar.toString().execute().text
assert result.contains("Hello embedded world!")
