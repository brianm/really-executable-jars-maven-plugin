File jar = new File(new File(basedir, "target"), 'test-003-1.0-SNAPSHOT.jar')
assert jar.exists()
result = jar.toString().execute().text
assert result.contains("Salut world!")
