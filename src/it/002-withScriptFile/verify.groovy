File jar = new File(new File(basedir, "target"), 'test-002-1.0-SNAPSHOT.jar')
assert jar.exists()
result = jar.toString().execute().text
assert result.contains("Fake news!")
